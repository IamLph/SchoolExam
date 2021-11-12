import {get, post, baseUrl} from './http'

//教师或者管理员进行登陆
export const LoginAdmin = params => get("/Admin/getAllAdmin",params);
export const LoginTeacher = params => get("/Teacher/loginTeacher",params);


//分页获取教师信息
export const GetTeacherByPage = params => get("/Teacher/getTeacherByPage",params);
//修改教师信息
export const UpdateTeacherInfo = params => post("/Teacher/updateTeacherInfo",params);
//删除教师
export const DeleteTeacherById = params => post("/Teacher/deleteTeacherById",params);
//添加教师
export const addTeacher = params => post("/Teacher/addTeacher",params);
//根据id查询教师
export const getTeacherById = params => get("/Teacher/getTeacherById",params);
//获取所有教师
export const GetAllTeacher = params => get("/Teacher/getAllTeacher",params);

//分页获取学生信息
export const GetUserByPage = params => get("/User/getUserTotalByPage",params);
//根据id搜索用户
export const GetUserById = params => get("/User/getUserById",params);
//根据id删除用户
export const DeleteUserById = params => post("/User/deleteUserById",params);
//添加用户
export const AddUser = params => post("/User/registerUser",params);
//修改用户信息
export const UpdateUserInfo = params => post("/User/updateUserInfo",params);
//修改用户密码
export const UpdateUserPassword = params => post("/User/updateUserPasswordById",params);

//分页获取试题
export const GetQuestionByTotalPage = params => get("/Question/getQuestionByPage",params);
//搜索获取试题
export const GetQuestionById = params => get("/Question/getQuestionById",params);
//获取所有课程
export const GetAllCourse = params => get("/Course/getAllCourse",params);
//根据类型获取课程
export const GetQuestionByType = params => get("/Question/getQuestionByType",params);
//根据Id删除试题
export const DeleteQuestionById = params => post("/Question/deleteQuestionByExamId",params);
//添加试题
export const AddQuestion = params => post("/Question/addQuestion",params);
//修改试题
export const UpdateQuestion = params => post("/Question/updateQuestion",params);

//根据课程以及类型分页获取试题
export const GetQuestionToSelect = params => get("/Question/getQuestionByCourseIdAndTypeByPage",params);
//添加考试通知
export const AddNotice = params => post("/Notice/addNotice",params);
//添加试卷
export const addExamPaper = params => post("/Notice/addExamPaper",params);
//获取所有发布的考试通知
export const GetAllNotice = params => get("/Notice/getAllNotice",params);
//分页获取考试通知
export const GetNoticeByTotal = params => get("/Notice/getNoticeByTotal",params);
//根据课程或则通知Id获取通知
export const GetNoticeByCourseIdOrNoticeId = params => get("/Notice/getNoticeByCourseIdAndNoticeIdByPage",params);
//通知状态修改操作
export const UpdateNoticeStatusById = params => post("/Notice/updateNoticeInfoById",params);

//根据Id获取课程
export const GetCourseById = params => get("/Course/getCourseById",params);
//上传课程包括文件
export const AddCourse = baseUrl+"/Course/uploadFile";
//修改课程信息
export const UpdateCourse = params => post("/Course/updateCourseById",params);
//删除课程
export const DeleteCourse = params => post("/Course/deleteCourseById",params);
//根据课程号获取视频
export const GetVideoByCourseId = params => get("/Video/getVideoByClassId",params);
//编辑视频信息
export const UpdateVideoInfo = baseUrl+"/Course/updateVideoInfo";
//添加视频信息
export const AddVideoInfo = baseUrl+"/Course/addVideoInfo";
//根据Id删除视频
export const DeleteVideoById = params => post("/Video/deleteVideoById",params);
//获取考试结果
export const GetExamResult = params => get("/Notice/getExamResult",params);
//根据考试id获取考生情况
export const GetExamByNoticeId = params => get("/Notice/getExamByNoticeId",params);
//保存用户的成绩（修改）
export const SetScoreByUserIdAndNoticeId = params => post("/Notice/setScoreByUserIdAndNoticeId",params);
//修改分数公布状态
export const SetScorePublic = params => post("/Notice/setScorePublic",params);
//根据通知Id获取考试试卷
export const GetExamResultByNoticeId = params => get("/Notice/getExamResultByNoticeId",params);
