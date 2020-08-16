package cn.gq.android.web.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author DELL
 * @create 2020/6/5 17:31
 * @update 2020/6/5
 * Description:
 * @since 1.0.0
 */
public class FileUtils  {


    // 将文件保存到本地  返回文件
    public static  String uploadFile (MultipartFile file , String fileRoot) {

        String fileName = System.currentTimeMillis()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String path = fileRoot+fileName ;
        File localFile = new File(path) ;
        if(!localFile.getParentFile().exists())
        {
            localFile.getParentFile().mkdirs() ;
        }
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
            fileName = null ;
        } finally {

        }
        return  fileName ;
    }
}
