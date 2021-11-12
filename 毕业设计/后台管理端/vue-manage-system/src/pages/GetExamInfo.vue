<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 成绩批改
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <!--                搜索输入框-->
        <el-input v-model="searchId" placeholder="考试编号" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
      </div>
      <!----------------------顶部---------------------------------------------------------------------------------------------------------------------------->
      <!--表格-->
      <el-table :data="allNotice"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                height="450"
                style="width: 100%">
        <el-table-column prop="noticeId" label="考试ID" width="100" align="center"></el-table-column>

        <el-table-column label="课程名称" align="center"  width="88">
          <template #default="scope">
            <el-tag>{{ scope.row.className }}</el-tag>
          </template>
        </el-table-column>
        <!--题目-->
        <el-table-column prop="type" label="试卷名称" align="center">
        </el-table-column>
        <el-table-column prop="teacherName" label="教师" align="center"></el-table-column>
        <el-table-column label="发布状态" align="center" width="80">
          <template #default="scope">
            <el-tag type="success" v-if="scope.row.isPublish == 2">待批改</el-tag>
            <el-tag type="warning" v-else-if="scope.row.isPublish == 3">已完成</el-tag>
          </template>
        </el-table-column>
        <!--                操做-->
        <el-table-column label="操作" width="360" align="center">
          <template #default="scope">
            <el-button type="text" icon="el-icon-edit"
                       @click="handlePublish(scope.$index, scope.row)">批改
            </el-button>
            <el-button type="text" icon="el-icon-delete" class="red"
                       @click="handleFinish(scope.$index, scope.row)">发布成绩</el-button>
<!--            <el-button type="text" icon="el-icon-delete" class="red"-->
<!--                       @click="handleDelete(scope.$index, scope.row)">删除</el-button>-->
          </template>
        </el-table-column>
      </el-table>
      <!--弹窗批改-->
      <el-dialog title="编辑" v-model="setExamVisible" width="80%">
        <!--            -->
        <template #footer>
                <span class="dialog-footer">
                    <el-button @click="setExamVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit">保 存</el-button>
                </span>
        </template>
        <el-table :data="ExamPaperInfo"
                  border
                  class="table"
                  ref="multipleTable"
                  header-cell-class-name="table-header"
                  height="400"
                  style="width: 100%">
          <el-table-column prop="userId" label="学生学号" width="100" align="center"></el-table-column>
          <el-table-column prop="name" label="学生姓名" width="100" align="center"></el-table-column>

          <el-table-column label="测试分数" align="center"  width="88">
            <template #default="scope">
              <el-tag>{{ scope.row.score }}</el-tag>
            </template>
          </el-table-column>
          <!--题目-->
          <el-table-column prop="live" label="离开次数" align="center">
          </el-table-column>

          <el-table-column label="最终分数" align="center">
            <template #default="scope">
              <el-input v-model="scope.row.finalscore" placeholder="请输入最终分数"></el-input>
            </template>
          </el-table-column>
        </el-table>

      </el-dialog>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage,ElMessageBox} from "element-plus";
import {
  UpdateNoticeStatusById,
  GetExamResult,
  GetExamByNoticeId,
  SetScoreByUserIdAndNoticeId,
  SetScorePublic,
  GetExamResultByNoticeId, DeleteVideoById
} from '../api/api'
export default {
  name: "GetExamInfo",
  data(){
    return{
      //所有试题
      allQuestion:null,
      searchId:'',
      courseArray:null,
      //选择科目类型
      courseId:"",
      //    通知
      allNotice:null,
      setExamVisible:false,
      ExamPaperInfo:null,
      noticeId:"",
    }
  },
  created() {
    this.getAllExamResult();
  },
  watch:{
    searchId(newId,oldId){
      if(newId == ""){
        this.getAllExamResult();
      }
    }
  },
  methods:{
    //试卷批改保存
    saveEdit(){
      let list = this.ExamPaperInfo;
      let flag = 0;
      let arr = [];
      let that = this;
      let noticeId = this.noticeId;
      for(var i=0;i<list.length;i++){
        // console.log(list[i].finalscore);
        if(list[i].finalscore > 100){
          alert("学号:["+list[i].userId+"]分数异常！")
          flag = 1;
          break;
        }
        arr[i] = {
          "userId":list[i].userId,
          "finalscore":list[i].finalscore+"",
          "noticeId":noticeId
        }
      }
      if(flag == 0){
        /**
         * 将最终分数修改到数据库中
         */
        // console.log(arr)
        let arrJson = JSON.stringify(arr);
        SetScoreByUserIdAndNoticeId({
          list:arrJson
        }).then(res=>{
          // console.log(res);
          if(res.status == 200){
            ElMessage.success("保存成功");
            that.setExamVisible = false;
          }else{
            that.$message.error("保存失败");
          }
        }).catch(err=>{
          that.$message.error("网络异常");
        })
      }
    },
    //获取所有考试通知
    getAllExamResult(){
      let that = this;
      GetExamResult().then(res=>{
        that.allNotice = res.data;
      }).catch(err=>{
        that.$message.error("网络异常");
      })
    },
    //    搜索方法
    handleSearch(){
      this.courseId = "";
      let searchId = this.searchId;
      let that = this;
      if(searchId != ""){
        GetExamResultByNoticeId({
          noticeId:searchId
        }).then(res=>{
          if(res.status == 200){
            that.allNotice = res.data;
          }
        }).catch(err=>{
          that.$message.error("网络异常");
        })
      }
    },
    //批改操作
    handlePublish(index, row){
      let noticeId = row.noticeId;
      this.noticeId = noticeId;
      let that = this;
      this.setExamVisible = true;
      //获取对应课程试卷
      GetExamByNoticeId({
        noticeId:noticeId
      }).then(res=>{
        if(res.status == 200){
          that.ExamPaperInfo = res.data;
        }else{
          that.$message.error("数据为空");
        }
      }).catch(err=>{
        that.$message.error("网络延迟");
      })
    },
    //发布成绩
    handleFinish(index, row){
      let noticeId = row.noticeId;
      let that = this;
      ElMessageBox.confirm("您确定要公布成绩吗？", "提示", {
        type: "warning",
      }).then(() => {
        //修改为3 批改完成
        UpdateNoticeStatusById({
          type:3,
          noticeId:noticeId
        }).then(res=>{
          if(res.status == 200){
            that.getAllExamResult();
            //将分数公布出去
            SetScorePublic({
              noticeId:noticeId
            }).then(res=>{
              // console.log(res)
            })
          }
        }).catch(err=>{
          that.$message.error("网络延迟");
        })
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

