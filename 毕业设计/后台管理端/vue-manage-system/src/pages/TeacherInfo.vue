<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 教师管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box" style="margin-left: 20px">
                <el-input v-model="searchId" placeholder="用户ID" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>

                <el-button type="success" icon="el-icon-lx-add" @click="toAddTeacher">添加</el-button>

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
            <el-table :data="allTeacher" border class="table"
                      ref="multipleTable"
                      header-cell-class-name="table-header">
<!--                账号-->
                <el-table-column prop="teacherId" label="ID" width="100"></el-table-column>
<!--                用户名-->
<!--                <el-table-column prop="name" label="用户名"></el-table-column>-->
                <el-table-column label="姓名" align="center" width="200">
                    <template #default="scope">
                        <el-tag>{{ scope.row.teacherName }}</el-tag>
                    </template>
                </el-table-column>
<!--                操做-->
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
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
                    <el-input v-model="teacherName"></el-input>
                </el-form-item>
                <el-form-item label="修改密码">
                    <el-input v-model="teacherPassWord" type="password"></el-input>
                </el-form-item>
            </el-form>
<!--            -->
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit">确 定</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 教师添加框 -->
        <el-dialog title="添加" v-model="addTeacherPanel" width="30%">
            <el-form label-width="70px">

                <el-form-item label="用户名">
                    <el-input v-model="teacherName"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="teacherPassWord" type="password"></el-input>
                </el-form-item>
            </el-form>
            <!--            -->
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="addTeacherPanel = false">取 消</el-button>
                    <el-button type="primary" @click="confirmToAdd">确 定</el-button>
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
    import { GetTeacherByPage ,UpdateTeacherInfo,DeleteTeacherById,addTeacher,getTeacherById} from '../api/api';
    export default {
        name: "teacherInfo",
        data(){
            return{
                //所有教师
                allTeacher:null,
                //当前页面 即 totalPage
                currentPage:1,
                //每页显示条数 与后台需要保持一致为5
                pageSize:5,
                //总页数
                allPageTotal:20,
                searchId:'',
                //弹窗编辑
                editVisible:false,
                //编辑的用户名
                teacherName:'',
                //编辑索引
                editIndex:'',
                teacherPassWord:'',
                teacherId:'',
                addTeacherPanel:false,
            }
        },
        created() {
            this.getAllTeacher();
        },
        watch:{
            searchId(newId,oldId){
                if(newId == ""){
                    this.getAllTeacher();
                }
            }
        },
        methods:{
            //总是获取当前页面数据
            getAllTeacher(){
              let that = this;
              let page = that.currentPage;
              GetTeacherByPage({
                  "totalPage":page
              }).then(res=>{
                  // console.log(res);
                  if(res.status == 200){
                      that.allTeacher = res.data.teachers;
                      that.allPageTotal = res.data.allTotal;
                  }else{
                      that.$message({
                          message: '暂时没有教师数据',
                          type: 'warning'
                      });
                  }
              }).catch(err=>{
                  // console.log(err)
                  that.$message.error("网络异常");
              })
            },
        //    搜索方法
            handleSearch(){
                // console.log(this.searchId);
                let searchId = this.searchId;
                if(searchId != ""){
                    /**
                     * 根据教师id查询教师
                     * */
                    let that = this;
                    getTeacherById({
                        teacherId:that.searchId
                    }).then(res=>{
                        // console.log(res);
                        if(res.status == 200){
                            that.allTeacher = res.data.teachers;
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
        //    编辑
            handleEdit(index, row){
                this.editIndex = index;
                //索引
                // console.log(index);
                //行内容
                // console.log(row);
                this.teacherName = row.teacherName;
                this.teacherId = row.teacherId;
                this.editVisible = true;
            },
            //编辑确认
            saveEdit(){
                this.editVisible = false;
            /**
             * 发送请求去修改
             */
            //如果密码为空，修改姓名 不空则修改名字 都传递
                let name = this.teacherName;
                let password = this.teacherPassWord;
                let id = this.teacherId;
                let that = this;
                UpdateTeacherInfo({
                    name:name,
                    password: password,
                    id:id
                }).then(res=>{
                    if(res.status == 200){
                        ElMessage.success('修改成功');
                        that.allTeacher[that.editIndex].teacherName = that.teacherName;
                        that.teacherPassWord = '';
                    }else{
                        that.$message({
                            message: '修改失败',
                            type: 'warning'
                        });
                    }
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },
            handleDelete(index, row){
                let that = this;
                ElMessageBox.confirm("您确定要删除吗？", "提示", {
                    type: "warning",
                }).then(() => {
                        // ElMessage.success("删除成功");
                        // that.allTeacher.splice(index, 1);
                        let teacherId = row.teacherId;
                    /**
                     * 根据id删除教师，发送请求
                     */
                    DeleteTeacherById({
                        id:teacherId
                    }).then(res=>{
                        // console.log(res)
                        if(res.status == 200){
                            ElMessage.success("删除成功");
                            that.allTeacher.splice(index, 1);
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
            before(){
                // console.log("上一页");
                if(this.currentPage !== 1){
                    this.currentPage--;
                    this.getAllTeacher();
                }
            },
            next(){
                if(this.currentPage < this.allPageTotal){
                    this.currentPage++;
                    this.getAllTeacher();
                }
            },
            toAddTeacher(){
                // console.log("++");
                this.addTeacherPanel = true;
            },
            confirmToAdd(){
                // console.log("确认添加");
                // console.log(this.teacherName);
                // console.log(this.teacherPassWord);
                let that = this;
                ElMessageBox.confirm("您确定要添加吗？", "提示", {
                    type: "warning",
                }).then(() => {
                    addTeacher({
                        teacherName:that.teacherName,
                        teacherPassword:that.teacherPassWord
                    }).then(res=>{
                        // console.log(res)
                        if(res.status == 200){
                            ElMessage.success("添加成功");
                            that.addTeacherPanel = false;
                            that.teacherName = "";
                            that.teacherPassWord = "";
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
