package cn.gq.android.web.entity;

import java.io.Serializable;

/**
 * @author DELL
 * @create 2020/4/29 17:25
 * @update 2020/4/29
 * Description:
 * @since 1.0.0
 */
public  class CourseTableWarp implements Serializable {
    public CourseTable courseTable ;

    public CourseTable getCourseTable() {
        return courseTable;
    }

    public void setCourseTable(CourseTable courseTable) {
        this.courseTable = courseTable;
    }
}