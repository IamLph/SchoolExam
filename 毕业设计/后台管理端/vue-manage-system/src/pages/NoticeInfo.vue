<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 通知管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
<!--                搜索输入框-->
                <el-input v-model="searchId" placeholder="通知编号" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
<!--                <el-button type="success" icon="el-icon-lx-add" @click="toAddQuestion">添加</el-button>-->
                <span style="font-size: 20px;margin-left: 20px">第
                    <span style="color: #747cff;font-weight: 600;">{{currentPage}}</span>
                    / {{allPageTotal}}页
                </span>
                <el-button-group style="margin-left: 20px">
                    <el-button type="primary" icon="el-icon-arrow-left" @click="before">上一页</el-button>
                    <el-button type="primary"  @click="next">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
                </el-button-group>

                <div style="display: flex;margin-top: 10px">
                    <el-form>
                        <el-form-item>
                            <span style="font-size: 16px">课程选择:</span>
                            <el-select style="margin-left: 10px" v-model="courseId" placeholder="科目类型" @change="getCourseId($event)">
                                <el-option key="" label="全部" value=""></el-option>
                                <span v-for="(item,index) in courseArray" :key="index">
                                    <el-option :key="item.classId" :label="item.className" :value="item.classId"></el-option>
                                </span>
                            </el-select>
                        </el-form-item>
                    </el-form>
               </div>
            </div>
<!----------------------顶部---------------------------------------------------------------------------------------------------------------------------->
          <!--表格-->
            <el-table :data="allNotice" border class="table" ref="multipleTable" header-cell-class-name="table-header">

                <el-table-column prop="noticeId" label="考试ID" width="80" align="center"></el-table-column>

                <el-table-column label="课程" align="center"  width="88">
                    <template #default="scope">
                        <el-tag>{{ scope.row.className }}</el-tag>
                    </template>
                </el-table-column>
<!--题目-->
                <el-table-column prop="type" label="试卷名称" align="center" width="130">
                </el-table-column>
                <el-table-column prop="startTime" label="开始时间" align="center"></el-table-column>
                <el-table-column prop="endTime" label="结束时间" align="center"></el-table-column>
                <el-table-column prop="teacherName" label="教师" align="center"></el-table-column>
                <el-table-column label="通知状态" align="center" width="80">
                  <template #default="scope">
                    <el-tag type="success" v-if="scope.row.isPublish == 0">未发布</el-tag>
                    <el-tag type="warning" v-else-if="scope.row.isPublish == 1">已发布</el-tag>
                    <el-tag type="danger" v-else-if="scope.row.isPublish == 2 || scope.row.isPublish == 3">已结束</el-tag>
                  </template>
                </el-table-column>
<!--                操做-->
                <el-table-column label="操作" width="260" align="center">
                    <template #default="scope">
                        <el-button type="text" icon="el-icon-edit"
                                   @click="handlePublish(scope.$index, scope.row)">发布
                        </el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"
                                   @click="handleFinish(scope.$index, scope.row)">结束</el-button>
                      <el-button type="text" icon="el-icon-delete" class="red"
                                 @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    import { ref, reactive } from "vue";
    import { ElMessage, ElMessageBox } from "element-plus";
    import {GetAllCourse,GetNoticeByTotal,GetNoticeByCourseIdOrNoticeId,UpdateNoticeStatusById} from '../api/api'
    export default {
        name: "noticeInfo",
        data(){
            return{
                //所有试题
                allQuestion:null,
                //当前页面 即 totalPage
                currentPage:1,
                //每页显示条数 与后台需要保持一致为5
                pageSize:5,
                //总页数
                allPageTotal:20,
                searchId:'',
                courseArray:null,
                //选择科目类型
                courseId:"",
            //    通知
                allNotice:null,
            }
        },
        created() {
            this.getAllCourseArr();
            this.getAllNotice();
        },
        methods:{
            //获取所有考试通知
            getAllNotice(){
              let that = this;
              let currentPage = this.currentPage;
                GetNoticeByTotal({
                  totalPage: currentPage
                }).then(res=>{
                  // console.log(res)
                  if(res.status == 200){
                    that.allNotice = res.data.notice;
                    that.allPageTotal = res.data.allTotal;
                  }else{
                    that.$message({
                      message: '通知为空',
                      type: 'warning'
                    });
                  }
                }).catch(err=>{
                  that.$message.error("网络异常");
                })
            },
            //根据课程选择
            getCourseId(e){
              this.searchId = "";
              if(e == ""){
                this.getAllNotice();
              }else{
                this.getNoticeByAllId();
              }
            },
          //    搜索方法
          handleSearch(){
            this.courseId = "";
            let searchId = this.searchId;
            if(searchId != ""){
              //根据Id搜索考试通知
              this.getNoticeByAllId();
            }
          },
            //获取所有课程
            getAllCourseArr(){
                let that = this;
                GetAllCourse().then(res=>{
                    if(res.status == 200){
                        that.courseArray = res.data;
                    }
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },

            //通过各种Id获取通知
            getNoticeByAllId(){
              let searchId = this.searchId;
              let courseId = this.courseId;
              let currentPage = this.currentPage;
              let that = this;
              GetNoticeByCourseIdOrNoticeId({
                totalPage:currentPage,
                courseId:courseId,
                noticeId:searchId
              }).then(res=>{
                if(res.status == 200){
                  that.allNotice = res.data.notice;
                  that.allPageTotal = res.data.allTotal;
                }else{
                  that.$message({
                    message: '通知为空',
                    type: 'warning'
                  });
                }
              }).catch(err=>{
                that.$message.error("网络异常");
              })
            },
            before(){
                if(this.currentPage !== 1){
                    this.currentPage--;
                    if(this.courseId != "" || this.searchId != ""){
                        this.getNoticeByAllId();
                    }else{
                        this.getAllNotice();
                    }
                }
            },
            next(){
                if(this.currentPage < this.allPageTotal){
                    this.currentPage++;
                    if(this.courseId != "" || this.searchId != ""){
                      this.getNoticeByAllId();
                    }else{
                        this.getAllNotice();
                    }
                }
            },
            updateInfo(type,noticeId){
              let that = this;
              UpdateNoticeStatusById({
                type:type,
                noticeId:noticeId
              }).then(res=>{
                // console.log(res)
                if(res.status == 200){
                  ElMessage.success("操作成功");
                  that.getAllNotice();
                }else{
                  that.$message.error("操作失败，请稍后重试");
                }
              }).catch(err=>{
                that.$message.error("网络异常");
              })
            },
            //发布操作
            handlePublish(index, row){
              let noticeId = row.noticeId;
              console.log(row);
              let that = this;
              if(row.isPublish != 0){
                that.$message.error("操作失败");
              }else{
                ElMessageBox.confirm("您确定要发布通知吗？", "提示", {
                  type: "warning",
                }).then(() => {
                  that.updateInfo(1,noticeId);
                }).catch(() => {});
              }

            },
            //结束操作
            handleFinish(index, row){
              let noticeId = row.noticeId;
              let that = this;
              if(row.isPublish == 2){
                that.$message.error("操作失败");
              }else{
                ElMessageBox.confirm("您确定要结束通知吗？", "提示", {
                  type: "warning",
                }).then(() => {
                  that.updateInfo(2,noticeId);
                }).catch(() => {});
              }
            },
            //删除操作
            handleDelete(index, row){
                let noticeId = row.noticeId;
                let that = this;
                ElMessageBox.confirm("您确定要删除吗？", "提示", {
                    type: "warning",
                }).then(() => {
                    that.updateInfo(4,noticeId);
                }).catch(() => {});
            },
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
    .el-dropdown {
        vertical-align: top;
    }
    .el-dropdown + .el-dropdown {
        margin-left: 15px;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }
</style>
