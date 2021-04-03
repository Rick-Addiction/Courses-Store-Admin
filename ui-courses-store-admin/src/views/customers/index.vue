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
      <el-form ref="dataForm" :rules="rules" :model="temp" >
        <div class="dialog-line"> 
          <label class="dialog-label" for="firstname">Firstname</label>
          <div class="dialog-input" v-if="dialogStatus==='read'" placeholder="Firstname">{{temp.firstname}}</div>
          <el-input v-else class="dialog-input" v-model="temp.firstname"  placeholder="Firstname" />        

          <label class="dialog-label" for="lastname">LastName</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.lastname}}</div>
          <el-input v-else class="dialog-input" v-model="temp.lastname" placeholder="LastName" />
        </div>
        <div class="dialog-line"> 
          <label class="dialog-label" for="phone">Phone</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.phone}}</div>
          <el-input v-else class="dialog-input" v-model="temp.phone" placeholder="Phone" />        
  
          <label class="dialog-label" for="email">Email</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.email}}</div>
          <el-input v-else class="dialog-input" v-model="temp.email" placeholder="Email" />
        </div>
        <div class="dialog-line"> 
          <label class="dialog-label" for="linkedin">LinkedIn</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.linkedin}}</div>
          <el-input v-else class="dialog-input" v-model="temp.linkedin" placeholder="LinkedIn" />        
        </div>
        <div class="dialog-line"> 
          <label class="dialog-label" for="company">Company</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.company}}</div>
          <el-input v-else class="dialog-input" v-model="temp.company" placeholder="Company" />
          
          <label class="dialog-label" for="position">Position</label>
          <div class="dialog-input" v-if="dialogStatus==='read'">{{temp.position}}</div>
          <el-input v-else class="dialog-input" v-model="temp.position" placeholder="Position" />
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
import { getAllCustomers, createNewCustomer, editCustomer, deleteCustomer } from '/src/services/CustomerService'
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
      activeName: 'Customer',
      dialogStatus: '',
      dialogFormVisible: false,
      temp: {
        idCustomer: '',
        firstname: '',
        lastName: '',
        phone: '',
        email: '',
        linkedin:'',
        company:'',
        position:''
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
        console.log("UPDATING CUSTOMERS:",response)
        if(response.customers != null){
          this.users = response.customers
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
        idCustomer: '',
        firstname: '',
        lastname: '',
        phone: '',
        email: '',
        linkedin:'',
        company:'',
        position:''
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
    deleteData(row){
      console.log('YUPPPP')
      console.log(row)
      this.temp = Object.assign({}, row)
      deleteCustomer(this.temp.idCustomer).then(() =>{        
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
            this.temp.firstname= ws[i].firstname;
            this.temp.lastname= ws[i].lastname;
            this.temp.phone= ws[i].phone;
            this.temp.email= ws[i].email;
            this.temp.linkedin= ws[i].linkedin;
            this.temp.company=ws[i].company;
            this.temp.position= ws[i].position;
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

      const registerCostumers = (constumer) => {
        return new Promise((resolve) => {
          resolve(createNewCustomer(constumer));
        });
    };

        this.listLoading= true;
        
        const fileContents = await readUploadedFileAsText(file)  
        console.log("START");
        for (var i = 0; i < fileContents.length; i++) {
               console.log("EXECUTING");
               var response = await registerCostumers(fileContents[i])
        }
         console.log("DONE");
        this.getAllUsers();
        this.listLoading= false;




      
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
