package cn.gq.android.web.dao.opencourse;

import cn.gq.android.web.entity.OpenCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/27 12:23
 * @update 2020/4/27
 * Description:
 * @since 1.0.0
 */
@Mapper
public interface OpenCourseMapper {

    int addOpenCourseMapper(OpenCourse openCourse);

    List<Map<String,Object>> findOpenCourseMajorAndCourse();
}
