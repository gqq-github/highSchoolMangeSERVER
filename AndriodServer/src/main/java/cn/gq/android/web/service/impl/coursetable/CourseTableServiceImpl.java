package cn.gq.android.web.service.impl.coursetable;

import cn.gq.android.web.dao.coursetable.CourseTableMapper;
import cn.gq.android.web.entity.CourseTable;
import cn.gq.android.web.service.coursetable.ICourseTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/29 16:14
 * @update 2020/4/29
 * Description:
 * @since 1.0.0
 */
@Service
public class CourseTableServiceImpl implements ICourseTableService {
    @Autowired
    CourseTableMapper courseTableMapper ;
    @Override
    public boolean saveCourseTable(CourseTable courseTable) {
       int  i = courseTableMapper.addCourseTable(courseTable) ;
        return i>0;
    }
    @Transactional
    @Override
    public boolean deleteCourseTable(CourseTable courseTable) {
       int i = courseTableMapper.updateCourseTable(courseTable);
        return i>0;
    }

    @Override
    public List<Map<String, Object>> showCourseTable(String query) {

        return courseTableMapper.selectCourseTableByClassName(query);
    }
}
