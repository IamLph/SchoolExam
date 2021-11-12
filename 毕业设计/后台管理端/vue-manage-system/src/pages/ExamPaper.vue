<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-calendar"></i> 添加考试通知
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box" style="width: 100%">
                <el-form label-width="80px">
                    <div style="display: flex">
                        <el-form-item label="课程选择" ref="formRef">
                            <el-select v-model="courseId" placeholder="课程选择"  style="width: 220px" @change="getCourseId($event)">
                                <span v-for="(item,index) in AllCourse" :key="index">
                                    <el-option :key="item.classId" :label="item.className" :value="item.classId"></el-option>
                                </span>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="试卷名称">
                            <el-input v-model="exam_type" style="width: 220px"></el-input>
                        </el-form-item>

                        <el-form-item label="教师选择" prop="region">
                            <el-select v-model="teacherId" placeholder="教师选择"  style="width: 220px" @change="getTeacherId($event)">
                                <span v-for="(item,index) in AllTeacher" :key="index">
                                    <el-option :key="item.teacherId" :label="item.teacherName" :value="item.teacherId"></el-option>
                                </span>
                            </el-select>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary" @click="submit">生成试卷</el-button>
                        </el-form-item>
                    </div>
                    <div style="display: flex">
                        <el-form-item label="开始时间">
                            <el-date-picker
                                    v-model="startTime"
                                    type="datetime"
                                    placeholder="选择开始时间">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="结束时间">
                            <el-date-picker
                                    v-model="endTime"
                                    type="datetime"
                                    placeholder="选择结束时间">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item v-if="courseId!=''">
                                <el-button type="success" plain @click="addOneSelect">添加单选题</el-button>
                                <el-button type="info" plain @click="addMoreSelect">添加多选题</el-button>
                                <el-button type="warning" plain @click="addPanduan">添加判断题</el-button>
                        </el-form-item>
                    </div>
<!--表格-->
                    <el-form-item>
                        <el-table
                                v-loading="loading"
                                element-loading-text="拼命发布中..."
                                element-loading-spinner="el-icon-loading"
                                element-loading-background="rgba(0, 0, 0, 0.8)"
                                :data="multipleSelection"
                                height="400"
                                border
                                :header-cell-style="{textAlign: 'center'}"
                                :cell-style="{ textAlign: 'center' }"
                                style="width: 90%;text-align: center">
                            <el-table-column
                                    prop="examId"
                                    label="编号"
                                    width="100">
                            </el-table-column>
                            <el-table-column
                                    label="类型"
                                    width="100">
                                <template #default="scope">
                                    <span v-if="scope.row.type == '1'">单选题</span>
                                    <span v-else-if="scope.row.type == '2'">多选题</span>
                                    <span v-else-if="scope.row.type == '3'">判断题</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="题目">
                                <template #default="scope">
                                    <el-tooltip class="item" effect="dark" :content="scope.row.title" placement="top">
                                    <span v-if="scope.row.title.length > 40">
                                        {{scope.row.title.substring(0,40)}}....
                                    </span>
                                        <span v-else>
                                        {{scope.row.title}}
                                    </span>
                                    </el-tooltip>
                                </template>
                            </el-table-column>

                            <el-table-column
                                    fixed="right"
                                    label="操作"
                                    width="100">
                                <template v-slot:default="scope">
                                    <el-button
                                            @click.native.prevent="deleteRow(scope.$index, multipleSelection)"
                                            type="text"
                                            size="medium">
                                        移除
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-form-item>
                </el-form>

<!--                弹出-->
                <el-dialog title="添加试题" v-model="selectQuestion" width="80%">
                    <div class="handle-box">
                        <span style="font-size: 20px;margin-left: 20px">第
                        <span style="color: #747cff;font-weight: 600;">{{currentPage}}</span>
                            / {{allPageTotal}}页
                         </span>
                        <el-button-group style="margin-left: 20px">
                            <el-button type="primary" icon="el-icon-arrow-left" @click="before">上一页</el-button>
                            <el-button type="primary"  @click="next">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
                        </el-button-group>
                    </div>
<!--                    表单-->
                    <el-table
                            ref="multipleTable"
                            :data="QuestionList"
                            tooltip-effect="dark"
                            style="width: 100%"
                            :header-cell-style="{textAlign: 'center'}"
                            :cell-style="{ textAlign: 'center' }"
                            @selection-change="handleSelectionChange">
                        <el-table-column
                                type="selection"
                                width="55">
                        </el-table-column>

                        <el-table-column
                                prop="examId"
                                label="编号"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                label="类型"
                                width="100">
                            <template #default="scope">
                                <span v-if="scope.row.type == '1'">单选题</span>
                                <span v-else-if="scope.row.type == '2'">多选题</span>
                                <span v-else-if="scope.row.type == '3'">判断题</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="题目">
                            <template #default="scope">
                                <el-tooltip class="item" effect="dark" :content="scope.row.title" placement="top">
                                    <span v-if="scope.row.title.length > 40">
                                        {{scope.row.title.substring(0,40)}}....
                                    </span>
                                    <span v-else>
                                        {{scope.row.title}}
                                    </span>
                                </el-tooltip>
                            </template>
                        </el-table-column>
                    </el-table>
                    <!--            -->
                    <template #footer>
                        <span class="dialog-footer">
                            <el-button @click="selectQuestion = false">取 消</el-button>
                            <el-button type="primary" @click="confirmToAdd">确 定</el-button>
                        </span>
                    </template>
                </el-dialog>
            </div>
        </div>
    </div>
</template>

<script>
    import {ElMessage} from "element-plus";
    import {AddNotice, GetAllCourse, GetAllTeacher, GetQuestionToSelect,addExamPaper} from "../api/api";

    export default {
        name: "ExamPaper",
        data(){
            return{
                exam_type:"",
                startTime:"",
                endTime:"",
                //所有教师
                AllTeacher:null,
                teacherId:'',
                //所有课程
                AllCourse:null,
                selectQuestion:false,
                courseId:'',
                currentType:'',
                //当前页数
                currentPage:1,
                //总页数
                allPageTotal:2,
                //问题集合
                QuestionList:[],
                //选择的数组
                multipleSelection: [],
                //选择时的数组
                SelectionChangeList:[],
                //加载框
                loading:false,
            }
        },
        created() {
            this.getAllCourseArr();
            this.getAllTeacher();
        },
        methods:{
            //发布通知信息
            submit(){
                if(this.exam_type == "" || this.teacherId == "" || this.startTime == ""|| this.endTime == "" || this.multipleSelection.length == 0 || this.multipleSelection == null){
                    ElMessage.error("数据填写不完整！！");
                }else{
                    let exam_type = this.exam_type;
                    let teacherId = this.teacherId;
                    //转化为时间戳
                    let startTime = this.startTime.getTime();
                    let endTime = this.endTime.getTime();
                    let courseId = this.courseId;
                    if(startTime > endTime){
                        ElMessage.error("请注意时间先后！！");
                    }else{
                        let questionList = this.multipleSelection;
                        this.loading = true;
                        //添加通知
                        this.addNotice(exam_type,startTime,endTime,teacherId,questionList,courseId);
                    }

                }
            },
            addExamPaper(noticeId,list){
                let arr = [];
                for(let i=0;i<list.length;i++){
                    arr[i] = {
                        "noticeId": noticeId,
                        "questionId": list[i].examId
                    };
                }
                let arrJson = JSON.stringify(arr);
                let that = this;
                addExamPaper({
                    list:arrJson
                }).then(res=>{
                    if(res.status == 200){
                        ElMessage.success("发布成功");
                        that.loading = false;
                    }
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },
            //添加考试通知
            addNotice(type,startTime,endTime,teacherId,questionList,courseId){
                let that = this;
                AddNotice({
                    type:type,
                    startTime:startTime,
                    endTime:endTime,
                    teacherId:teacherId,
                    courseId:courseId
                }).then(res=>{
                    if(res.status == 200){
                        let noticeId = res.data;
                        //添加试题
                        that.addExamPaper(noticeId,questionList);
                        // ElMessage.success("发布成功");
                    }else{
                        that.$message.error("发布失败");
                    }
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },
            //获取所有课程
            getAllCourseArr(){
                let that = this;
                GetAllCourse().then(res=>{
                    if(res.status == 200){
                        that.AllCourse = res.data;
                    }
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },
            //获取课程编号
            getCourseId(e){
              // console.log(e);
            },
            //获取所有教师
            getAllTeacher(){
                let that = this;
                GetAllTeacher().then(res=>{
                    if(res.status == 200){
                        that.AllTeacher = res.data;
                    }
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },
            getTeacherId(e){
            },
            //删除某一行数据
            deleteRow(index, rows) {
                rows.splice(index, 1);
            },
            addOneSelect(){
                this.selectQuestion = true;
                this.currentType = '1';
                this.getQuestionListByAll();
            },
            addMoreSelect(){
                this.selectQuestion = true;
                this.currentType = '2';
                this.getQuestionListByAll();
            },
            addPanduan(){
                this.selectQuestion = true;
                this.currentType = '3';
                this.getQuestionListByAll();
            },
            //选择题加到数组
            handleSelectionChange(val) {
                this.SelectionChangeList = val;
            },
            //确定添加了
            confirmToAdd(){
                this.multipleSelection = this.multipleSelection.concat(this.SelectionChangeList);
                this.selectQuestion = false;
            },
            getQuestionListByAll(){
                let courseId = this.courseId;
                let totalPage = this.currentPage;
                let type = this.currentType;
                let that = this;
                GetQuestionToSelect({
                    courseId:courseId,
                    type:type,
                    totalPage:totalPage
                }).then(res=>{
                    if(res.status == 200){
                        that.QuestionList = res.data.questions;
                        that.allPageTotal = res.data.allTotal;
                    }
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },
            before(){
                if(this.currentPage !== 1){
                    this.currentPage--;
                    this.getQuestionListByAll();
                }
            },
            next(){
                if(this.currentPage < this.allPageTotal){
                    this.currentPage++;
                    this.getQuestionListByAll();
                }
            },
        },
    }
</script>

<style scoped>

</style>
