package cn.gq.android.web.dao.coursetable;

import cn.gq.android.web.entity.CourseTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/29 16:15
 * @update 2020/4/29
 * Description:
 * @since 1.0.0
 */
@Mapper
public interface CourseTableMapper {
    int addCourseTable(CourseTable courseTable);


    int updateCourseTable(CourseTable courseTable);

    List<Map<String, Object>> selectCourseTableByClassName(@Param("query") String query);
}
