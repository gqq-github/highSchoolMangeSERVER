package cn.gq.android.web.service.impl.collegeandmajors;

import cn.gq.android.web.dao.collegeandmajors.CollegeAndMajorMapper;
import cn.gq.android.web.entity.ClassEntity;
import cn.gq.android.web.entity.College;
import cn.gq.android.web.entity.CollegesAndMajors;
import cn.gq.android.web.entity.Major;
import cn.gq.android.web.service.collegeandmajors.ICollegeAndMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author DELL
 * @create 2020/4/21 21:20
 * @update 2020/4/21
 * Description:
 * @since 1.0.0
 */
@Service
public class CollegeAndMajorServiceImpl implements ICollegeAndMajorService {
    @Autowired
    private CollegeAndMajorMapper collegeAndMajorMapper;

    @Override
    public List<Map<String, Object>> getColleges() {

        return collegeAndMajorMapper.getColleges();
    }

    @Override
    @Transactional
    public boolean addCollegeAndMajors(CollegesAndMajors collegesAndMajors) throws Exception {
        College college = new College();
        college.setCname(collegesAndMajors.getCname());
        int i1 = 0;
        collegeAndMajorMapper.addCollege(college);
        Integer collegeId = college.getCid();
        if (collegeId != null) {
            List<Major> majors = new ArrayList<>();
            for (int i = 0; i < collegesAndMajors.getMajors().size(); i++) {
                Major major = new Major();
                major.setMname(collegesAndMajors.getMajors().get(i));
                major.setCollegeId(collegeId);
                majors.add(major);

            }
            i1 = collegeAndMajorMapper.addMajors(majors);
            if (i1 <= 0) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    @Override
    public List<HashMap<String, Object>> getCollegeAndMajors() {
        List<Map<String, Object>> collegeAndMajor = collegeAndMajorMapper.getCollegeAndMajor();
        return delcollegeAndMajorDataToJSONObject(collegeAndMajor);
    }

    @Override
    public List<HashMap<String, Object>> getCollegeAndMajorAndClass() {
        List<Map<String, Object>> res = collegeAndMajorMapper.getALLInfo();
        return delCollegeAndMajorAndClass(res);
    }

    private List<HashMap<String, Object>> delCollegeAndMajorAndClass(List<Map<String, Object>> res) {
        if (res == null || res.size() == 0) {
            return null;
        }
        HashMap<String, HashMap<String, Object>> vis = new HashMap<>();
        List<Major> majors;
        List<ClassEntity> classEntities;
        HashMap<String,Object> item ;
        HashMap <String,Object> majorMap ;
        List<HashMap<String,Object>> majorsList ;
        int majorPosition = 0 ;
        List<HashMap<String,Object>> result = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            Map temp = res.get(i);
            Integer collegeId = (Integer) temp.get("collegeId");
            Integer majorId = (Integer) temp.get("majorId");
            Integer classId = (Integer) temp.get("classId");
            String collegeName = (String) temp.get("collegeName");
            String majorName = (String) temp.get("majorName");
            String className = (String) temp.get("className");
            ClassEntity classEntity = new ClassEntity(classId,className);
            if((item = vis.get(collegeId+collegeName))!=null) {
                if(item.get(majorId+majorName)!=null) {
                  majorsList = (List<HashMap<String, Object>>) item.get("majors");
                  majorMap = majorsList.get((int)item.get(majorId+majorName));
                  classEntities = (List<ClassEntity>) majorMap.get("classs");
                 classEntities.add(classEntity);
                }else {
                    majorMap = new HashMap<>() ;
                    majorMap.put("majorId",majorId);
                    majorMap.put("majorName",majorName);
                    classEntities = new ArrayList<>() ;
                    classEntities.add(classEntity);
                    majorMap.put("classs",classEntities);
                   majorsList = (List<HashMap<String, Object>>) item.get("majors");
                   majorsList.add(majorMap);
                   item.put(majorId+majorName,majorPosition++);
                }
            } else  {
                majorPosition = 0;
               item = new HashMap<>() ;
               item.put("collegeId",collegeId);
               item.put("collegeName",collegeName);
               item.put(majorId+majorName,majorPosition++);
               majorsList = new ArrayList<>() ;
               majorMap = new HashMap<>() ;
               majorMap.put("majorId",majorId);
               majorMap.put("majorName",majorName);
               classEntities = new ArrayList<>() ;
               classEntities.add(classEntity);
               majorMap.put("classs",classEntities);
               majorsList.add(majorMap);
               item.put("majors",majorsList);
               vis.put(collegeId+collegeName,item);
               result.add(item);
            }
        }
        return  result ;
    }

    public List<HashMap<String, Object>> delcollegeAndMajorDataToJSONObject(List<Map<String, Object>> collegeAndMajor) {
        if (collegeAndMajor == null || collegeAndMajor.size() == 0) {
            // 返回空值来提示Admin进行添加学院专业信息
            return null;
        }

        List<HashMap<String, Object>> res = new ArrayList<>();
        List<Major> majors;
        // 用来标记当前得到的数据是否重复
        HashMap<String, HashMap<String, Object>> vis = new HashMap<>();
        HashMap<String, Object> maps;
        for (int i = 0; i < collegeAndMajor.size(); i++) {
            Map<String, Object> tempMap = collegeAndMajor.get(i);

            Integer cid = (Integer) tempMap.get("cid");
            String cname = (String) tempMap.get("cname");
            Integer mid = (Integer) tempMap.get("mid");
            String mname = (String) tempMap.get("mname");
            Major major = new Major();
            major.setMid(mid);
            major.setMname(mname);
            major.setCollegeId(cid);
            if ((maps = vis.get(cid + cname)) != null) {
                majors = (List<Major>) maps.get("majors");
                majors.add(major);
            } else {
                maps = new HashMap<>();
                maps.put("cid", cid);
                maps.put("cname", cname);
                majors = new ArrayList<>();
                majors.add(major);
                maps.put("majors", majors);
                vis.put(cid + cname, maps);
            }
        }
        for (Map.Entry<String, HashMap<String, Object>> entry : vis.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
}
