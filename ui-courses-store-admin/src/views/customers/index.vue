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
      :data="customers"
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
    <main-dialog
      v-model="dialogFormVisible"
      :is-visible="dialogFormVisible"
      :current-customer="currentCustomer"
      :dialog-status="dialogStatus"
      @setDialogStatus="setDialogStatusValue"
      @refreshCustomersTable="refreshCustomersTable"
      @setDialogFormVisible="setDialogFormVisible"
    />
  </div>
</template>

<script>
import { getAllCustomers, createNewCustomer, deleteCustomer } from '/src/services/CustomerService'
import xlsx from 'xlsx'
import mainDialog from './maindialog/maindialog.vue'

export default {
  components: {
    'main-dialog': mainDialog
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
      customers: [],
      course: {
        id_course: '',
        name: ''
      },
      tabPosition: 'left',
      dialogStatus: 'read',
      dialogFormVisible: false,
      currentCustomer: {
        idCustomer: '',
        firstname: '',
        lastname: '',
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
    this.refreshCustomersTable()
  },
  methods: {
    setDialogStatusValue(dialogStatus) {
      this.dialogStatus = dialogStatus
    },
    setDialogFormVisible(dialogFormVisible) {
      this.dialogFormVisible = dialogFormVisible
    },
    refreshCustomersTable() {
      this.listLoading = true
      getAllCustomers().then(response => {
        console.log('UPDATING CUSTOMERS:', response)
        this.resetTemp()
        if (response.customers != null) {
          this.customers = response.customers
          this.numberOfCustomers = this.customers.length
        } else {
          this.customers = []
          this.numberOfCustomers = 0
        }
        console.log(this.customers)
        this.listLoading = false
      })
    },
    resetTemp() {
      this.currentCustomer = {
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
      this.currentCustomer = Object.assign({}, row)
      this.dialogStatus = 'read'
      this.dialogFormVisible = true
    },
    changeDialogMode(dialogMode) {
      this.dialogStatus = dialogMode
    },
    deleteData(row) {
      console.log(row)
      this.currentCustomer = Object.assign({}, row)
      deleteCustomer(this.currentCustomer.idCustomer).then(() => {
        this.refreshCustomersTable()
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
                this.currentCustomer.firstname = ws[i].firstname
                this.currentCustomer.lastname = ws[i].lastname
                this.currentCustomer.phone = ws[i].phone
                this.currentCustomer.email = ws[i].email
                this.currentCustomer.linkedin = ws[i].linkedin
                this.currentCustomer.company = ws[i].company
                this.currentCustomer.position = ws[i].position
                excellist.push(this.currentCustomer)
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
      this.getAllCustomers()
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
