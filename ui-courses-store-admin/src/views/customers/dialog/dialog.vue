<template>
  <div>
    <el-dialog center width="90%" :title="textMap[dialogStatus]" :visible.sync="localVisible">
      <el-tabs v-model="activeName">
        <el-tab-pane label="Customer" name="Customer">
          <component
            :is="customerTabType[dialogStatus].component"
            :temp="temp"
            v-on="customerTabType[dialogStatus].events"
          />
        </el-tab-pane>
        <el-tab-pane v-if="dialogStatus==='edit' || dialogStatus==='read'" label="Courses" name="Courses">
          <courses-related-tab
            :temp="temp"
          />
        </el-tab-pane>
        <!--<el-tabs v-model="activeName2" tab-position="left" style="height: 600px;" @tab-click="handleClick">
            <el-tab-pane label="Acquired" name="Acquired">
              <div class="dialog-line">
                <el-select
                  v-model="course"
                  class="dialog-input"
                  value-key="id_course"
                  filterable
                  remote
                  reserve-keyword
                  placeholder="Please enter a keyword"
                  :remote-method="searchCoursesAvailable"
                  :loading="listLoading"
                >
                  <el-option
                    v-for="item in options"
                    :key="item.id_course"
                    :label="item.name"
                    :value="item"
                  />
                </el-select>
                <el-button @click="AddAcquiredCourseByCustomer()">
                  Add
                </el-button>
              </div>
              <el-table
                :data="coursesRelatedToCustomer.acquired_courses"
                style="width: 100%"
              >
                <el-table-column
                  fixed
                  prop="course_name"
                  label="course_name"
                  width="150"
                />
                <el-table-column
                  prop="value_paid"
                  label="value_paid"
                  width="120"
                />
                <el-table-column
                  prop="acquisition_date"
                  label="acquisition_date"
                  width="120"
                />
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="Desired" name="Desired" @tab-click="UpdateDesiredCourseByCustomer">
              <div class="dialog-line">
                <el-select
                  v-model="course"
                  class="dialog-input"
                  value-key="id_course"
                  filterable
                  remote
                  reserve-keyword
                  placeholder="Please enter a keyword"
                  :remote-method="searchCoursesAvailable"
                  :loading="listLoading"
                >
                  <el-option
                    v-for="item in options"
                    :key="item.id_course"
                    :label="item.name"
                    :value="item"
                  />
                </el-select>
                <el-button @click="AddDesiredCourseByCustomer()">
                  Add
                </el-button>
              </div>
              <el-table
                :data="coursesRelatedToCustomer.desired_courses"
                style="width: 100%"
              >
                <el-table-column
                  fixed
                  prop="course_name"
                  label="course_name"
                  width="150"
                />
                <el-table-column
                  prop="desire_description"
                  label="desire_description"
                  width="120"
                />
                <el-table-column
                  prop="desire_date"
                  label="desire_date"
                  width="120"
                />
              </el-table>
            </el-tab-pane>
          </el-tabs>
        </el-tab-pane> -->
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
    value: Boolean,
    temp: {
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
        read: { component: readCustomerTab, events: { changeToEditMode: () => this.changeToEditMode() }},
        edit: { component: editCustomerTab, events: { cancelEdit: () => this.cancelDialog() }},
        create: { component: createCustomerTab, events: { cancelCreate: () => this.cancelEdit() }}
      },
      activeName: 'Customer'
    }
  },
  computed: {
    localVisible: {
      get() {
        return this.value
      },
      set(value) {
        this.$emit('input', value)
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
  methods: {
    changeToEditMode() {
      this.$emit('setDialogStatus', 'edit')
    },
    cancelDialog() {
      this.$emit('setDialogFormVisible', false)
    }
  }
}
</script>
