package com.lph.exam_web.mapper;

import com.lph.exam_web.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminMapper {
    @Select("select * from admin")
    List<Admin> getAllAdmin();

    @Select("select * from admin where adminId = #{adminId}")
    Admin getAdminById(String adminId);
}
