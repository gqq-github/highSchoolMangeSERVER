package cn.gq.android.web.service.opencourse;

import cn.gq.android.web.entity.OpenCourse;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/27 12:07
 * @update 2020/4/27
 * Description:
 * @since 1.0.0
 */
public interface IOpenCourseService {
  boolean addOpenCourse (OpenCourse openCourse);
    List<Object> findCourseAndMajor();
}
