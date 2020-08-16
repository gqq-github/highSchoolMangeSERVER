package cn.gq.android.web.service.coursetable;

import cn.gq.android.web.entity.CourseTable;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/29 16:12
 * @update 2020/4/29
 * Description:
 * @since 1.0.0
 */
public interface ICourseTableService {

    boolean saveCourseTable(CourseTable courseTable);

    boolean deleteCourseTable(CourseTable courseTable);

    List<Map<String, Object>> showCourseTable(String query);
}
