package cn.gq.android.web.service.collegeandmajors;

import cn.gq.android.web.entity.CollegesAndMajors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICollegeAndMajorService {
    List<Map<String, Object>> getColleges() ;


    boolean addCollegeAndMajors(CollegesAndMajors collegesAndMajors) throws Exception;
    List<HashMap<String, Object>> getCollegeAndMajors ();

    List<HashMap<String, Object>> getCollegeAndMajorAndClass();
}
