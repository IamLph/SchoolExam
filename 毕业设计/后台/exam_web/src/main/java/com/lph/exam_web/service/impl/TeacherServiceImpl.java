package com.lph.exam_web.service.impl;

import com.lph.exam_web.common.Code;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.mapper.TeacherMapper;
import com.lph.exam_web.pojo.Teacher;
import com.lph.exam_web.service.TeacherService;
import com.lph.exam_web.util.Md5Salt;
import com.lph.exam_web.util.RandomCount;
import com.lph.exam_web.vo.TeacherAndTotalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Repository
public class TeacherServiceImpl implements TeacherService {

    @Autowired(required = false)
    private TeacherMapper teacherMapper;

    @Override
    public Result<Object> getAllTeacher() {
        List<Teacher> list = teacherMapper.getAllTeacher();
        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> getTeacherById(String teacherId) {
        Teacher teacher = teacherMapper.getTeacherById(teacherId);
        List<Teacher> list = new ArrayList<>();
        if(teacher == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            list.add(teacher);
            TeacherAndTotalVo t = new TeacherAndTotalVo();
            t.setTeachers(list);
            t.setAllTotal(1);
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
        }
    }

    @Override
    public Result<Object> loginTeacher(String teacherId, String password) {
        Teacher teacher = teacherMapper.getTeacherById(teacherId);
        //        System.out.println(admin);
        if(teacher == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            if(teacher.getTeacherPassword().equals(password)){
//            登陆成功
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),teacher);
            }else{
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"登陆失败");
            }
        }
    }

    @Override
    public Result<Object> getTeacherByPage(int totalPage) {
        int size = 5;
//        分页页数
        totalPage = (totalPage -1)*size;
//        获取总条数
        int allTotal = teacherMapper.getAllTotal();
        if(allTotal%size == 0){
            allTotal = (allTotal/size);
        }else{
            allTotal = (allTotal/size)+1;
        }
        List<Teacher> list = teacherMapper.getTeacherByPage(totalPage,size);

        if(list == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            TeacherAndTotalVo t = new TeacherAndTotalVo();
            t.setTeachers(list);
            t.setAllTotal(allTotal);
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),t);
        }
    }

    @Override
    public Result<Object> updateTeacherInfo(String teacherName,String teacherPassword,String teacherId) {
//        不修改密码
        if(teacherPassword == null || teacherPassword == ""){
            int a = teacherMapper.updateTeacherName(teacherName, teacherId);
//            System.out.println(a);
            if(a == 0){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
            }else{
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
            }
        }else{
//         修改密码
//            密码加盐
            Md5Salt m = new Md5Salt();
            String saltPassword = m.getMD5(teacherPassword);
            int b = teacherMapper.updateTeacher(teacherName, saltPassword, teacherId);
            if(b == 0){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"修改失败");
            }else{
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"修改成功");
            }
        }
    }

    @Override
    public Result<Object> deleteTeacherById(String teacherId) {
        int a = teacherMapper.deleteTeacherById(teacherId);
        if(a == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"删除失败");
        }else{
            return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"删除成功");
        }
    }

    @Override
    public Result<Object> addTeacher(String teacherName, String teacherPassword) {
//        获取随机账号
        RandomCount randomCount = new RandomCount();
        String teacherId = randomCount.getRandomId();
//        查询教师是否存在
        Teacher teacher = teacherMapper.getTeacherById(teacherId);
        if(teacher != null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"已经存在");
        }else{
//            密码加盐,添加教师
            Md5Salt m = new Md5Salt();
            String saltPassword = m.getMD5(teacherPassword);
            int a = teacherMapper.addTeacher(teacherId,teacherName,saltPassword);
            if(a==0){
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"添加失败");
            }else{
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),"添加成功");
            }
        }
    }

}
