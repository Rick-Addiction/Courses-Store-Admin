<template>
<el-tabs v-model="activeName">
  <div v-show="dialogStatus==='create' || dialogStatus==='edit'">
  <el-tab-pane label="Customer" name="Customer" >
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
  </div>
  <div v-show="dialogStatus==='edit'">
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
      </div>
</el-tabs>
</template>

<script>
export default {
  props: {
    name: { type: String, default: '' },
    // Here is the trick
    currentName: String
  },
  data() {
    return {
      users: [],
      activeName: 'Customer',
      dialogStatus: 'create',
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
  }
}
</script>