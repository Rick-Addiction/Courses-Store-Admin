<template>
  <div class="app-container">
    <div class="table-options-container">
      <el-button class="table-option" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        Add
      </el-button>
      <el-upload
        class="table-option"
        style="margin-left: 10px;"
        action=""
        :on-change="handleImportFile"
        :multiple="false"
        :limit="1"
        :auto-upload="false"
      >
        <el-button style="margin-left: 10px;" type="primary" icon="el-icon-download">
          Import
        </el-button>
      </el-upload>
    </div>
    <el-table
      v-loading="listLoading"
      :data="users"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="Name">
        <template slot-scope="scope">
          {{ scope.row.firstname + " " + scope.row.lastname }}
        </template>
      </el-table-column>
      <el-table-column label="email" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="phone" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleMoreDetails(row)">
            More Details
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="deleteData(row)">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <customer-dialog v-model="dialogFormVisible" :temp="temp" :dialogStatus="dialogStatus" @setDialogStatus="setDialogStatusValue" @updateTable="getAllUsers" @setDialogFormVisible="setDialogFormVisible" />
  </div>
</template>

<script>
import { getAllCustomers, createNewCustomer, editCustomer, deleteCustomer} from '/src/services/CustomerService'
import xlsx from 'xlsx'
import customerDialog from './dialog/dialog.vue'

export default {
  components: {
    'customer-dialog': customerDialog
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      users: [],
      course: {
        id_course: '',
        name: ''
      },
      tabPosition: 'left',
      dialogStatus: 'read',
      dialogFormVisible: false,
      temp: {
        idCustomer: '',
        firstname: '',
        lastName: '',
        phone: '',
        email: '',
        linkedin: '',
        company: '',
        position: ''
      },
      listLoading: false
    }
  },
  mounted() {
    this.getAllUsers()
  },
  methods: {
    setDialogStatusValue(dialogStatus) {
      this.dialogStatus = dialogStatus
    },
    setDialogFormVisible(dialogFormVisible) {
      this.dialogFormVisible = dialogFormVisible
    },
    childShowFun() {
      this.dialogFormVisible = false
    },
    getAllUsers() {
      getAllCustomers().then(response => {
        console.log('UPDATING CUSTOMERS:', response)
        if (response.customers != null) {
          this.users = response.customers
          this.numberOfUsers = this.users.length
        } else {
          this.users = []
          this.numberOfUsers = 0
        }
        console.log(this.users)
      })
    },
    resetTemp() {
      this.temp = {
        idCustomer: '',
        firstname: '',
        lastname: '',
        phone: '',
        email: '',
        linkedin: '',
        company: '',
        position: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
    },
    handleMoreDetails(row) {
      this.temp = Object.assign({}, row)
      this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'read'
      this.dialogFormVisible = true
      console.log('MORE DETAILS:', this.temp)
    },
    changeDialogMode(dialogMode) {
      this.dialogStatus = dialogMode
      this.$nextTick(() => {
        //this.$refs['dataForm'].clearValidate()
      })
    },
    editData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          editCustomer(this.temp).then(() => {
            console.log(this.temp)
            this.getAllUsers()
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Edited Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    deleteData(row) {
      console.log('YUPPPP')
      console.log(row)
      this.temp = Object.assign({}, row)
      deleteCustomer(this.temp.idCustomer).then(() => {
        this.getAllUsers()
        this.$notify({
          title: 'Success',
          message: 'Deleted Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    async handleImportFile(e) {
      const file = e.raw
      console.log(file)
      if (!/\.(xls|xlsx)$/.test(file.name.toLowerCase())) {
        this.$notify({
          title: 'Error',
          message: 'The upload format is incorrect. Please upload xls or xlsx format',
          type: 'error',
          duration: 2000
        })
        return
      }

      const readUploadedFileAsText = (inputFile) => {
        const fileReader = new FileReader()

        return new Promise((resolve) => {
          fileReader.onload = async ev => {
            try {
              const data = ev.target.result
              const XLSX = xlsx
              const excellist = []
              const workbook = XLSX.read(data, {
                type: 'binary'
              })
              const wsname = workbook.SheetNames[0] // Take the first sheet，wb.SheetNames[0] :Take the name of the first sheet in the sheets
              const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]) // Generate JSON table content，wb.Sheets[Sheet名]    Get the data of the first sheet
              // Clear received data
              // Edit data
              for (var i = 0; i < ws.length; i++) {
                this.resetTemp()
                this.temp.firstname = ws[i].firstname
                this.temp.lastname = ws[i].lastname
                this.temp.phone = ws[i].phone
                this.temp.email = ws[i].email
                this.temp.linkedin = ws[i].linkedin
                this.temp.company = ws[i].company
                this.temp.position = ws[i].position
                excellist.push(this.temp)
              }
              resolve(excellist)
              // At this point, you get an array containing objects that need to be processed
            } catch (e) {
              console.log('Read failure', e)
              return alert('Read failure!')
            }
          }
          fileReader.readAsBinaryString(inputFile)
        })
      }

      const registerCostumers = (customer) => {
        return new Promise((resolve) => {
          resolve(createNewCustomer(customer))
        })
      }

      this.listLoading = true

      const fileContents = await readUploadedFileAsText(file)
      console.log('START')
      for (var i = 0; i < fileContents.length; i++) {
        console.log('EXECUTING')
        var response = await registerCostumers(fileContents[i])
      }
      console.log('DONE')
      this.getAllUsers()
      this.listLoading = false
      this.$notify({
        title: 'Success',
        message: fileContents.length + ' Customers imported successfully ',
        type: 'success',
        duration: 2000
      })

      // fileReader.onloadend = x =>
      //   {
      //       console.log(excellist);
      //       console.log(Object.keys(excellist).length);
      //       for (var i = 0; i < excellist.length; i++) {
      //         console.log("EXECUTING");
      //         createNewCustomer(excellist[i]);
      //       }
      //       console.log("DONE");
      //       this.getAllUsers();
      //       this.listLoading= false;
      //       //here we call some other functions which most likely don't cause any problems
      //   }

      // this.listLoading= false;
      // this.getAllUsers();

      // this.$notify({
      //     title: 'Success',
      //     message: 'Import Successfully Done',
      //     type: 'success',
      //     duration: 2000
      //   });
    }
  }
}
</script>
