package com.lph.exam_web.controller;

import com.lph.exam_web.common.Code;
import com.lph.exam_web.common.Result;
import com.lph.exam_web.service.NoticeService;
import com.lph.exam_web.service.TeacherService;
import com.lph.exam_web.service.UserService;
import com.lph.exam_web.util.QiniuUpload;
import com.lph.exam_web.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin
@ResponseBody
@Controller
@Api(tags = "用户接口")
@RequestMapping("api/User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取学生")
    @GetMapping("/getUser")
    public Result<Object> getAllUser(){
        return userService.getAllUser();
    }

    @ApiOperation("根据ID获取用户信息")
    @GetMapping("/getUserById")
    public Result<Object> getUserById(
            @RequestParam(value = "userId",required = true) String userId){
        return userService.getUserById(userId);
    }

    /**
     * 1用户输入名字和密码即可完成注册
     * 2.自动生成账号
     * 3.判断账号是否存在，否则继续生成
     * 4.添加账号
     * @param name
     * @param userId
     * @param password
     * @return
     */
    @ApiOperation("注册用户")
    @PostMapping("/registerUser")
    public Result<Object> registerUser(@RequestParam(value = "name",required = true)String name,
                            @RequestParam(value = "userId",required = true)String userId,
                            @RequestParam(value = "password",required = true)String password){
        return userService.registerUser(name, userId, password);
    }

    @ApiOperation("用户登陆")
    @PostMapping("/loginUser")
    public Result<Object> loginUser(@RequestParam(value = "userId",required = true)String userId,
                                    @RequestParam(value = "password",required = true)String password){
        return userService.loginUser(userId,password);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/updateUserInfo")
    public Result<Object> updateUserInfo(@RequestBody  UserVo user){
        return userService.updateUserInfo(user);
    }

    
    @ApiOperation("根据ID删除用户")
    @PostMapping("/deleteUserById")
    public Result<Object> deleteUserById(@RequestParam(value = "userId",required = true)String userId){
        return userService.deleteUserById(userId);
    }

    @ApiOperation("根据ID修改用户密码")
    @PostMapping("/updateUserPasswordById")
    public Result<Object> updateUserPasswordById(@RequestParam(value = "userId")String userId,
                                                 @RequestParam(value = "password")String password){
        return userService.updateUserPassWordById(userId,password);
    }

    @ApiOperation("根据用户ID获取考试通知")
    @GetMapping("/getExamNoticeById")
    public Result<Object> getExamNoticeById() throws Exception {
//        获取关系表
        return userService.getExamNoticeById();
    }
    @ApiOperation("用户提交试卷")
    @PostMapping("/submitExamPaper")
    public Result<Object> userSubmitExamPaper(@RequestParam(value = "userId")String userId,
                                              @RequestParam(value = "score")int score,
                                              @RequestParam(value = "live")int live,
                                              @RequestParam(value = "teacherId")String teacherId,
                                              @RequestParam(value = "noticeId")String noticeId){
        return userService.submitExamPaper(userId, score, live, teacherId, noticeId);
    }

    @ApiOperation("用户获取考试记录")
    @GetMapping("/getExamHistory")
    public Result<Object> getExamHistory(@RequestParam(value = "userId")String userId){
        return userService.getExamHistory(userId);
    }

    @ApiOperation("分页获取用户")
    @GetMapping("/getUserTotalByPage")
    public Result<Object> getUserTotalByPage(@RequestParam(value = "totalPage")int totalPage){
        return userService.getUserTotalByPage(totalPage);
    }

    @ApiOperation("获取用户头像")
    @PostMapping("/updateUserHeadImg")
    public Result<Object> getUserHeadImg(@RequestParam(value = "file")MultipartFile file,@RequestParam(value = "userId")String userId){
        String name = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"headImg"+name.substring(name.lastIndexOf("."));
        try {
            String fileUrl = "http://"+ QiniuUpload.updateFile(file, fileName);
//            System.out.println(fileUrl);
            if(fileUrl != null || fileUrl != ""){
                return userService.updateUserImg(fileUrl,userId);
            }else{
                return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"图片上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"图片上传失败");
    };
}