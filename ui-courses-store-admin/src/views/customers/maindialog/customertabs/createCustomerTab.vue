<template>
  <div>
    <el-form ref="dataForm" :rules="rules" :model="currentCustomer">
      <div class="dialog-line">
        <label class="dialog-label" for="firstname">Firstname</label>
        <el-form-item prop="firstname" class="dialog-input">
          <el-input v-model="currentCustomer.firstname" placeholder="Firstname" />
        </el-form-item>

        <label class="dialog-label" for="lastname">LastName</label>
        <el-input v-model="currentCustomer.lastname" class="dialog-input" placeholder="LastName" />
      </div>
      <div class="dialog-line">
        <label class="dialog-label" for="phone">Phone</label>
        <el-input v-model="currentCustomer.phone" class="dialog-input" placeholder="Phone" />

        <label class="dialog-label" for="email">Email</label>
        <el-input v-model="currentCustomer.email" class="dialog-input" placeholder="Email" />
      </div>
      <div class="dialog-line">
        <label class="dialog-label" for="linkedin">LinkedIn</label>
        <el-input v-model="currentCustomer.linkedin" class="dialog-input" placeholder="LinkedIn" />
      </div>
      <div class="dialog-line">
        <label class="dialog-label" for="company">Company</label>
        <el-input v-model="currentCustomer.company" class="dialog-input" placeholder="Company" />

        <label class="dialog-label" for="position">Position</label>
        <el-input v-model="currentCustomer.position" class="dialog-input" placeholder="Position" />
      </div>
    </el-form>
    <div class="dialog-footer">
      <el-button @click="$emit('closeDialog', false)">
        Cancel
      </el-button>
      <el-button @click="createCustomer()">
        Create
      </el-button>
    </div>
  </div>
</template>

<script>
import { createNewCustomer } from '/src/services/CustomerService'

export default {
  data() {
    return {
      visibleForm: true,
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
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      rules: {
        firstname: [
          // trigger:'blur' -> trigger when focus is lost
          { required: true, message: 'Please enter login name', trigger: 'blur' },
          { min: 3, max: 10, message: 'Length is 3 to 10 characters', trigger: 'blur' }
        ],
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      },
      activeName: 'Customer',
      activeName2: 'Acquired'
    }
  },
  methods: {
    createCustomer() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          createNewCustomer(this.currentCustomer).then(() => {
            this.$emit('closeDialog', true)
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    }
  }
}

</script>

