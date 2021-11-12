package com.lph.exam_web.service.impl;

import com.lph.exam_web.common.Code;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.mapper.UserMapper;
import com.lph.exam_web.pojo.HeadImg;
import com.lph.exam_web.pojo.User;
import com.lph.exam_web.service.UserService;
import com.lph.exam_web.util.Md5Salt;
import com.lph.exam_web.util.StampToDate;
import com.lph.exam_web.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

//    @Autowired
//    private StampToDate stampToDate;

    private dateInfo dateInfo;
    private ExamInfoDetailVo examInfoDetailVo;

    @Override
    public Result<Object> getAllUser() {
        List<User> list = userMapper.selectUser();
        if (list == null) {
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> getUserById(String userId) {
        User user = userMapper.getUserById(userId);
        List<User> list = new ArrayList<>();
        if(user == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            list.add(user);
            UserAndTotalVo t = new UserAndTotalVo();
            t.setUsers(list);
            t.setAllTotal(1);
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
        }
    }

//    注册用户
    @Override
    public Result<Object> registerUser(String name, String userId, String password) {
        User user =  userMapper.getUserById(userId);
        if(user != null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"该账号已被注册");
        }else{
//            盐值密码
            Md5Salt m = new Md5Salt();
            String saltPassword = m.getMD5(password);

            int result = userMapper.registerUser(name,userId,saltPassword);
            if(result == 0){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"注册失败");
            }
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"注册成功");
        }
    }

    @Override
    public Result<Object> loginUser(String userId, String password) {
        User user = userMapper.getUserById(userId);
        if (user == null) {
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"用户不存在");
        }else{
//            盐值验证。
            Md5Salt m = new Md5Salt();
            String saltPassword = m.getMD5(password);
//            System.out.println(saltPassword);
            if(user.getPassword().equals(saltPassword)){
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),user);
            }else {
                return new Result<>(Code.ERROR.getCode(), Code.ERROR.getDesc(), "登陆失败");
            }
        }

    }

    @Override
    public Result<Object> updateUserInfo(UserVo userVo) {
        int result =  userMapper.updateUserInfo(userVo);
//        System.out.println(result);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
    }

    @Override
    public Result<Object> deleteUserById(String userId) {
        int result = userMapper.deleteUserById(userId);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"删除失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"删除成功");
    }

    @Override
    public Result<Object> updateUserPassWordById(String userId, String password) {
//        直接盐值修改密码
        Md5Salt m = new Md5Salt();
        String saltPassword = m.getMD5(password);

        int result  = userMapper.updateUserPassWord(userId,saltPassword);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
    }

    @Override
    public Result<Object> getExamNoticeById() throws Exception {
        List<ExamInfoVo> list = userMapper.getExamNotice();
//        ll
        List<ExamInfoDetailVo> list2 = new ArrayList<>();
        for (ExamInfoVo info: list) {
            dateInfo = new dateInfo();
            dateInfo = StampToDate.getDate(info.getStartTime(),info.getEndTime());
            int surTime = StampToDate.getSurTime(info.getStartTime());
            examInfoDetailVo = new ExamInfoDetailVo();
            examInfoDetailVo.setId(info.getNoticeId());
            examInfoDetailVo.setTime(dateInfo.getTime());
            examInfoDetailVo.setType(info.getType());
            examInfoDetailVo.setTeacherId(info.getTeacherId());
            examInfoDetailVo.setTeacher(info.getTeacherName());
            examInfoDetailVo.setDetailTime(dateInfo.getStartTime()+"-"+dateInfo.getEndTime());
            examInfoDetailVo.setSurTime(surTime);
            list2.add(examInfoDetailVo);
        }
        if (list2 == null) {
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list2);
    }

    @Override
    public Result<Object> submitExamPaper(String userId, int score, int live, String teacherId, String noticeId) {
//        需要先判断下是否提交过了
        int isSubmit = userMapper.getIsSubmit(userId, noticeId);
//        System.out.println(isSubmit);
        if(isSubmit == 0){
            int result = userMapper.submitExamPaper(userId, score, live, teacherId, noticeId);
            if (result == 0) {
                return new Result<>(Code.ERROR.getCode(), Code.ERROR.getDesc(), "提交失败");
            } else {
                return new Result<>(Code.SUCCESS.getCode(), Code.SUCCESS.getDesc(), "提交成功");
            }
        }else{
            return new Result<>(Code.ERROR.getCode(), Code.ERROR.getDesc(), "已经考过了!!");
        }

    }

    @Override
    public Result<Object> getExamHistory(String userId) {
        List<ExamHistory> list = userMapper.getExamHistory(userId);
        if(list.size() == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
        }
    }

    /**
     * 分页获取学生
     */
    @Override
    public Result<Object> getUserTotalByPage(int totalPage) {
        int size = 5;
        totalPage = (totalPage -1)*size;
//        获取总条数
        int allTotal = userMapper.getAllTotal();
        if(allTotal%size == 0){
            allTotal = (allTotal/size);
        }else{
            allTotal = (allTotal/size)+1;
        }
        List<User> list = userMapper.getUserByPage(totalPage,size);

        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            UserAndTotalVo t = new UserAndTotalVo();
            t.setUsers(list);
            t.setAllTotal(allTotal);
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
        }
    }

    @Override
    public Result<Object> updateUserImg(String headImg, String userId) {
        int result = userMapper.updateUserImg(headImg, userId);
        if(result == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
        }else{
            HeadImg headImg1 = new HeadImg();
            headImg1.setHeadImg(headImg);
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),headImg1);
        }
    }
}
