package com.lph.exam_web.service;

import com.lph.exam_web.common.Result;

public interface AdminService {
//    获取所有
    Result<Object> getAllAdmin();
//    根据ID获取管理员并且登陆
    Result<Object> getAdminById(String adminId,String adminPassword);
}
