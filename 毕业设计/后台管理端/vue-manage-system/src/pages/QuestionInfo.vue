<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 试题管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
<!--                搜索输入框-->
                <el-input v-model="searchId" placeholder="试题编号" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="success" icon="el-icon-lx-add" @click="toAddQuestion">添加</el-button>

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


                    <span style="margin-left: 20px">
                        <el-form>
                            <el-form-item>
                                <span style="font-size: 16px">题型选择:</span>
                                <el-select style="margin-left: 10px" v-model="questionType" placeholder="题目类型" @change="getQuestionType($event)">
                                    <el-option key="" label="全部" value=""></el-option>
                                    <el-option key="1" label="单选题" value="1"></el-option>
                                    <el-option key="2" label="多选题" value="2"></el-option>
                                    <el-option key="3" label="判断题" value="3"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-form>

                    </span>
                </div>
            </div>
<!--            表格-->
            <el-table :data="allQuestion" border class="table" ref="multipleTable" header-cell-class-name="table-header">

                <el-table-column prop="examId" label="ID" width="80" align="center"></el-table-column>


                <el-table-column label="课程" align="center"  width="88">
                    <template #default="scope">
                        <el-tag>{{ scope.row.className }}</el-tag>
                    </template>
                </el-table-column>
<!--题目-->
                <el-table-column label="题目" align="center" width="130">
                    <template #default="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.title" placement="top">
                            <span v-if="scope.row.title.length < 15">
                                {{scope.row.title}}
                            </span>
                            <span v-else>
                                {{scope.row.title.substring(0,15)}}...
                            </span>
                        </el-tooltip>
                    </template>
                </el-table-column>
                <el-table-column prop="type" label="类型" align="center" width="80"></el-table-column>
<!--                A-->
                <el-table-column label="A" align="center" width="100">
                    <template #default="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.optionList[0].content" placement="top">
                            <span v-if="scope.row.title.length < 15">
                                {{scope.row.optionList[0].content}}
                            </span>
                            <span v-else>
                                {{scope.row.optionList[0].content.substring(0,15)}}...
                            </span>
                        </el-tooltip>
                    </template>
                </el-table-column>
<!--                B-->
                <el-table-column label="B"  align="center" width="100">
                    <template #default="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.optionList[1].content" placement="top">
                            <span v-if="scope.row.title.length < 15">
                                {{scope.row.optionList[1].content}}
                            </span>
                            <span v-else>
                                {{scope.row.optionList[1].content.substring(0,15)}}...
                            </span>
                        </el-tooltip>
                    </template>
                </el-table-column>
<!--                C-->
                <el-table-column label="C" align="center" width="100">
                    <template #default="scope">
                        <span v-if="scope.row.optionList[2] != null">
                             <el-tooltip class="item" effect="dark" :content="scope.row.optionList[2].content" placement="top">
                                <span v-if="scope.row.title.length < 15">
                                    {{scope.row.optionList[2].content}}
                                </span>
                                <span v-else>
                                    {{scope.row.optionList[2].content.substring(0,15)}}...
                                </span>
                            </el-tooltip>
                        </span>
                    </template>
                </el-table-column>
<!--                D-->
                <el-table-column label="D" align="center" width="100">
                    <template #default="scope">
                        <span v-if="scope.row.optionList[3] != null">
                            <el-tooltip class="item" effect="dark" :content="scope.row.optionList[3].content" placement="top">
                            <span v-if="scope.row.title.length < 15">
                                {{scope.row.optionList[3].content}}
                            </span>
                            <span v-else>
                                {{scope.row.optionList[3].content.substring(0,15)}}...
                            </span>
                        </el-tooltip>
                        </span>

                    </template>
                </el-table-column>
<!--                true-->
                <el-table-column prop="answer" label="正确答案" align="center" width="80"></el-table-column>

                <el-table-column label="解释" align="center">
                    <template #default="scope">
                        <el-tooltip class="item" effect="dark" :content="scope.row.explain" placement="top">
                            <span v-if="scope.row.explain.length < 15">
                                {{scope.row.explain}}
                            </span>
                            <span v-else>
                                {{scope.row.explain.substring(0,15)}}...
                            </span>
                        </el-tooltip>
                    </template>
                </el-table-column>
<!--                操做-->
                <el-table-column label="操作" width="160" align="center">
                    <template #default="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"
                                   @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

<!-- ===============================================编辑弹出框 ===============================================-->
        <el-dialog title="编辑" v-model="editVisible" width="30%">
            <el-form label-width="70px">
                <div style="display: flex">
                    <el-form-item label="课程">
                        <el-form>
                            <el-form-item>
                                <el-select v-model="a_courseId" placeholder="科目类型">
                                    <span v-for="(item,index) in courseArray" :key="index">
                                <el-option :key="item.classId" :label="item.className" :value="item.classId"></el-option>
                            </span>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </el-form-item>

                    <el-form-item label="类型">
                        <el-form>
                            <el-form-item>
                                <el-select v-model="a_type" placeholder="题目类型">
                                    <el-option key="1" label="单选题" value="1"></el-option>
                                    <el-option key="2" label="多选题" value="2"></el-option>
                                    <el-option key="3" label="判断题" value="3"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </el-form-item>
                </div>

                <el-form-item label="题目">
                    <el-input type="textarea" rows="2" v-model="a_title" style="width: 92%"></el-input>
                </el-form-item>

                <div style="display: flex">
                    <el-form-item label="选项A">
                        <el-input v-model="a_A"></el-input>
                    </el-form-item>
                    <span style="margin-left: 6%">
                        <el-form-item label="选项B">
                            <el-input v-model="a_B"></el-input>
                        </el-form-item>
                    </span>

                </div>

                <div style="display: flex" v-show="a_type != 3 ">
                    <el-form-item label="选项C">
                        <el-input v-model="a_C"></el-input>
                    </el-form-item>
                    <span style="margin-left: 6%">
                        <el-form-item label="选项D">
                            <el-input v-model="a_D"></el-input>
                        </el-form-item>
                    </span>
                </div>
                <el-form-item label="正确答案">

                    <el-radio-group v-model="a_answer" size="small" v-if="a_type == 1">
                        <el-radio-button label="A"></el-radio-button>
                        <el-radio-button label="B"></el-radio-button>
                        <el-radio-button label="C"></el-radio-button>
                        <el-radio-button label="D"></el-radio-button>
                    </el-radio-group>

                    <el-checkbox-group v-model="a_moreAnswer" size="small" v-else-if="a_type == 2">
                        <el-checkbox-button v-for="index in a_option" :label="index" :key="index">{{index}}</el-checkbox-button>
                    </el-checkbox-group>

                    <el-radio-group v-model="a_answer" size="small" v-else-if="a_type == 3">
                        <el-radio-button label="A"></el-radio-button>
                        <el-radio-button label="B"></el-radio-button>
                    </el-radio-group>

                </el-form-item>

                <el-form-item label="解释">
                    <el-input type="textarea" rows="3" v-model="a_explain" style="width: 92%"></el-input>
                </el-form-item>

            </el-form>
            <!--            -->
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="updateQuestion">确 定</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 添加试题 -->
        <el-dialog title="添加试题" v-model="addQuestionPanel" width="40%">
            <el-form label-width="70px">
                <div style="display: flex">
                    <el-form-item label="课程">
                        <el-form>
                            <el-form-item>
                                <el-select v-model="a_courseId" placeholder="科目类型">
                                    <span v-for="(item,index) in courseArray" :key="index">
                                <el-option :key="item.classId" :label="item.className" :value="item.classId"></el-option>
                            </span>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </el-form-item>

                    <el-form-item label="类型">
                        <el-form>
                            <el-form-item>
                                <el-select v-model="a_type" placeholder="题目类型">
                                    <el-option key="1" label="单选题" value="1"></el-option>
                                    <el-option key="2" label="多选题" value="2"></el-option>
                                    <el-option key="3" label="判断题" value="3"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </el-form-item>
                </div>

                <el-form-item label="题目">
                        <el-input type="textarea" rows="2" v-model="a_title" style="width: 92%"></el-input>
                </el-form-item>

                <div style="display: flex">
                    <el-form-item label="选项A">
                        <el-input v-model="a_A"></el-input>
                    </el-form-item>
                    <span style="margin-left: 6%">
                        <el-form-item label="选项B">
                            <el-input v-model="a_B"></el-input>
                        </el-form-item>
                    </span>

                </div>

                <div style="display: flex" v-show="a_type != 3 ">
                    <el-form-item label="选项C">
                        <el-input v-model="a_C"></el-input>
                    </el-form-item>
                    <span style="margin-left: 6%">
                        <el-form-item label="选项D">
                            <el-input v-model="a_D"></el-input>
                        </el-form-item>
                    </span>
                </div>


                <el-form-item label="正确答案">

                    <el-radio-group v-model="a_answer" size="small" v-if="a_type == 1">
                        <el-radio-button label="A"></el-radio-button>
                        <el-radio-button label="B"></el-radio-button>
                        <el-radio-button label="C"></el-radio-button>
                        <el-radio-button label="D"></el-radio-button>
                    </el-radio-group>

                    <el-checkbox-group v-model="a_moreAnswer" size="small" v-else-if="a_type == 2">
                        <el-checkbox-button v-for="index in a_option" :label="index" :key="index">{{index}}</el-checkbox-button>
                    </el-checkbox-group>

                    <el-radio-group v-model="a_answer" size="small" v-else-if="a_type == 3">
                        <el-radio-button label="A"></el-radio-button>
                        <el-radio-button label="B"></el-radio-button>
                    </el-radio-group>

                </el-form-item>

                <el-form-item label="解释">
                    <el-input type="textarea" rows="3" v-model="a_explain" style="width: 92%"></el-input>
                </el-form-item>

            </el-form>
            <!--            -->
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="addQuestionPanel = false">取 消</el-button>
                    <el-button type="primary" @click="confirmToAdd">确 定</el-button>
                </span>
            </template>
        </el-dialog>

    </div>
</template>

<script>
    import { ref, reactive } from "vue";
    import { ElMessage, ElMessageBox } from "element-plus";
    import {GetQuestionByTotalPage,DeleteQuestionById,AddQuestion,GetQuestionById,GetAllCourse,GetQuestionByType,UpdateQuestion} from '../api/api'
    import {getQuestionVo} from '../utils/methods'
    export default {
        name: "userInfo",
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
                //编辑
                editIndex:'',
                editVisible:false,
                e_examId:"",
                //添加
                addQuestionPanel:false,
                a_className:"",
                a_courseId:"",
                a_title:"",
                a_type:"",
                a_A:"",
                a_B:"",
                a_C:"",
                a_D:"",
                a_answer:"",
                a_moreAnswer:[],
                a_explain:"",
                a_option:['A','B','C','D'],
                //选择科目类型
                courseId:"",
                //选择题目类型
                questionType:"",
            //    科目类型数组，有默认
                courseArray:[{
                    "classId":"1001",
                    "className":"语文"
                },{
                    "classId":"10012",
                    "className":"数学"
                }],

            }
        },
        created() {
            this.getAllCourseArr();
            this.getAllQuestion();
        },
        watch:{
            searchId(newId,oldId){
                if(newId == ""){
                    this.getAllQuestion();
                }
            }
        },
        methods:{
            getQuestionByType(){
                let courseId = this.courseId;
                let questionType = this.questionType;
                let currentPage = this.currentPage;
                let that = this;
                GetQuestionByType({
                    courseId:courseId,
                    questionType:questionType,
                    totalPage:currentPage
                }).then(res=>{
                    console.log(res)
                    if(res.status == 200){
                        let allQuestion = getQuestionVo(res.data.questions);
                        that.allQuestion = allQuestion;
                        that.allPageTotal = res.data.allTotal;
                    }else{
                        that.$message({
                            message: '题库为空',
                            type: 'warning'
                        });
                    }
                }).catch(err=>{
                    that.$message.error("网络异常");
                })
            },
            getCourseId(e){
                this.getQuestionByType();
            },
            getQuestionType(e){
                this.getQuestionByType();
            },
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
            //获取所有试题
            getAllQuestion(){
                let that = this;
                let currentPage = this.currentPage;
                GetQuestionByTotalPage({
                    totalPage: currentPage
                }).then(res=>{
                    // console.log(res)
                    if(res.status == 200){
                        let allQuestion = getQuestionVo(res.data.questions);
                        that.allQuestion = allQuestion;
                        that.allPageTotal = res.data.allTotal;
                    }else{
                        that.$message({
                            message: '题库为空',
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
                    GetQuestionById({
                        examId:searchId
                    }).then(res=>{
                        if(res.status == 200){
                            let allQuestion = getQuestionVo(res.data.questions);
                            that.allQuestion = allQuestion;
                            that.allPageTotal = res.data.allTotal;
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
        //    添加试题
            toAddQuestion(){
                this.addQuestionPanel = true;
            },
            clearQuestion(){
                // this.addQuestionPanel=false;
                this.a_className="";
                this.a_courseId="";
                this.a_title="";
                this.a_type="";
                this.a_A="";
                this.a_B="";
                this.a_C="";
                this.a_D="";
                this.a_answer="";
                this.a_explain="";
                this.e_examId = "";
            },
            confirmToAdd(){
                let true_answer = "";
                let true_optionList = "";
                //设置正确答案
                if(this.a_type == 2){
                    let arr = this.a_moreAnswer.sort();
                    for(let i=0;i<arr.length;i++){
                        if(i==arr.length-1){
                            true_answer = true_answer + arr[i]
                        }else{
                            true_answer += arr[i]+","
                        }
                    }
                }else{
                    true_answer = this.a_answer;
                }
                //设置选项
                let optionArr = [];
                let obj = null;
                if(this.a_A !== ""){
                    obj = {
                        "prefix":"A",
                        "content":this.a_A
                    };
                    optionArr.push(obj);
                }
                if(this.a_B !== ""){
                    obj = {
                        "prefix":"B",
                        "content":this.a_B
                    };
                    optionArr.push(obj);
                }
                if(this.a_C !== ""){
                    obj = {
                        "prefix":"C",
                        "content":this.a_C
                    };
                    optionArr.push(obj);
                }
                if(this.a_D !== ""){
                    obj = {
                        "prefix":"D",
                        "content":this.a_D
                    };
                    optionArr.push(obj);
                }
                true_optionList = JSON.stringify(optionArr);
                let that = this;
                //先判断填写信息是否为空
                if(this.a_courseId == "" || this.a_type == "" || this.a_title == "" || this.a_A == "" || this.a_B == "" || this.a_answer == ""){
                    that.$message.error("试题信息填写不完善");
                }else{
                    ElMessageBox.confirm("您确定要添加吗？", "提示", {
                        type: "warning",
                    }).then(() => {
                        AddQuestion({
                            courseId:that.a_courseId,
                            title:that.a_title,
                            type:that.a_type,
                            optionList:true_optionList,
                            answer:that.a_answer,
                            explain:that.a_explain
                        }).then(res=>{
                            // console.log(res);
                            if(res.status == 200){
                                ElMessage.success("添加成功");
                                that.clearQuestion();
                                that.addQuestionPanel = false;
                            }else{
                                that.$message.error("添加失败，请稍后重试");
                            }
                        }).catch(err=>{
                            that.$message.error("网络延迟");
                        })
                    }).catch(() => {});
                }
            },
            before(){
                if(this.currentPage !== 1){
                    this.currentPage--;
                    if(this.courseId != "" || this.questionType != ""){
                        this.getQuestionByType();
                    }else{
                        this.getAllQuestion();
                    }
                }
            },
            next(){
                if(this.currentPage < this.allPageTotal){
                    this.currentPage++;
                    if(this.courseId != "" || this.questionType != ""){
                        this.getQuestionByType();
                    }else{
                        this.getAllQuestion();
                    }
                }
            },
            //删除操作
            handleDelete(index, row){
                let that = this;
                // console.log(row);
                let examId = row.examId;
                ElMessageBox.confirm("您确定要删除吗？", "提示", {
                    type: "warning",
                }).then(() => {
                    // let userId = row.userId;
                    /**
                     * 根据id删除教师，发送请求
                     */
                    DeleteQuestionById({
                        examId:examId
                    }).then(res=>{
                        if(res.status == 200){
                            ElMessage.success("删除成功");
                            that.allQuestion.splice(index, 1);
                        }else{
                            that.$message.error("删除失败");
                        }
                    }).catch(err=>{
                        that.$message.error("网络异常");
                    })
                }).catch(() => {});
            },
            //    编辑
            handleEdit(index, row){
                console.log(row);
                this.editIndex = index;
                //先把数据加上去
                this.setE_Question(row);
                this.editVisible = true;
            },
            //编辑确认
            updateQuestion(){
                let true_answer = "";
                let true_optionList = "";
                //设置正确答案
                if(this.a_type == 2){
                    let arr = this.a_moreAnswer.sort();
                    for(let i=0;i<arr.length;i++){
                        if(i==arr.length-1){
                            true_answer = true_answer + arr[i]
                        }else{
                            true_answer += arr[i]+","
                        }
                    }
                }else{
                    true_answer = this.a_answer;
                }
                //设置选项
                let optionArr = [];
                let obj = null;
                if(this.a_A !== ""){
                    obj = {
                        "prefix":"A",
                        "content":this.a_A
                    };
                    optionArr.push(obj);
                }
                if(this.a_B !== ""){
                    obj = {
                        "prefix":"B",
                        "content":this.a_B
                    };
                    optionArr.push(obj);
                }
                if(this.a_C !== ""){
                    obj = {
                        "prefix":"C",
                        "content":this.a_C
                    };
                    optionArr.push(obj);
                }
                if(this.a_D !== ""){
                    obj = {
                        "prefix":"D",
                        "content":this.a_D
                    };
                    optionArr.push(obj);
                }
                true_optionList = JSON.stringify(optionArr);
                let that = this;
                //先判断填写信息是否为空
                if(this.a_courseId == "" || this.a_type == "" || this.a_title == "" || this.a_A == "" || this.a_B == "" || this.a_answer == ""){
                    that.$message.error("试题信息填写不完善");
                }else{
                    ElMessageBox.confirm("您确定要修改试题吗？", "提示", {
                        type: "warning",
                    }).then(() => {
                        UpdateQuestion({
                            examId:that.e_examId,
                            courseId:that.a_courseId,
                            title:that.a_title,
                            type:that.a_type,
                            optionList:true_optionList,
                            answer:that.a_answer,
                            explain:that.a_explain
                        }).then(res=>{
                            if(res.status == 200){
                                ElMessage.success("修改成功");
                                that.clearQuestion();
                                that.editVisible = false;
                                that.getAllQuestion();
                            }else{
                                that.$message.error("修改失败，请稍后重试");
                            }
                        }).catch(err=>{
                            that.$message.error("网络延迟");
                        })
                    }).catch(() => {});
                }
            },
            setE_Question(row){
                this.e_examId = row.examId;
                this.a_courseId = row.courseId;
                this.a_title = row.title;
                this.a_explain = row.explain;
                if(row.type == "多选题"){
                    this.a_type = "2";
                    this.a_moreAnswer = row.answer.split(",");
                }else if(row.type == "判断题"){
                    this.a_type = "3";
                    this.a_answer = row.answer;
                }else if(row.type == "单选题"){
                    this.a_type = "1";
                    this.a_answer = row.answer;
                }
                let arr = row.optionList;
                this.a_A = arr[0].content;
                this.a_B = arr[1].content;
                if(arr.length > 2){
                    if(arr[2].content != "" || arr[2].content != null){
                        this.a_C = arr[2].content;
                    }
                    if(arr[3].content != "" || arr[3].content != null){
                        this.a_D = arr[3].content;
                    }
                }
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
