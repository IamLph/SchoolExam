package com.lph.exam_web.mapper;

import com.lph.exam_web.pojo.Video;
import com.lph.exam_web.vo.UserVideoVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VideoMapper {

    @Select("select * from videoinfo")
    List<Video> getAllVideo();
//根据课程Id获取视频
    @Select("select * from videoinfo where classId = #{classId}")
    List<Video> getVideoByClassId(String classId);

    @Select("select videoinfo.id,videoinfo.classId,videoinfo.videoUrl,videoinfo.videoName,videoinfo.videoInfo,videoinfo.videoUnit,videoinfo.publishTime,videoinfo.videoImg,uservideo.initTime,uservideo.remainTime,uservideo.videoIsOk from videoinfo,uservideo where videoinfo.id = uservideo.videoId and uservideo.userId = #{userId}  and videoinfo.classId = #{classId}")
    List<UserVideoVo> getVideoInfoByUserId(String userId,String classId);

    @Update("update uservideo set initTime = #{initTime},remainTime = #{remainTime},videoIsOk = #{videoIsOk} where userId=#{userId} and videoId=#{videoId}")
    int updateUserVideo(String userId,String videoId,String initTime,String remainTime,String videoIsOk);
//    删除
    @Delete("delete from videoinfo where id = #{id}")
    int deleteVideoById(String id);

}
