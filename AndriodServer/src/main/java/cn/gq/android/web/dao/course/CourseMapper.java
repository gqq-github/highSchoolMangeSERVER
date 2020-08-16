package cn.gq.android.web.dao.course;

import cn.gq.android.web.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface CourseMapper {
    int addCourseMapper (Course course) ;
    Map<String,Object> selectCourseById(String id);
}
