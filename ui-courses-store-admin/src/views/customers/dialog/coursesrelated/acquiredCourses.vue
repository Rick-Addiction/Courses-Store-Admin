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
  </div>
</template>

<script>
import { AddAcquiredCoursesByCustomer, getAcquiredCoursesByCustomer } from '/src/services/CustomerService'

export default {
  props: {
    temp: {
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
      options: []
    }
  },
  watch: {
    update: function(newVal, oldVal) {
      if (newVal === true) {
        this.UpdateAcquiredCourseByCustomer()
        this.$emit('updateComplete')
      }
    }
  },
  methods: {
    AddAcquiredCourseByCustomer() {
      var newAcquisition = {
        id_course: this.course.id_course,
        acquisition_date: '18/04/2021',
        value_paid: 123
      }

      AddAcquiredCoursesByCustomer(this.temp.idCustomer, newAcquisition).then(() => {
        this.UpdateAcquiredCourseByCustomer()
      })

      this.$nextTick(() => {
        this.course.id_course = ''
        this.course.name = ''
        // this.$refs['dataForm'].clearValidate()
      })
    },
    UpdateAcquiredCourseByCustomer() {
      console.log('UpdateAcquiredCourseByCustomer')
      getAcquiredCoursesByCustomer(this.temp.idCustomer).then(response => {
        if (response.not_acquired_courses != null) {
          this.coursesRelatedToCustomer.not_acquired_courses = response.not_acquired_courses.map(item => {
            return { id_course: `${item.id_course}`, name: `${item.name}` }
          })
        }
        this.coursesRelatedToCustomer.acquired_courses = response.acquired_courses
        this.coursesRelatedToCustomer.not_acquired_courses = response.not_acquired_courses
        this.options = this.coursesRelatedToCustomer.not_acquired_courses
        console.log('courses: ', this.coursesRelatedToCustomer)
        console.log('options: ', this.options)
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
      } else {
        this.options = []
      }
    }
  }
}

</script>
