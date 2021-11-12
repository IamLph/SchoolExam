package com.lph.exam_web.service;

import com.lph.exam_web.common.Result;
import com.lph.exam_web.vo.UserVo;

public interface UserService {
//    获取所有用户
    Result<Object> getAllUser();
//    根据ID查询用户
    Result<Object> getUserById(String userId);
//    插入用户
    Result<Object> registerUser(String name,String userId,String password);
//    用户登陆
    Result<Object> loginUser(String userId,String password);
//    用户修改信息
    Result<Object> updateUserInfo(UserVo userVo);
//    根据Id删除用户
    Result<Object> deleteUserById(String userId);
//    根据Id修改用户密码
    Result<Object> updateUserPassWordById(String userId,String password);
//    根据用户的ID获取考试的通知信息
    Result<Object> getExamNoticeById() throws Exception;
//    用户提交考试试卷
    Result<Object> submitExamPaper(String useId, int score, int live, String teacherId, String noticeId);
//    用户获取考试记录
    Result<Object> getExamHistory(String userId);
//    分页获取学生
    Result<Object> getUserTotalByPage(int totalPage);
//    根据用户Id修改头像
    Result<Object> updateUserImg(String headImg,String userId);
}
