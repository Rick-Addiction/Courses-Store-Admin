<template>
  <div class="app-container">
    <div class="table-options-container">
      <el-button class="table-option" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        Add
      </el-button>
      <el-upload
        class="table-option"
        style="margin-left: 10px;"
        action=""
        :on-change="handleImportFile"
        :multiple="false"
        :limit="1"
        :auto-upload="false"
      >
        <el-button style="margin-left: 10px;" type="primary" icon="el-icon-download">
          Import
        </el-button>
      </el-upload>
    </div>
    <el-table
      v-loading="listLoading"
      :data="users"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="Name">
        <template slot-scope="scope">
          {{ scope.row.firstname + " " + scope.row.lastname }}
        </template>
      </el-table-column>
      <el-table-column label="email" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="phone" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleMoreDetails(row)">
            More Details
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="deleteData(row)">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog center width="90%" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">

      <el-tabs v-model="activeName">
        <el-tab-pane label="Customer" name="Customer">
          <el-form ref="dataForm" :rules="rules" :model="temp">
            <div class="dialog-line">
              <label class="dialog-label" for="firstname">Firstname</label>
              <div v-if="dialogStatus==='read'" class="dialog-input" placeholder="Firstname">{{ temp.firstname }}</div>
              <el-input v-else v-model="temp.firstname" class="dialog-input" placeholder="Firstname" />

              <label class="dialog-label" for="lastname">LastName</label>
              <div v-if="dialogStatus==='read'" class="dialog-input">{{ temp.lastname }}</div>
              <el-input v-else v-model="temp.lastname" class="dialog-input" placeholder="LastName" />
            </div>
            <div class="dialog-line">
              <label class="dialog-label" for="phone">Phone</label>
              <div v-if="dialogStatus==='read'" class="dialog-input">{{ temp.phone }}</div>
              <el-input v-else v-model="temp.phone" class="dialog-input" placeholder="Phone" />

              <label class="dialog-label" for="email">Email</label>
              <div v-if="dialogStatus==='read'" class="dialog-input">{{ temp.email }}</div>
              <el-input v-else v-model="temp.email" class="dialog-input" placeholder="Email" />
            </div>
            <div class="dialog-line">
              <label class="dialog-label" for="linkedin">LinkedIn</label>
              <div v-if="dialogStatus==='read'" class="dialog-input">{{ temp.linkedin }}</div>
              <el-input v-else v-model="temp.linkedin" class="dialog-input" placeholder="LinkedIn" />
            </div>
            <div class="dialog-line">
              <label class="dialog-label" for="company">Company</label>
              <div v-if="dialogStatus==='read'" class="dialog-input">{{ temp.company }}</div>
              <el-input v-else v-model="temp.company" class="dialog-input" placeholder="Company" />

              <label class="dialog-label" for="position">Position</label>
              <div v-if="dialogStatus==='read'" class="dialog-input">{{ temp.position }}</div>
              <el-input v-else v-model="temp.position" class="dialog-input" placeholder="Position" />
            </div>
          </el-form>
          <div class="dialog-footer">
            <el-button v-if="dialogStatus==='read'" @click="changeDialogMode('edit')">
              Edit
            </el-button>
            <el-button v-if="dialogStatus!=='edit' || dialogStatus!=='create'" @click="dialogFormVisible = false">
              Cancel
            </el-button>
            <el-button v-if="dialogStatus=='edit'" type="primary" @click="editData()">
              Confirm
            </el-button>
            <el-button v-if="dialogStatus=='create'" type="primary" @click="createData()">
              Create
            </el-button>
          </div>
        </el-tab-pane>
        <el-tab-pane v-if="dialogStatus==='edit' || dialogStatus==='read'" label="Courses" name="Courses">
          <el-tabs v-model="activeName2" tab-position="left" style="height: 600px;" @tab-click="handleClick">
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
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </div>
</template>

<script>
import { getAllCustomers, createNewCustomer, editCustomer, deleteCustomer,
  AddAcquiredCoursesByCustomer, AddDesiredCoursesByCustomer, getAcquiredCoursesByCustomer, getDesiredCoursesByCustomer } from '/src/services/CustomerService'
import xlsx from 'xlsx'

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
      course: {
        id_course: '',
        name: ''
      },
      coursesRelatedToCustomer: {},
      newAcquisition: {},
      tabPosition: 'left',
      activeName: 'Customer',
      activeName2: 'Acquired',
      dialogStatus: '',
      dialogFormVisible: false,
      options: [],
      temp: {
        idCustomer: '',
        firstname: '',
        lastName: '',
        phone: '',
        email: '',
        linkedin: '',
        company: '',
        position: ''
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
        console.log('UPDATING CUSTOMERS:', response)
        if (response.customers != null) {
          this.users = response.customers
          this.numberOfUsers = this.users.length
        } else {
          this.users = []
          this.numberOfUsers = 0
        }
        console.log(this.users)
      })
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
    },
    resetTemp() {
      this.temp = {
        idCustomer: '',
        firstname: '',
        lastname: '',
        phone: '',
        email: '',
        linkedin: '',
        company: '',
        position: ''
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
    handleMoreDetails(row) {
      this.temp = Object.assign({}, row) // copy obj
      console.log('temp: ', this.temp)
      this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'read'
      this.dialogFormVisible = true

      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleClick(tab, event) {
      if (tab.label === 'Acquired') {
        console.log('UpdateAcquiredCourseByCustomer')
        this.UpdateAcquiredCourseByCustomer()
      } else if (tab.label === 'Desired') {
        console.log('UpdateDesiredCourseByCustomer')
        this.UpdateDesiredCourseByCustomer()
      }
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
    AddAcquiredCourseByCustomer() {
      this.newAcquisition = {
        id_course: this.course.id_course,
        acquisition_date: '18/04/2021',
        value_paid: 123
      }

      AddAcquiredCoursesByCustomer(this.temp.idCustomer, this.newAcquisition).then(() => {
        this.UpdateAcquiredCourseByCustomer()
      })

      this.$nextTick(() => {
        this.course.id_course = ''
        this.course.name = ''
        this.$refs['dataForm'].clearValidate()
      })
    },
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
        this.$refs['dataForm'].clearValidate()
      })
    },
    changeDialogMode(dialogMode) {
      this.dialogStatus = dialogMode
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    editData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          editCustomer(this.temp).then(() => {
            console.log(this.temp)
            this.getAllUsers()
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Edited Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    createData() {
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
    deleteData(row) {
      console.log('YUPPPP')
      console.log(row)
      this.temp = Object.assign({}, row)
      deleteCustomer(this.temp.idCustomer).then(() => {
        this.getAllUsers()
        this.$notify({
          title: 'Success',
          message: 'Deleted Successfully',
          type: 'success',
          duration: 2000
        })
      })
    },
    async handleImportFile(e) {
      const file = e.raw
      console.log(file)
      if (!/\.(xls|xlsx)$/.test(file.name.toLowerCase())) {
        this.$notify({
          title: 'Error',
          message: 'The upload format is incorrect. Please upload xls or xlsx format',
          type: 'error',
          duration: 2000
        })
        return
      }

      const readUploadedFileAsText = (inputFile) => {
        const fileReader = new FileReader()

        return new Promise((resolve) => {
          fileReader.onload = async ev => {
            try {
              const data = ev.target.result
              const XLSX = xlsx
              const excellist = []
              const workbook = XLSX.read(data, {
                type: 'binary'
              })
              const wsname = workbook.SheetNames[0] // Take the first sheet，wb.SheetNames[0] :Take the name of the first sheet in the sheets
              const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]) // Generate JSON table content，wb.Sheets[Sheet名]    Get the data of the first sheet
              // Clear received data
              // Edit data
              for (var i = 0; i < ws.length; i++) {
                this.resetTemp()
                this.temp.firstname = ws[i].firstname
                this.temp.lastname = ws[i].lastname
                this.temp.phone = ws[i].phone
                this.temp.email = ws[i].email
                this.temp.linkedin = ws[i].linkedin
                this.temp.company = ws[i].company
                this.temp.position = ws[i].position
                excellist.push(this.temp)
              }
              resolve(excellist)
              // At this point, you get an array containing objects that need to be processed
            } catch (e) {
              console.log('Read failure', e)
              return alert('Read failure!')
            }
          }
          fileReader.readAsBinaryString(inputFile)
        })
      }

      const registerCostumers = (customer) => {
        return new Promise((resolve) => {
          resolve(createNewCustomer(customer))
        })
      }

      this.listLoading = true

      const fileContents = await readUploadedFileAsText(file)
      console.log('START')
      for (var i = 0; i < fileContents.length; i++) {
        console.log('EXECUTING')
        var response = await registerCostumers(fileContents[i])
      }
      console.log('DONE')
      this.getAllUsers()
      this.listLoading = false
      this.$notify({
        title: 'Success',
        message: fileContents.length + ' Customers imported successfully ',
        type: 'success',
        duration: 2000
      })

      // fileReader.onloadend = x =>
      //   {
      //       console.log(excellist);
      //       console.log(Object.keys(excellist).length);
      //       for (var i = 0; i < excellist.length; i++) {
      //         console.log("EXECUTING");
      //         createNewCustomer(excellist[i]);
      //       }
      //       console.log("DONE");
      //       this.getAllUsers();
      //       this.listLoading= false;
      //       //here we call some other functions which most likely don't cause any problems
      //   }

      // this.listLoading= false;
      // this.getAllUsers();

      // this.$notify({
      //     title: 'Success',
      //     message: 'Import Successfully Done',
      //     type: 'success',
      //     duration: 2000
      //   });
    }
  }
}
</script>
