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
    </el-table></div>
</template>

<script>
import { AddDesiredCoursesByCustomer, getDesiredCoursesByCustomer } from '/src/services/CustomerService'

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
        this.UpdateDesiredCourseByCustomer()
        this.$emit('updateComplete')
      }
    }
  },
  methods: {
    AddDesiredCourseByCustomer() {
      this.newDesire = {
        id_course: this.course.id_course,
        desire_date: '18/04/2021',
        desire_description: 'DESCRIPTION'
      }

      AddDesiredCoursesByCustomer(this.temp.idCustomer, this.newDesire).then(() => {
        this.UpdateDesiredCourseByCustomer()
      }
      )

      this.$nextTick(() => {
        this.course.id_course = ''
        this.course.name = ''
        // this.$refs['dataForm'].clearValidate()
      })
    },
    UpdateDesiredCourseByCustomer() {
      getDesiredCoursesByCustomer(this.temp.idCustomer).then(response => {
        if (response.not_desired_courses != null) {
          this.coursesRelatedToCustomer.not_desired_courses = response.not_desired_courses.map(item => {
            return { id_course: `${item.id_course}`, name: `${item.name}` }
          })
        }
        this.coursesRelatedToCustomer.desired_courses = response.desired_courses
        this.coursesRelatedToCustomer.not_desired_courses = response.not_desired_courses
        this.options = this.coursesRelatedToCustomer.not_desired_courses
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
