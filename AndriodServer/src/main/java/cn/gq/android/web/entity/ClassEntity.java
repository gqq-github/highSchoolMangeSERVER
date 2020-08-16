package cn.gq.android.web.entity;

/**
 * @author DELL
 * @create 2020/5/1 15:21
 * @update 2020/5/1
 * Description:
 * @since 1.0.0
 */
public class ClassEntity {
        private  Integer classId ;
        private String className ;

    public ClassEntity(Integer classId, String className) {
     this.classId =classId ;
     this.className = className ;
    }
   public ClassEntity () {

   }
    public Integer getClassId() {
            return classId;
        }

        public void setClassId(Integer classId) {
            this.classId = classId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
}
