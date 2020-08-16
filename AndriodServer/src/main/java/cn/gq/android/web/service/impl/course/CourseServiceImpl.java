package cn.gq.android.web.service.impl.course;

import cn.gq.android.web.dao.course.CourseMapper;
import cn.gq.android.web.entity.Course;
import cn.gq.android.web.service.cource.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DELL
 * @create 2020/4/20 20:17
 * @update 2020/4/20
 * Description:
 * @since 1.0.0
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper ;
    @Override
    public boolean addCourse(Course course) {

        int i = courseMapper.addCourseMapper(course);
        return i>0;
    }
}
