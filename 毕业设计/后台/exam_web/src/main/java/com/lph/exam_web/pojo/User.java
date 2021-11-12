package com.lph.exam_web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String name;
    private String userId;
    private String password;
    private String headImg;
    private int sex;
    private String nation;
    private String nationPlace;
    private String phone;
    private String department;
    private String grade;
    private String branch;

//    public User() {
//    }
//
//    public User(int id, String name, String userId, String password, String headImg, int sex, String nation, String nationPlace, String phone, String department, String grade, String branch) {
//        this.id = id;
//        this.name = name;
//        this.userId = userId;
//        this.password = password;
//        this.headImg = headImg;
//        this.sex = sex;
//        this.nation = nation;
//        this.nationPlace = nationPlace;
//        this.phone = phone;
//        this.department = department;
//        this.grade = grade;
//        this.branch = branch;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getHeadImg() {
//        return headImg;
//    }
//
//    public void setHeadImg(String headImg) {
//        this.headImg = headImg;
//    }
//
//    public int getSex() {
//        return sex;
//    }
//
//    public void setSex(int sex) {
//        this.sex = sex;
//    }
//
//    public String getNation() {
//        return nation;
//    }
//
//    public void setNation(String nation) {
//        this.nation = nation;
//    }
//
//    public String getNationPlace() {
//        return nationPlace;
//    }
//
//    public void setNationPlace(String nationPlace) {
//        this.nationPlace = nationPlace;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    public String getGrade() {
//        return grade;
//    }
//
//    public void setGrade(String grade) {
//        this.grade = grade;
//    }
//
//    public String getBranch() {
//        return branch;
//    }
//
//    public void setBranch(String branch) {
//        this.branch = branch;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", userId='" + userId + '\'' +
//                ", password='" + password + '\'' +
//                ", headImg='" + headImg + '\'' +
//                ", sex=" + sex +
//                ", nation='" + nation + '\'' +
//                ", nationPlace='" + nationPlace + '\'' +
//                ", phone='" + phone + '\'' +
//                ", department='" + department + '\'' +
//                ", grade='" + grade + '\'' +
//                ", branch='" + branch + '\'' +
//                '}';
//    }
}
