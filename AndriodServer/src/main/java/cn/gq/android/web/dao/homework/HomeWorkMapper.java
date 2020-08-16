package cn.gq.android.web.dao.homework;

import cn.gq.android.web.entity.SendHomeWork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Mapper
public interface HomeWorkMapper {
    // 教师发布作业
    Integer saveHomeWork (SendHomeWork sendHomeWork) ;
    // 为学生展示作业信息
    List<Map<String,Object>> showHomeWorkToStudent (String studentId);
}
