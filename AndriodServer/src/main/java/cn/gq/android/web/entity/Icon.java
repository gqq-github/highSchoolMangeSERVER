package cn.gq.android.web.entity;

/**
 * @author DELL
 * @create 2020/4/11 13:13
 * @update 2020/4/11
 * Description:
 * @since 1.0.0
 */
public class Icon {
    private  String iconName ;
    private byte [] image ;
    private  Integer role ;

    public Icon(String iconName, byte[] image, Integer role) {
        this.iconName = iconName;
        for (int i = 0; i < image.length ; i++) {
            if(image[i]<0) {
                image[i] += 256 ;
            }
        }
        this.role = role;
        this.image = image;


    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
