<template>
  <div>
    <el-form ref="dataForm" :rules="rules" :model="temp">
      <div class="dialog-line">
        <label class="dialog-label" for="firstname">Firstname</label>
        <div class="dialog-input" placeholder="Firstname">{{ temp.firstname }}</div>

        <label class="dialog-label" for="lastname">LastName</label>
        <div class="dialog-input">{{ temp.lastname }}</div>
      </div>
      <div class="dialog-line">
        <label class="dialog-label" for="phone">Phone</label>
        <div class="dialog-input">{{ temp.phone }}</div>

        <label class="dialog-label" for="email">Email</label>
        <div class="dialog-input">{{ temp.email }}</div>
      </div>
      <div class="dialog-line">
        <label class="dialog-label" for="linkedin">LinkedIn</label>
        <div class="dialog-input">{{ temp.linkedin }}</div>
      </div>
      <div class="dialog-line">
        <label class="dialog-label" for="company">Company</label>
        <div class="dialog-input">{{ temp.company }}</div>

        <label class="dialog-label" for="position">Position</label>
        <div class="dialog-input">{{ temp.position }}</div>
      </div>
    </el-form>
    <div class="dialog-footer">
      <el-button @click="changeToEditMode()">
        Edit
      </el-button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    temp: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      visibleForm: true,
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
    changeToEditMode() {
      this.$emit('changeToEditMode')
    }
  }
}

</script>

