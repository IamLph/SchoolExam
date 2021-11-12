package com.lph.exam_web.service.impl;

import com.lph.exam_web.common.Code;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.mapper.AdminMapper;
import com.lph.exam_web.pojo.Admin;
import com.lph.exam_web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Override
    public Result<Object> getAllAdmin() {
        List<Admin> list = adminMapper.getAllAdmin();
        if(list.size() == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }
        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result<Object> getAdminById(String adminId,String adminPassword) {
        Admin admin = adminMapper.getAdminById(adminId);
//        System.out.println(admin);
        if(admin == null){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"数据为空");
        }else{
            if(admin.getAdminPassword().equals(adminPassword)){
//            登陆成功
                return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),admin);
            }else{
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"登陆失败");
            }
        }
    }
}
