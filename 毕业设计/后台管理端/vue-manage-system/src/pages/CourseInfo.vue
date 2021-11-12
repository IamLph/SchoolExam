<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 学科管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box" style="margin-left: 20px">
        <el-input v-model="searchId" placeholder="课程ID" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>

        <el-button type="success" icon="el-icon-lx-add" @click="toAddTeacher">添加</el-button>
      </div>
<!--=============================顶部====================================================================================      -->
      <!--            表格-->
      <el-table :data="AllCourse" border class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                height="450"
                style="width: 100%">
        <!--                账号-->
        <el-table-column prop="classId" label="课程编号" align="center" width="100"></el-table-column>

        <el-table-column label="课程图像" align="center" width="200">
          <template #default="scope">
            <el-image
                style="width: 50px; height: 50px"
                :src="scope.row.classImgUrl"></el-image>
          </template>
        </el-table-column>
        <el-table-column label="课程名称" align="center">
          <template #default="scope">
            <el-tag>{{ scope.row.className }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="任课老师" align="center" width="200">
          <template #default="scope">
            <el-tag type="warning">{{ scope.row.classTeacherName }}</el-tag>
          </template>
        </el-table-column>
        <!--                操做-->
        <el-table-column label="操作" width="280" align="center">
          <template #default="scope">
            <el-button type="text" icon="el-icon-lx-punch" @click="handleCourse(scope.$index, scope.row)">课时管理
            </el-button>

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
        <el-form-item label="课程图像">
<!--          <el-input v-model="teacherName"></el-input>-->
          <el-image
              style="width: 100px; height: 100px"
              :src="classImgUrl"></el-image>
        </el-form-item>
        <el-form-item label="课程名称">
          <el-input v-model="className"></el-input>
        </el-form-item>
        <el-form-item label="任课老师">
          <el-input v-model="classTeacherName"></el-input>
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

    <!-- 课程添加框 -->
    <el-dialog title="添加" v-model="addTeacherPanel" width="30%">
      <el-form label-width="70px" v-loading="loading">
        <el-form-item label="课程图像">
          <el-upload
              action="#"
              multiple
              :limit="1"
              list-type="picture-card"
              :http-request="uploadSectionFile">
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="课程名称">
          <el-input v-model="className"></el-input>
        </el-form-item>
        <el-form-item label="任课老师">
          <el-input v-model="classTeacherName"></el-input>
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
<!--    课时管理框-->
    <el-dialog title="编辑" v-model="courseVisible" width="80%">
<!--      编辑弹出框-->
      <el-dialog title="编辑" v-model="courseEditVisible" width="40%">
        <el-form label-width="70px" v-loading="loading" >
          <el-form-item label="课时">
            <el-input v-model="editVideoName"></el-input>
          </el-form-item>
          <el-form-item label="单元">
            <el-input v-model="editVideoUnit"></el-input>
          </el-form-item>

          <el-form-item label="课程图像">
<!--            <el-image-->
<!--                style="width: 100px; height: 100px"-->
<!--                :src="editVideoImg"></el-image>-->
            <el-upload
                action="#"
                multiple
                :limit="1"
                list-type="picture-card"
                :http-request="uploadSectionPictureFile">
                <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="课程视频">
            <el-upload
                action="#"
                multiple
                :limit="1"
                list-type="picture-card"
                :http-request="uploadSectionMp5File">
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>

          <el-form-item label="视频信息">
            <el-input v-model="editVideoInfo"></el-input>
          </el-form-item>

        </el-form>
        <!--            -->
        <template #footer>
                <span class="dialog-footer">
                    <el-button @click="courseEditVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveVideoEdit">确 定</el-button>
                </span>
        </template>
      </el-dialog>
<!--      添加弹出框-->
      <el-dialog title="添加" v-model="addVideoPanel" width="40%">
        <el-form label-width="70px" v-loading="loading" >
          <el-form-item label="课时">
            <el-input v-model="editVideoName"></el-input>
          </el-form-item>
          <el-form-item label="单元">
            <el-input v-model="editVideoUnit"></el-input>
          </el-form-item>

          <el-form-item label="课程图像">
            <el-upload
                action="#"
                multiple
                :limit="1"
                list-type="picture-card"
                :http-request="uploadSectionPictureFile">
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="课程视频">
            <el-upload
                action="#"
                multiple
                :limit="1"
                list-type="picture-card"
                :http-request="uploadSectionMp5File">
              <i class="el-icon-plus"></i>
            </el-upload>
          </el-form-item>

          <el-form-item label="视频信息">
            <el-input v-model="editVideoInfo"></el-input>
          </el-form-item>

        </el-form>
        <!--       确定添加视频     -->
        <template #footer>
                <span class="dialog-footer">
                    <el-button @click="addVideoPanel = false">取 消</el-button>
                    <el-button type="primary" @click="addVideo">确 定</el-button>
                </span>
        </template>
      </el-dialog>
<!---------------------------上面弹出框------>
      <div class="handle-box" style="margin-left: 20px">
        <el-button type="success" icon="el-icon-lx-add" @click="addNewVideo">添加</el-button>
      </div>
      <el-table :data="videoInfo" border class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                height="450"
                style="width: 100%">
        <!--                账号-->
        <el-table-column prop="videoName" label="课时" align="center" width="100"></el-table-column>
        <el-table-column prop="videoUnit" label="单元" align="center" width="100"></el-table-column>

        <el-table-column label="视频封面" align="center" width="200">
          <template #default="scope">
            <el-image
                style="width: 50px; height: 50px"
                :src="scope.row.videoImg"></el-image>
          </template>
        </el-table-column>

        <el-table-column label="视频简介" align="center">
          <template #default="scope">
            <el-tooltip class="item" effect="dark" :content="scope.row.videoInfo" placement="top">
                <span v-if="scope.row.videoInfo.length < 15">
                    {{scope.row.videoInfo}}
                </span>
                <span v-else>
                    {{scope.row.videoInfo.substring(0,15)}}...
                </span>
            </el-tooltip>
          </template>
        </el-table-column>

        <!--操做-->
        <el-table-column label="操作" width="280" align="center">
          <template #default="scope">
<!--            视频编辑-->
            <el-button type="text" icon="el-icon-edit" @click="handleVideoEdit(scope.$index, scope.row)">编辑
            </el-button>

            <el-button type="text" icon="el-icon-delete" class="red"
                       @click="videoDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-dialog>

  </div>
</template>

<script>
import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import { GetAllCourse,GetCourseById,AddCourse,UpdateCourse,DeleteCourse,GetVideoByCourseId,UpdateVideoInfo,AddVideoInfo,DeleteVideoById} from '../api/api';
import axios from 'axios';
export default {
  name: "CourseInfo",
  data(){
    return{
      searchId:'',
      //弹窗编辑
      editVisible:false,
      //编辑索引
      editIndex:'',
      addTeacherPanel:false,
    //  所有课程
      AllCourse:null,
      classId:"",
      classImgUrl:"",
      className:"",
      classTeacherName:"",
      file:null,
      //加载框
      loading: false,
      courseVisible:false,
      videoInfo:null,
      courseEditVisible:false,
      videoImg:"",
      videoUrl:"",
      //编辑视频五个参数
      editVideoImg:"",
      editVideUrl:"",
      editVideoInfo:"",
      editVideoName:"",
      editVideoUnit:"",
      videoSelectImg:null,
      videoSelectMp5:null,
      //选择视频时的Id
      videoId:"",
      addVideoPanel:false,
    }
  },
  created() {
    //获取所有课程
    this.getAllCourse();
  },
  watch:{
    searchId(newId,oldId){
      if(newId == ""){
        this.getAllCourse();
      }
    }
  },
  methods:{
    addVideo(){
      this.loading = true;
      let img = this.videoSelectImg;
      let mp5 = this.videoSelectMp5;
      let videoName = this.editVideoName;
      let videoInfo = this.editVideoInfo;
      let videoUnit = this.editVideoUnit;
      let classId = this.classId;
      let that = this;
      let formData = new FormData();
      formData.append("img", img);
      formData.append("mp5", mp5);
      formData.append("videoName", videoName);
      formData.append("videoInfo", videoInfo);
      formData.append("videoUnit", videoUnit);
      formData.append("classId", classId);
      if(img == null || mp5 == null){
        alert("请选择图片与视频");
      }else{
        //发送添加请求
        axios({
          method: 'post',
          url:AddVideoInfo,
          data: formData,
          headers: {'Content-Type':'multipart/form-data'}
        }).then(res=>{
          if(res.status == 200){
            GetVideoByCourseId({
              classId:classId
            }).then(res=>{
              // console.log(res);
              if(res.status == 200){
                that.videoInfo = res.data;
                that.loading = false;
                that.$message({
                  message: '添加成功',
                  type: 'success'
                });
                that.editVideoInfo = "";
                that.editVideoUnit = "";
                that.editVideoName = "";
                that.editVideoImg = "";
                that.editVideUrl = "";
                that.addVideoPanel = false;
              }
            }).catch(err=>{
              that.$message.error("网络异常");
            })
          }
        }).catch(err=>{
          that.$message.error("网络异常");
        });
      }

    },
    //添加新视频的面板打开
    addNewVideo(){
      this.addVideoPanel = true;
    },
    //选择图片
    uploadSectionPictureFile:function (params) {
      this.videoSelectImg = params.file;
    },
    //选择视频
    uploadSectionMp5File:function (params) {
      this.videoSelectMp5 = params.file;
    },
    //视频信息编辑框
    handleVideoEdit(index,row){
      this.courseEditVisible = true;
      this.editVideoInfo = row.videoInfo;
      this.editVideoUnit = row.videoUnit;
      this.editVideoName = row.videoName;
      this.editVideoImg = row.videoImg;
      this.editVideUrl = row.videoUrl;
      this.videoId = row.id;
    },
    //确认修改视频的信息
    saveVideoEdit(){
      this.loading = true;
      let img = this.videoSelectImg;
      let mp5 = this.videoSelectMp5;
      let videoName = this.editVideoName;
      let videoInfo = this.editVideoInfo;
      let videoUnit = this.editVideoUnit;
      let id = this.videoId;
      let that = this;
      let formData = new FormData();
      formData.append("img", img);
      formData.append("mp5", mp5);
      formData.append("videoName", videoName);
      formData.append("videoInfo", videoInfo);
      formData.append("videoUnit", videoUnit);
      formData.append("id", id);
      //发送请求
      axios({
        method: 'post',
        url:UpdateVideoInfo,
        data: formData,
        headers: {'Content-Type':'multipart/form-data'}
      }).then(res=>{
        if(res.status == 200){
          let classId = that.classId;
          GetVideoByCourseId({
            classId:classId
          }).then(res=>{
            if(res.status == 200){
              that.videoInfo = res.data;
              that.loading = false;
              that.$message({
                message: '修改成功',
                type: 'success'
              });
              that.editVideoInfo = "";
              that.editVideoUnit = "";
              that.editVideoName = "";
              that.editVideoImg = "";
              that.editVideUrl = "";
              that.courseEditVisible = false;
            }
          }).catch(err=>{
            that.$message.error("网络异常");
          })
        }
      }).catch(err=>{
        that.$message.error("网络异常");
      });
    },
    //重新获取当前的课程的课时
    getClassVideo(){
      let classId = this.classId;
      let that = this;
      GetVideoByCourseId({
        classId:classId
      }).then(res=>{
        if(res.status == 200){
          that.videoInfo = res.data;
        }
      }).catch(err=>{
        that.$message.error("网络异常");
      })
    },
    //课程视频管理
    handleCourse(index, row){
      this.courseVisible = true;
      let that = this;
      let classId = row.classId;
      //设置课程的Id
      this.classId = classId;
      //获取视频课程信息
      GetVideoByCourseId({
        classId:classId
      }).then(res=>{
        if(res.status == 200){
          that.videoInfo = res.data;
        }
      }).catch(err=>{
        that.$message.error("网络异常");
      })
    },
    /*视频删除*/
    videoDelete(index,row){
      //获取视频Id
      let id = row.id;
      let that = this;
      /**
       * 根据视频id删除视频
       */
      ElMessageBox.confirm("您确定要删除吗？", "提示", {
        type: "warning",
      }).then(() => {
        DeleteVideoById({
          id:id
        }).then(res=>{
          if(res.status == 200){
            that.getClassVideo();
          }
        }).catch(err=>{
          that.$message.error("网络异常");
        })
      }).catch(() => {});
    },
    //确认添加
    confirmToAdd(){
      this.loading = true;
      this.uploadFile(this.file);
    },
    //选择文件
    uploadSectionFile: function (params) {
      this.file = params.file;
    },
    // 上传函数
    uploadFile: function (file) {
      let className = this.className;
      let classTeacherName = this.classTeacherName;
      let that = this;
      let formData = new FormData();
      formData.append("file", file);
      formData.append("className", className);
      formData.append("classTeacherName", classTeacherName);
      axios({
        method: 'post',
        url:AddCourse,
        data: formData,
        headers: {'Content-Type':'multipart/form-data'}
      }).then(res=>{
        if(res.status == 200){
          // console.log(res)
          that.loading = false;
          that.$message({
            message: '添加成功',
            type: 'success'
          });
          that.addTeacherPanel = false;
          //重新获取课程
          that.getAllCourse();
        }
      }).catch(err=>{
        that.$message.error("网络异常");
      });
    },
    //获取所有课程
    getAllCourse(){
      let that = this;
      GetAllCourse().then(res=>{
        // console.log(res)
        if(res.status == 200){
          that.AllCourse = res.data
        }else{
          that.$message({
            message: '暂时没有数据',
            type: 'warning'
          });
        }
      }).catch(err=>{
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
        GetCourseById({
          courseId:searchId
        }).then(res=>{
            if(res.status == 200){
              // console.log(res);
              that.AllCourse = res.data;
            }else{
              that.$message({
                message: '搜索失败',
                type: 'warning'
              });
            }
        }).catch(err=>{
          that.$message.error("网络异常");
        });
      }
    },
    //    编辑
    handleEdit(index, row){
      //显示编辑框
      this.editVisible = true;
      this.editIndex = index;
      //索引
      //行内容
      this.classId = row.classId;
      this.classImgUrl = row.classImgUrl;
      this.className = row.className;
      this.classTeacherName = row.classTeacherName;

    },
    //删除操作
    handleDelete(index, row){
      let that = this;
      ElMessageBox.confirm("您确定要删除吗？", "提示", {
        type: "warning",
      }).then(() => {
        // ElMessage.success("删除成功");
        // that.allTeacher.splice(index, 1);
        let classId = row.classId;
        /**
         * 根据id删除教师，发送请求
         */
        DeleteCourse({
          classId:classId
        }).then(res=>{
          if(res.status == 200){
            ElMessage.success("删除成功");
            that.getAllCourse();
          }else{
            ElMessage.warn("删除失败");
          }
        }).catch(err=>{
          that.$message.error("网络异常");
        });
      }).catch(() => {});
    },
    //编辑确认
    saveEdit(){
      let classId = this.classId;
      let className = this.className;
      let classTeacherName = this.classTeacherName;
      //请求编辑
      let that = this;
      UpdateCourse({
        classId: classId,
        className: className,
        classTeacherName: classTeacherName
      }).then(res=>{
        // console.log(res)
        if(res.status == 200){
          that.$message({
            message: '修改成功',
            type: 'success'
          });
        }
        that.editVisible = false;
      }).catch(err=>{
        that.$message.error("网络异常");
      })
    },
    //去添加课程
    toAddTeacher(){
      this.classImgUrl="";
      this.className="";
      this.classTeacherName="";
      this.addTeacherPanel = true;
    },
  },
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}
.disabled .el-upload--picture-card {
  display: none;
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
