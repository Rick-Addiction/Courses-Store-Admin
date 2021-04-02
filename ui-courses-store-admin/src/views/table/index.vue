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

    <el-dialog center width="90%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">    
      
  <el-tabs v-model="activeName">  
  <el-tab-pane label="Customer" name="Customer">
      <el-form ref="dataForm" :rules="rules" :model="temp" >
        <div class="dialog-line"> 
          <label class="dialog-label" for="firstname">Firstname</label>
          <div class="dialog-input" v-if="dialogStatus==='read'" placeholder="Firstname">{{temp.firstname}}</div>
          <el-input v-else class="dialog-input" v-model="temp.firstname"  placeholder="Firstname" />        

          <label class="dialog-label" for="lastname">LastName</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.lastname}}</div>
          <el-input v-else class="dialog-input" v-model="temp.lastname" placeholder="LastName" />
        </div>
        <div class="dialog-line"> 
          <label class="dialog-label" for="phone">Phone</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.phone}}</div>
          <el-input v-else class="dialog-input" v-model="temp.phone" placeholder="Phone" />        
  
          <label class="dialog-label" for="email">Email</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.email}}</div>
          <el-input v-else class="dialog-input" v-model="temp.email" placeholder="Email" />
        </div>
        <div class="dialog-line"> 
          <label class="dialog-label" for="linkedin">LinkedIn</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.linkedin}}</div>
          <el-input v-else class="dialog-input" v-model="temp.linkedin" placeholder="LinkedIn" />        
        </div>
        <div class="dialog-line"> 
          <label class="dialog-label" for="company">Company</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.company}}</div>
          <el-input v-else class="dialog-input" v-model="temp.company" placeholder="Company" />
          
          <label class="dialog-label" for="position">Position</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.position}}</div>
          <el-input v-else class="dialog-input" v-model="temp.position" placeholder="Position" />
        </div>
      </el-form>
        <div class="dialog-footer">
          <el-button v-if="dialogStatus==='read'" @click="changeDialogMode('edit')">
            Edit
          </el-button>
          <el-button v-if="dialogStatus!=='edit' || dialogStatus!=='create'" @click="dialogFormVisible = false">
            Cancel
          </el-button>
          <el-button v-if="dialogStatus=='edit'" type="primary" @click="editData()">
            Confirm
          </el-button>
          <el-button v-if="dialogStatus=='create'" type="primary" @click="createData()">
            Create
          </el-button>
        </div>
  </el-tab-pane>
  <el-tab-pane label="Courses" name="Courses" v-if="dialogStatus==='edit' || dialogStatus==='read'">
        <el-table
        :data="users"
        style="width: 100%"
        height="50%">
          <el-table-column
            fixed
            prop="firstname"
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
import { getAllCustomers, createNewCustomer, editCustomer, deleteCustomer } from '/src/services/CustomerService'

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
        firstname: '',
        lastName: '',
        phone: '',
        email: '',
        linkedin:'',
        company:'',
        position:''
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
        if(response.customers != null){
          this.users = response.customers
          this.numberOfUsers = this.users.length
        } 
        else{
          this.users = []
          this.numberOfUsers = 0
        }       
        console.log(users)
      })
    },
    resetTemp() {
      this.temp = {
        idCustomer: '',
        firstname: '',
        lastname: '',
        phone: '',
        email: '',
        linkedin:'',
        company:'',
        position:''
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
              message: 'Edited Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    createData() {
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
    deleteData(row){
      console.log('YUPPPP')
      console.log(row)
      this.temp = Object.assign({}, row)
      deleteCustomer(this.temp.idCustomer).then(() =>{        
            this.getAllUsers()
            this.$notify({
              title: 'Success',
              message: 'Deleted Successfully',
              type: 'success',
              duration: 2000
            })
      })
    }
  }
}
</script>
