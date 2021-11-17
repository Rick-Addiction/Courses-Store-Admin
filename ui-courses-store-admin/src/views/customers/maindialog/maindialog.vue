<template>
  <div>
    <el-dialog center width="90%" :title="textMap[dialogStatus]" :visible.sync="localVisible">
      <el-tabs v-model="activeName">
        <el-tab-pane label="Customer" name="Customer">
          <component
            :is="customerTabType[dialogStatus].component"
            v-bind="customerTabType[dialogStatus].props"
            v-on="customerTabType[dialogStatus].events"
          />
        </el-tab-pane>
        <el-tab-pane v-if="dialogStatus==='edit' || dialogStatus==='read'" label="Courses" name="Courses">
          <courses-related-tab
            :current-customer="currentCustomer"
          />
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </div>
</template>

<script>
import createCustomerTab from './customertabs/createCustomerTab.vue'
import readCustomerTab from './customertabs/readCustomerTab.vue'
import editCustomerTab from './customertabs/editCustomerTab.vue'
import coursesRelatedTab from './coursesrelated/coursesrelated.vue'

export default {
  components: {
    'create-customer-tab': createCustomerTab,
    'read-customer-tab': readCustomerTab,
    'edit-customer-tab': editCustomerTab,
    'courses-related-tab': coursesRelatedTab
  },
  props: {
    isVisible: Boolean,
    currentCustomer: {
      type: Object,
      default: null
    },
    dialogStatus: {
      type: String,
      default: 'read'
    }
  },
  data() {
    return {
      comp: readCustomerTab,
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      customerTabType: {
        read: {
          component: readCustomerTab,
          props: { currentCustomer: this.currentCustomer },
          events: { changeToEditMode: () => this.changeToEditMode() }
        },
        edit: {
          component: editCustomerTab,
          props: { customerToEdit: this.currentCustomer },
          events: { closeDialog: (successExecution) => this.closeDialog(successExecution) }
        },
        create: {
          component: createCustomerTab,
          events: { closeDialog: (successExecution) => this.closeDialog(successExecution) }
        }
      },
      activeName: 'Customer'
    }
  },
  computed: {
    localVisible: {
      get() {
        return this.isVisible
      },
      set(isVisible) {
        this.$emit('input', isVisible)
      }
    },
    dialogMode: {
      get() {
        return this.dialogStatus
      },
      set(dialogStatus) {
        this.$emit('input', dialogStatus)
      }
    }
  },
  watch: {
    currentCustomer: function(newVal) {
      this.customerTabType = {
        read: {
          component: readCustomerTab,
          props: { currentCustomer: newVal },
          events: { changeToEditMode: () => this.changeToEditMode() }
        },
        edit: {
          component: editCustomerTab,
          props: { customerToEdit: newVal },
          events: { closeDialog: (successExecution) => this.closeDialog(successExecution) }
        },
        create: {
          component: createCustomerTab,
          events: { closeDialog: (successExecution) => this.closeDialog(successExecution) }
        }
      }
    }
  },
  methods: {
    changeToEditMode() {
      this.$emit('setDialogStatus', 'edit')
    },
    closeDialog(successExecution) {
      this.$emit('setDialogFormVisible', false)
      if (successExecution) {
        this.$emit('refreshCustomersTable')
      }
    }
  }
}
</script>
