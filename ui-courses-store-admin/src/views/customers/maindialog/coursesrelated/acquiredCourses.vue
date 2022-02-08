<template>
  <div>
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
      >
        <el-option
          v-for="item in options"
          :key="item.id_course"
          :label="item.name"
          :value="item"
        />
      </el-select>
    </div>
    <el-form ref="dataForm" :model="newAcquisition">
      <div class="dialog-line">
        <label class="dialog-label" for="acquisitionDate">Acquisition Date</label>
        <el-form-item prop="firstname" class="dialog-input">
          <el-input v-model="newAcquisition.acquisitionDate" placeholder="Acquisition Date" />
        </el-form-item>
        <label class="dialog-label" for="valuePaid">Value Paid</label>
        <el-input v-model="newAcquisition.valuePaid" class="dialog-input" placeholder="Value Paid" />
      </div>
    </el-form>
    <el-button @click="AddAcquiredCourseByCustomer()">
      Add
    </el-button>
    <el-table
      v-loading="tableLoading"
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
  </div>
</template>

<script>
import { addAcquiredCoursesByCustomer, getAcquiredCoursesByCustomer } from '/src/services/CustomerService'

export default {
  props: {
    currentCustomer: {
      type: Object,
      required: true
    },
    update: {
      type: Boolean,
      default: false
    }

  },
  data() {
    return {
      coursesRelatedToCustomer: {},
      course: {
        id_course: '',
        name: ''
      },
      options: [],
      newAcquisition: {
        idCourse: '',
        acquisitionDate: '',
        valuePaid: ''
      },
      tableLoading: false
    }
  },
  watch: {
    update: function(newVal, oldVal) {
      if (newVal === true) {
        this.UpdateAcquiredCourseByCustomer()
        this.$emit('updateComplete')
      }
    },
    currentCustomer: function() {
      this.UpdateAcquiredCourseByCustomer()
    }
  },
  methods: {
    ResetNewAcquisition() {
      this.newAcquisition = {
          idCourse: '',
          acquisitionDate: '',
          valuePaid: ''
        }
    },
    AddAcquiredCourseByCustomer() {
      this.newAcquisition.idCourse = this.course.id_course

      addAcquiredCoursesByCustomer(this.currentCustomer.idCustomer, this.newAcquisition).then(() => {
        this.$notify({
              title: 'Success',
              message: 'Acquired course added Successfully',
              type: 'success',
              duration: 2000
            })
        this.UpdateAcquiredCourseByCustomer()
        this.ResetNewAcquisition()
      })

      this.$nextTick(() => {
        this.course.id_course = ''
        this.course.name = ''
      })
    },
    UpdateAcquiredCourseByCustomer() {
      this.options=[]
      console.log('UpdateAcquiredCourseByCustomer')
      this.tableLoading=true
      getAcquiredCoursesByCustomer(this.currentCustomer.idCustomer).then(response => {
        if (response.not_acquired_courses != null) {
          this.coursesRelatedToCustomer.not_acquired_courses = response.not_acquired_courses.map(item => {
            return { id_course: `${item.id_course}`, name: `${item.name}` }
          })
        }
        this.coursesRelatedToCustomer.acquired_courses = response.acquired_courses
        this.coursesRelatedToCustomer.not_acquired_courses = response.not_acquired_courses
        console.log('courses: ', this.coursesRelatedToCustomer)
        console.log('options: ', this.options)
        this.tableLoading = false
      })
    },
    handleClick() {
      console.log('UpdateAcquiredCourseByCustomer')
      this.UpdateAcquiredCourseByCustomer()
    },
    searchCoursesAvailable(query) {
      if (query !== '') {
        this.listLoading = true
        this.options = []
        setTimeout(() => {
          this.listLoading = false
          this.options = this.coursesRelatedToCustomer.not_acquired_courses.filter(item => {
            console.log('query:', query)
            return item.name.toLowerCase()
              .indexOf(query.toLowerCase()) > -1
          })
        }, 200)
      }
    }
  }
}

</script>
