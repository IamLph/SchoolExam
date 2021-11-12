import { http } from './http.js';

// 根据id获取用户
export function getUserById(params){
	http('/User/getUserById','post',params)
}

// 用户登陆
export function loginUser(params){
	http('/User/loginUser','post',params)
}

// 查询所有用户
export function getAllUser(params){
	http('/User/getUser','get',params)
}

// 注册账号
export function registerUser(params){
	http('/User/registerUser','post',params)
}
// 修改用户信息
export function updateUserInfo(params){
	http('/User/updateUserInfo','post',params)
}

// 获取考试通知
export function getExamInfoById(params){
	http('/User/getExamNoticeById','get',params)
}
 
// 获取所有课程
export function getAllCourse(params){
	http('/Course/getAllCourse','get',params)
}

// 根据课程id获取视频
export function getVideoByClassId(params){
	http('/Video/getVideoByClassId','get',params)
}

// 根据用户id获取视频学习信息
export function getVideoInfoByUserId(params){
	http('/Video/getVideoInfoByUserId','get',params)
}

// 更新视频进度
export function updateVideoInfoByUserIdAndVideoId(params){
	http('/Video/updateVideoInfoByUserIdAndVideoId','post',params)
}
// 获取练习题
export function getQuestionByCourseIdAndType(params){
	http('/Question/getQuestionByCourseIdAndType','get',params)
}
// 获取收藏的题目
export function updateFavor(params){
	http('/Question/updateFavor','get',params)
}
// 获取随机综合试题
export function getQuestionByCourseIdType4(params){
	http('/Question/getQuestionByCourseIdType4','get',params)
}
// 错题添加与修改
export function updateUserWrong(params){
	http('/Question/updateUserWrong','post',params)
}
// 获取收藏的试题
export function getUserFavor(params){
	http('/Question/getFavorQuestion','get',params)
}
// 获取错题集
export function getUserWrong(params){
	http('/Question/getWrongQuestion','get',params)
}
// 根据通知Id获取考试通知信息
export function getNiticeByNoticeId(params){
	http('/Notice/getNoticeById','get',params)
}
// 获取试卷
export function getExamPaper(params){
	http('/Question/getExamPaper','get',params)
}
// 提交试卷
export function submitExamPaper(params){
	http('/User/submitExamPaper','post',params)
}
// 获取考试记录
export function getExamHistory(params){
	http('/User/getExamHistory','get',params)
}
