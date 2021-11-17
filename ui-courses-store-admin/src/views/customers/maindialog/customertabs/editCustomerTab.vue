<template>
  <div>
    <el-form ref="dataForm" :rules="rules" :model="customerToEdit">
      <div class="dialog-line">
        <label class="dialog-label" for="firstname">Firstname</label>
        <el-form-item prop="firstname" class="dialog-input">
          <el-input v-model="customerToEdit.firstname" placeholder="Firstname" />
        </el-form-item>

        <label class="dialog-label" for="lastname">LastName</label>
        <el-input v-model="customerToEdit.lastname" class="dialog-input" placeholder="LastName" />
      </div>
      <div class="dialog-line">
        <label class="dialog-label" for="phone">Phone</label>
        <el-input v-model="customerToEdit.phone" class="dialog-input" placeholder="Phone" />

        <label class="dialog-label" for="email">Email</label>
        <el-input v-model="customerToEdit.email" class="dialog-input" placeholder="Email" />
      </div>
      <div class="dialog-line">
        <label class="dialog-label" for="linkedin">LinkedIn</label>
        <el-input v-model="customerToEdit.linkedin" class="dialog-input" placeholder="LinkedIn" />
      </div>
      <div class="dialog-line">
        <label class="dialog-label" for="company">Company</label>
        <el-input v-model="customerToEdit.company" class="dialog-input" placeholder="Company" />

        <label class="dialog-label" for="position">Position</label>
        <el-input v-model="customerToEdit.position" class="dialog-input" placeholder="Position" />
      </div>
    </el-form>
    <div class="dialog-footer">
      <el-button @click="$emit('closeDialog', false)">
        Cancel
      </el-button>
      <el-button type="primary" @click="editCustomer()">
        Confirm
      </el-button>
    </div>
  </div>
</template>

<script>
import { editCustomer } from '/src/services/CustomerService'

export default {
  props: {
    customerToEdit: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      rules: {
        firstname: [
          // trigger:'blur' -> trigger when focus is lost
          { required: true, message: 'Please enter login name', trigger: 'blur' },
          { min: 3, max: 10, message: 'Length is 3 to 10 characters', trigger: 'blur' }
        ],
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      }
    }
  },
  methods: {
    editCustomer() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          editCustomer(this.customerToEdit).then(() => {
            this.$emit('closeDialog', true)
            this.$notify({
              title: 'Success',
              message: 'Edited Successfully',
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

