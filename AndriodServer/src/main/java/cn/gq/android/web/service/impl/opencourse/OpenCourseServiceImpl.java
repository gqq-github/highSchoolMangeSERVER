package cn.gq.android.web.service.impl.opencourse;

import cn.gq.android.web.dao.course.CourseMapper;
import cn.gq.android.web.dao.opencourse.OpenCourseMapper;
import cn.gq.android.web.entity.OpenCourse;
import cn.gq.android.web.entity.TempCourse;
import cn.gq.android.web.entity.TempData;
import cn.gq.android.web.service.opencourse.IOpenCourseService;
import groovy.grape.GrapeIvy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * @author DELL
 * @create 2020/4/27 12:14
 * @update 2020/4/27
 * Description:
 * @since 1.0.0
 */
@Service
public class OpenCourseServiceImpl implements IOpenCourseService {
    @Autowired
    private OpenCourseMapper openCourseMapper ;
    @Autowired
    private CourseMapper courseMapper ;
    @Transactional
    public boolean
    addOpenCourse(OpenCourse openCourse) {
        Map<String, Object> map = courseMapper.selectCourseById(openCourse.getCourseId());
        if(map==null){
            // 表示输入的课程编号是错误的
            return  false ;
        }
        int i = openCourseMapper.addOpenCourseMapper(openCourse);
        return i>0;
    }

    @Override
    public List<Object> findCourseAndMajor() {
        List<Map<String, Object>> openCourseMajorAndCourse = openCourseMapper.findOpenCourseMajorAndCourse();
        return delData(openCourseMajorAndCourse);
    }
    public List<Object> delData (List<Map<String,Object>> data) {
      if(data==null || data.size()==0) {
          return null ;
      }
      HashMap <String , TempData> vis = new HashMap<>() ;
       TempData tempData ;
       List<Object> res = new ArrayList<>() ;
      for (int i = 0 ; i<data.size() ; i++) {
          Map temp = data.get(i) ;
          Integer  cmitd = (Integer) temp.get("cmtid");
          Integer majorId = (Integer) temp.get("majorid");
          String   majorName = (String) temp.get("mname");
          Integer  courseType = (Integer) temp.get("coursetype");
          String courseId = (String)temp.get("courseid") ;
          String courseName = (String) temp.get("cname");
          String  teacherName = (String) temp.get("teachername") ;
          TempCourse tempCourse = new TempCourse(courseName, courseId, courseType, teacherName);
          tempCourse.cmtId = cmitd ;
          if((tempData=vis.get(majorId+majorName))!=null) {
              tempData.courses.add(tempCourse);
          }else {
              ArrayList<TempCourse> tempCourses = new ArrayList<>();
              tempCourses.add(tempCourse);
              tempData = new TempData(majorId,majorName,tempCourses);
              vis.put(majorId+majorName,tempData);
          }
      }
      for (String key:vis.keySet())
          res.add(vis.get(key));
      return res ;
    }
}
