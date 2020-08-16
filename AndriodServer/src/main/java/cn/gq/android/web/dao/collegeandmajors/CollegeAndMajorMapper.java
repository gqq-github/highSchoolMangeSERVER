package cn.gq.android.web.dao.collegeandmajors;

import cn.gq.android.web.entity.College;
import cn.gq.android.web.entity.CollegesAndMajors;
import cn.gq.android.web.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/21 15:59
 * @update 2020/4/21
 * Description:
 * @since 1.0.0
 */
@Mapper
public interface CollegeAndMajorMapper {
    int addCollege(College college) ;
    int  addMajors (@Param("majors") List<Major> majors) ;

    List<Map<String, Object>> getCollegeAndMajor();

    List<Map<String, Object>> getColleges();

    List<Map<String, Object>> getALLInfo();
}
