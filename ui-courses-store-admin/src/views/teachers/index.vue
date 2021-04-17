<template>
  <div class="app-container">
    <div class="table-options-container">
      <el-button class="table-option" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        Add
      </el-button>
      <el-upload class="table-option" style="margin-left: 10px;"
      action=""
       :on-change="handleImportFile"
       :multiple="false" 
       :limit="1"
       :auto-upload = "false">
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
  <el-tab-pane label="Teacher" name="Teacher">
      <el-form ref="dataForm" :rules="rules" :model="temp" >
        <div class="dialog-line"> 
          <label class="dialog-label" for="name">Name</label>
          <div class="dialog-input" v-if="dialogStatus==='read'" placeholder="Name">{{temp.name}}</div>
          <el-input v-else class="dialog-input" v-model="temp.name"  placeholder="Name" />
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
  <el-tab-pane label="Courses" name="Courses" v-if="dialogStatus==='edit' || dialogStatus==='read'">
        <el-table
        :data="users"
        style="width: 100%"
        height="50%">
          <el-table-column
            fixed
            prop="firstname"
            label="Date"
            width="150">
          </el-table-column>
          <el-table-column
            prop="name"
            label="Name"
            width="120">
          </el-table-column>
          <el-table-column
            prop="state"
            label="State"
            width="120">
          </el-table-column>
          <el-table-column
            prop="city"
            label="City"
            width="120">
          </el-table-column>
          <el-table-column
            prop="address"
            label="Address"
            width="300">
          </el-table-column>
          <el-table-column
            prop="zip"
            label="Zip"
            width="120">
          </el-table-column>
      </el-table>
      </el-tab-pane>
    </el-tabs>   
    </el-dialog>  
  </div>
</template>

<script>
import { getAllTeachers, createNewTeacher, editTeacher, deleteTeacher } from '/src/services/TeacherService'
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
      activeName: 'Teacher',
      dialogStatus: '',
      dialogFormVisible: false,
      temp: {
        idTeacher: '',
        name: ''
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
      getAllTeachers().then(response => {
        console.log("UPDATING Teachers:",response)
        if(response.teachers != null){
          this.users = response.teachers
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
        idTeacher: '',
        name: ''
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
      this.temp.timestamp = new Date(this.temp.timestamp)
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
          editTeacher(this.temp).then(() => {
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
          createNewTeacher(this.temp).then(() => {
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
    deleteData(row){
      console.log('YUPPPP')
      console.log(row)
      this.temp = Object.assign({}, row)
      deleteTeacher(this.temp.idTeacher).then(() =>{        
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
      const file = e.raw;
      console.log(file);
      if (!/\.(xls|xlsx)$/.test(file.name.toLowerCase())) {
        this.$notify({
              title: 'Error',
              message: 'The upload format is incorrect. Please upload xls or xlsx format',
              type: 'error',
              duration: 2000
            })
        return ;
      }
            
      const readUploadedFileAsText = (inputFile) => {
       const fileReader = new FileReader();

        return new Promise((resolve) => {
          fileReader.onload = async ev => {
        try {
          const data = ev.target.result;
          const XLSX = xlsx;
          const excellist = []; 
          const workbook = XLSX.read(data, {
            type: "binary"
          });
          const wsname = workbook.SheetNames[0]; // Take the first sheet，wb.SheetNames[0] :Take the name of the first sheet in the sheets
          const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]); // Generate JSON table content，wb.Sheets[Sheet名]    Get the data of the first sheet
           // Clear received data
          // Edit data
          for (var i = 0; i < ws.length; i++) {
            this.resetTemp();
            this.temp.name= ws[i].name;
            excellist.push(this.temp);         
          }
          resolve(excellist);
          // At this point, you get an array containing objects that need to be processed
        } catch (e) {
          console.log("Read failure", e);
          return alert("Read failure!");;
        }
      };
        fileReader.readAsBinaryString(inputFile);
        });
      };

      const registerTeachers = (teacher) => {
        return new Promise((resolve) => {
          resolve(createNewTeacher(teacher));
        });
    };

        this.listLoading= true;
        
        const fileContents = await readUploadedFileAsText(file)  
        console.log("START");
        for (var i = 0; i < fileContents.length; i++) {
               console.log("EXECUTING");
               var response = await registerTeachers(fileContents[i])
        }
         console.log("DONE");
        this.getAllUsers();
        this.listLoading= false;
        this.$notify({
              title: 'Success',
              message: fileContents.length + ' Teachers imported successfully ',
              type: 'success',
              duration: 2000
            });

    }
  }
}
</script>
