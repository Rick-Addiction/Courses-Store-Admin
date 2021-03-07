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
    </el-table>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="90px" style="width: 400px; margin-left:50px;">
        <el-form-item label="firstname">
          <el-input v-model="temp.firstName" type="textarea" placeholder="firstname" />
        </el-form-item>
        <el-form-item label="lastname">
          <el-input v-model="temp.lastName" type="textarea" placeholder="lastname" />
        </el-form-item>
        <el-form-item label="doctype">
          <el-input v-model="temp.docType" type="textarea" placeholder="doctype" />
        </el-form-item>
        <el-form-item label="docnumber">
          <el-input v-model="temp.docNumber" type="textarea" placeholder="docnumber" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="createData()">
          Confirm
        </el-button>
      </div>
    </el-dialog>  
  </div>
</template>

<script>
import { getAllCustomers, createNewCustomer } from '/src/services/TestService'

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
