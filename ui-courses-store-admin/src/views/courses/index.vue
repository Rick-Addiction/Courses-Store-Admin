<template>
  <div class="app-container">
    <div class="table-options-container">
      <el-button class="table-option" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        Add
      </el-button>
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
          {{ scope.row.name }}
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
  <el-tab-pane label="Course" name="Course">
      <el-form ref="dataForm" :rules="rules" :model="temp" >
        <div class="dialog-line"> 
          <label class="dialog-label" for="name">Name</label>
          <div class="dialog-input" v-if="dialogStatus==='read'" placeholder="Name">{{temp.name}}</div>
          <el-input v-else class="dialog-input" v-model="temp.name"  placeholder="Name" />

          <label class="dialog-label" for="originalValue">Original Value</label>
          <div class="dialog-input" v-if="dialogStatus==='read'" placeholder="Original Value">{{temp.originalValue}}</div>
          <el-input v-else class="dialog-input" v-model="temp.originalValue"  placeholder="Original Value" />
        </div>
        <div class="dialog-line"> 
          <label class="dialog-label" for="teacher">Teacher</label>
          <div class="dialog-input" v-if="dialogStatus==='read'" placeholder="Teacher Responsible">{{temp.teacher.teacherName}}</div>
          <el-select v-else
            v-model=temp.teacher class="dialog-input"
            filterable
            remote
            reserve-keyword
            placeholder="Please enter a keyword"
            :remote-method="remoteMethod"
            :loading="listLoading">
            <el-option
              v-for="item in options"
              :key="item.idTeacher"
              :label="item.teacherName"
              :value="item">
            </el-option>
          </el-select>
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
    </el-tabs>   
    </el-dialog>  
  </div>
</template>

<script>
import { getAllCourses, createNewCourse, editCourse, deleteCourse } from '/src/services/CourseService'
import { getAllTeachers } from '/src/services/TeacherService'

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
      activeName: 'Course',
      dialogStatus: '',
      dialogFormVisible: false,
      temp: {
        idCourse: '',
        name: '',
        originalValue: '',
        teacher: {}
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
      },
      options: [],
      list: []
    }
  },
  created() {
    this.fetchData()
  },  
  mounted() {
    this.getAllUsers()
  },
  methods: {
    remoteMethod(query) {
        if (query !== '') {
          this.listLoading = true;
          this.options=[];
          setTimeout(() => {
            this.listLoading = false;
            this.options = this.list.filter(item => {
              return item.teacherName.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
            });
          }, 200);
        }
      },
    getAllUsers() {
      getAllCourses().then(response => {
        console.log("UPDATING Courses:",response)
        if(response.courses != null){
          this.users = response.courses
          this.numberOfUsers = this.users.length
        } 
        else{
          this.users = []
          this.numberOfUsers = 0
        }       
        console.log(users)
      })
    },
    resetTemp() {
      this.temp = {
        idCourse: '',        
        name: '',
        originalValue: '',
        teacher: {}
      }
      this.options=[]
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true      
      getAllTeachers().then(response => {
        this.list = response.teachers.map(item => {
        return { idTeacher: `${item.idTeacher}`, teacherName: `${item.name}` }});
      })
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleMoreDetails(row) {
      this.resetTemp()
      getAllTeachers().then(response => {
        this.list = response.teachers.map(item => {
        return { idTeacher: `${item.idTeacher}`, teacherName: `${item.name}` }});
      })
      this.temp= {
        idCourse: Object.assign({}, row).id_course,
        name: Object.assign({}, row).name,
        originalValue: Object.assign({}, row).original_value,
        teacher: {
          idTeacher:Object.assign({}, row).id_teacher_responsible,
          teacherName:Object.assign({}, row).teacher_responsible_name
        }
      }
      this.options.push(this.temp.teacher)
      this.dialogStatus = 'read'
      this.dialogFormVisible = true
      this.$nextTick(() => {
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
          editCourse(this.temp).then(() => {
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
          createNewCourse(this.temp).then(() => {
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
          console.log("Create Data", this.temp)
        }
      })
    },
    deleteData(row){
      this.temp = Object.assign({}, row)
      deleteCourse(this.temp.idCourse).then(() =>{        
            this.getAllUsers()
            this.$notify({
              title: 'Success',
              message: 'Deleted Successfully',
              type: 'success',
              duration: 2000
            })
      })
    },
  }
}
</script>
