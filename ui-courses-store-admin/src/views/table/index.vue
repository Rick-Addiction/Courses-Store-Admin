<template>
  <div class="app-container">
    <div class="table-options-container">
      <el-button class="table-option" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        Add
      </el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="users"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="b" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="idCustomer">
        <template slot-scope="scope">
          {{ scope.row.idCustomer }}
        </template>
      </el-table-column>
      <el-table-column label="firstName" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.firstName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="lastName" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.lastName }}
        </template>
      </el-table-column>
      <el-table-column label="doctype" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.docType }}
        </template>
      </el-table-column>
      <el-table-column label="docnumber" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.docNumber }}
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleMoreDetails(row)">
            More Details
          </el-button>          
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog center width="90%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">

    <el-tabs v-model="activeName">
      <el-tab-pane label="Customer" name="Customer">
      <el-form ref="dataForm" :rules="rules" :model="temp" >
        <div class="dialog-line"> 
          <label class="dialog-label" for="firstName">Firstname</label>
          <el-input class="dialog-input" v-model="temp.firstName" :disabled="dialogStatus!=='edit'" placeholder="Firstname" />        
  
          <label class="dialog-label" for="Lastname">LastName</label>
          <el-input class="dialog-input" v-model="temp.lastName" :disabled="dialogStatus!=='edit'" placeholder="lastname" />
        </div>
      </el-form>
        <div class="dialog-footer">
          <el-button :disabled="dialogStatus==='edit'" @click="changeDialogMode('edit')">
            Edit
          </el-button>
          <el-button :disabled="dialogStatus!=='edit'" @click="dialogFormVisible = false">
            Cancel
          </el-button>
          <el-button :disabled="dialogStatus!=='edit'" type="primary" @click="editData()">
            Confirm
          </el-button>
        </div>
      </el-tab-pane>
      <el-tab-pane label="Courses" name="Courses">
        <el-table
        :data="users"
        style="width: 100%"
        height="50%">
          <el-table-column
            fixed
            prop="firstName"
            label="Date"
            width="150">
          </el-table-column>
          <el-table-column
            prop="name"
            label="Name"
            width="120">
          </el-table-column>
          <el-table-column
            prop="state"
            label="State"
            width="120">
          </el-table-column>
          <el-table-column
            prop="city"
            label="City"
            width="120">
          </el-table-column>
          <el-table-column
            prop="address"
            label="Address"
            width="300">
          </el-table-column>
          <el-table-column
            prop="zip"
            label="Zip"
            width="120">
          </el-table-column>
      </el-table>
      </el-tab-pane>
      </el-tabs>
    </el-dialog>  
  </div>
</template>

<script>
import { getAllCustomers, createNewCustomer, editCustomer } from '/src/services/TestService'

export default {
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
      activeName: 'Customer',
      dialogStatus: '',
      dialogFormVisible: false,
      temp: {
        idCustomer: '',
        firstName: '',
        lastName: '',
        docType: '',
        docNumber: ''
      },
      listLoading: false,
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.fetchData()
  },  
  mounted() {
    this.getAllUsers()
  },
  methods: {
    getAllUsers() {
      getAllCustomers().then(response => {
        console.log(response)
        this.users = response
        this.numberOfUsers = this.users.length
      })
    },
    resetTemp() {
      this.temp = {
        idCustomer: '',
        firstName: '',
        lastName: '',
        docType: '',
        docNumber: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleMoreDetails(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'read'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    changeDialogMode(dialogMode) {
      this.dialogStatus = dialogMode
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
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
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    createData() {
      this.temp.idCustomer = 'aaaaaa'
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          createNewCustomer(this.temp).then(() => {
            console.log(this.temp)
            this.getAllUsers()
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
  }
}
</script>
