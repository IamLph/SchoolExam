<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 学生管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
<!--                搜索输入框-->
                <el-input v-model="searchId" placeholder="用户名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="success" icon="el-icon-lx-add" @click="toAddUser">添加</el-button>
                <span style="font-size: 20px;margin-left: 20px">第
                    <span style="color: #747cff;font-weight: 600;">{{currentPage}}</span>
                    / {{allPageTotal}}页
                </span>
                <el-button-group style="margin-left: 20px">
                    <el-button type="primary" icon="el-icon-arrow-left" @click="before">上一页</el-button>
                    <el-button type="primary"  @click="next">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
                </el-button-group>
            </div>
<!--            表格-->
            <el-table :data="allUser" border class="table" ref="multipleTable" header-cell-class-name="table-header">
<!--                账号-->
                <el-table-column prop="userId" label="ID" width="100"></el-table-column>
<!--                用户名-->
<!--                <el-table-column prop="name" label="用户名"></el-table-column>-->
                <el-table-column label="姓名" align="center"  width="88">
                    <template #default="scope">
                        <el-tag>{{ scope.row.name }}</el-tag>
                    </template>
                </el-table-column>
<!--                性别-->
                <el-table-column label="性别" align="center" width="50">
                   <template #default="scope">
                       <span v-if="scope.row.sex === 1">男</span>
                       <span v-else>女</span>
                   </template>
                </el-table-column>
<!--                地址-->
                <el-table-column prop="nationPlace" label="地址" align="center"></el-table-column>
<!--                联系电话-->
                <el-table-column prop="phone" label="联系电话" align="center" width="110"></el-table-column>
<!--                民族-->
                <el-table-column prop="nation" label="民族"  align="center" width="100"></el-table-column>
<!--                院系-->
                <el-table-column prop="department" label="院系" align="center"></el-table-column>
<!--                班级-->
                <el-table-column prop="grade" label="班级" align="center" width="100"></el-table-column>
<!--                部门-->
                <el-table-column prop="branch" label="部门" align="center" width="100"></el-table-column>
<!--                操做-->
                <el-table-column label="操作" width="260" align="center">
                    <template #default="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"
                                   @click="updatePassword(scope.$index, scope.row)">修改密码</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"
                                   @click="handleDelete(scope.$index, scope.row)">删除</el-button>

                    </template>
                </el-table-column>
            </el-table>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" v-model="editVisible" width="30%">
            <el-form label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="e_name"></el-input>
                </el-form-item>

                <el-form-item label="性别">
                    <el-radio v-model="e_sex" label="1">男</el-radio>
                    <el-radio v-model="e_sex" label="0">女</el-radio>
                </el-form-item>

                <el-form-item label="地址">
                    <el-input v-model="e_address"></el-input>
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input v-model="e_phone"></el-input>
                </el-form-item>
                <el-form-item label="民族">
                    <el-input v-model="e_nation"></el-input>
                </el-form-item>
                <el-form-item label="院系">
                    <el-input v-model="e_department"></el-input>
                </el-form-item>
                <el-form-item label="班级">
                    <el-input v-model="e_grade"></el-input>
                </el-form-item>
                <el-form-item label="部门">
                    <el-input v-model="e_branch"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit">确 定</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 用户添加框 -->
        <el-dialog title="添加" v-model="addUserPanel" width="30%">
            <el-form label-width="70px">

                <el-form-item label="学号">
                    <el-input v-model="a_userId" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="a_userName"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="a_userPassword" type="password"></el-input>
                </el-form-item>
            </el-form>
            <!--            -->
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="addUserPanel = false">取 消</el-button>
                    <el-button type="primary" @click="confirmToAdd">确 定</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 密码修改框 -->
        <el-dialog title="添加" v-model="updatePasswordPanel" width="30%">
            <el-form label-width="70px">

                <el-form-item label="学号">
                    <el-input v-model="p_userId" maxlength="10" disabled></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="p_userName" disabled></el-input>
                </el-form-item>
                <el-form-item label="新密码">
                    <el-input v-model="p_userPassword" type="password"></el-input>
                </el-form-item>
            </el-form>
            <!--            -->
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="updatePasswordPanel = false">取 消</el-button>
                    <el-button type="primary" @click="p_confirm">确 定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import { ref, reactive } from "vue";
    import { ElMessage, ElMessageBox } from "element-plus";
    // import { fetchData } from "../api/index";
    // import { userInfoData } from "../api/index";
    import {GetUserById, GetUserByPage,DeleteUserById,AddUser,UpdateUserInfo,UpdateUserPassword} from '../api/api'
    export default {
        name: "userInfo",
        data(){
            return{
                //所有用户
                allUser:null,
                //当前页面 即 totalPage
                currentPage:1,
                //每页显示条数 与后台需要保持一致为5
                pageSize:5,
                //总页数
                allPageTotal:20,
                searchId:'',
                //编辑
                editIndex:'',
                editVisible:false,
                e_userId:"",
                e_name:"",
                e_address:"",
                e_phone:"",
                e_nation:"",
                e_department:"",
                e_grade:"",
                e_branch:"",
                e_sex:"1",
                //添加
                addUserPanel:false,
                a_userName:'',
                a_userId:'',
                a_userPassword:'',
            //    密码
                updatePasswordPanel:false,
                p_userId:"",
                p_userName:"",
                p_userPassword:"",
            }
        },
        created() {
            this.getAllUser();
        },
        watch:{
            searchId(newId,oldId){
                if(newId == ""){
                    this.getAllUser();
                }
            }
        },
        methods:{
            getAllUser(){
                let that = this;
                let page = that.currentPage;
                GetUserByPage({
                    "totalPage":page
                }).then(res=>{
                    if(res.status == 200){
                        that.allUser = res.data.users;
                        that.allPageTotal = res.data.allTotal;
                    }else{
                        that.$message({
                            message: '暂时没有教师数据',
                            type: 'warning'
                        });
                    }
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },
            //    搜索方法
            handleSearch(){
                let that = this;
                let searchId = this.searchId;
                if(searchId != ""){
                    /**
                     * 根据教师id查询教师
                     * */
                    GetUserById({
                        userId:searchId
                    }).then(res=>{
                        if(res.status == 200){
                            that.allUser = res.data.users;
                            that.allPageTotal = res.data.allTotal;
                        }else{
                            that.$message({
                                message: '搜索失败',
                                type: 'warning'
                            });
                        }
                    }).catch(err=>{
                        that.$message.error("网络异常");
                    })
                }
            },
        //    添加学生
            toAddUser(){
                this.addUserPanel = true;
            },
            confirmToAdd(){
                let id = this.a_userId;
                let name = this.a_userName;
                let password = this.a_userPassword;
                let that = this;
                if(id.length != 10){
                    that.$message.error("账号不规范");
                }else{
                    ElMessageBox.confirm("您确定要添加吗？", "提示", {
                        type: "warning",
                    }).then(() => {
                        AddUser({
                            name:name,
                            userId:id,
                            password:password
                        }).then(res=>{
                            if(res.status == 200){
                                ElMessage.success("添加成功");
                                that.addUserPanel = false;
                                //恢复默认
                                that.a_userPassword = "";
                                that.a_userId = "";
                                that.a_userName = "";
                            }else{
                                that.$message({
                                    message: '添加失败，请稍后重试',
                                    type: 'warning'
                                });
                            }
                        }).catch(err=>{
                            that.$message.error("网络异常");
                        })
                    }).catch(() => {});
                }

            },
            before(){
                if(this.currentPage !== 1){
                    this.currentPage--;
                    this.getAllUser();
                }
            },
            next(){
                if(this.currentPage < this.allPageTotal){
                    this.currentPage++;
                    this.getAllUser();
                }
            },
            handleDelete(index, row){
                let that = this;
                ElMessageBox.confirm("您确定要删除吗？", "提示", {
                    type: "warning",
                }).then(() => {

                    let userId = row.userId;
                    /**
                     * 根据id删除教师，发送请求
                     */
                    DeleteUserById({
                        userId:userId
                    }).then(res=>{
                        if(res.status == 200){
                            ElMessage.success("删除成功");
                            that.allUser.splice(index, 1);
                        }else{
                            that.$message({
                                message: '删除失败',
                                type: 'warning'
                            });
                        }
                    }).catch(err=>{
                        that.$message.error("网络异常");
                    })
                }).catch(() => {});
            },
            //    编辑
            handleEdit(index, row){
                this.editIndex = index;
                this.setE_User(row);
                this.editVisible = true;
            },
            //编辑确认
            saveEdit(){
                /**
                 * 发送请求去修改
                 */
                let that = this;
                UpdateUserInfo({
                    name:that.e_name,
                    userId:that.e_userId,
                    sex:that.e_sex,
                    nation:that.e_nation,
                    nationPlace:that.e_address,
                    phone:that.e_phone,
                    department:that.e_department,
                    grade:that.e_grade,
                    branch:that.e_branch
                }).then(res=>{
                    if(res.status == 200){
                        ElMessage.success("修改成功");
                        that.updateUserInfoByThisPage();
                    }else{
                        that.$message.error("修改失败");
                    }
                    that.editVisible = false;
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },
            setE_User(row){
                this.e_userId=row.userId;
                this.e_name=row.name;
                this.e_address=row.nationPlace;
                this.e_phone=row.phone;
                this.e_nation=row.nation;
                this.e_department=row.department;
                this.e_grade=row.grade;
                this.e_branch=row.branch;
                this.e_sex = row.sex+"";
            },
            clearE_User(){
                this.e_userId="";
                this.e_name="";
                this.e_address="";
                this.e_phone="";
                this.e_nation="";
                this.e_department="";
                this.e_grade="";
                this.e_branch="";
                this.e_sex = "1";
            },
            updateUserInfoByThisPage(){
                this.allUser[this.editIndex].name = this.e_name;
                this.allUser[this.editIndex].branch = this.e_branch;
                this.allUser[this.editIndex].department = this.e_department;
                this.allUser[this.editIndex].grade = this.e_grade;
                this.allUser[this.editIndex].nation = this.e_nation;
                this.allUser[this.editIndex].nationPlace = this.e_address;
                this.allUser[this.editIndex].phone = this.e_phone;
                this.allUser[this.editIndex].sex = this.e_sex;
                this.clearE_User();
            },
            //修改密码
            updatePassword(index,row){
                this.updatePasswordPanel = true;
                this.p_userId = row.userId;
                this.p_userName = row.name;
            },
            //确认修改密码
            p_confirm(){
                let that = this;
                ElMessageBox.confirm("您确定要修改密码吗？", "提示", {
                    type: "warning",
                }).then(() => {
                    UpdateUserPassword({
                        userId:that.p_userId,
                        password:that.p_userPassword
                    }).then(res=>{
                        if(res.status == 200){
                            ElMessage.success("修改成功");
                            that.clearP_user();
                        }else{
                            that.$message.error("修改失败");
                        }
                    }).catch(err=>{
                        that.$message.error("网络异常");
                    })
                }).catch(() => {});
            },
            clearP_user(){
                this.p_userId = "";
                this.p_userName = "";
                this.p_userPassword = "";
                this.updatePasswordPanel = false;
            }
        },
    };
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .table {
        width: 100%;
        font-size: 14px;
    }
    .red {
        color: #ff0000;
    }
    .mr10 {
        margin-right: 10px;
    }
    .table-td-thumb {
        display: block;
        margin: auto;
        width: 40px;
        height: 40px;
    }
</style>
