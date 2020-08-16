package cn.gq.android.web.controller.file;

import cn.gq.android.web.utils.MyResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author DELL
 * @create 2020/6/5 13:15
 * @update 2020/6/5
 * Description: 实现文件的上传
 * @since 1.0.0
 */
@RestController
@RequestMapping("/file")
public class FileController {
    /**
     * 接收上传的文件，并且将上传的文件存储在指定路径下
     */
    @Value("${android.file.save.file}")
    String fileRoot ;
    @PostMapping(value = "/upload")
    public MyResult upload( MultipartFile file ,Integer tshId) {
        String msg = null;
        if(file.isEmpty()) {
           msg = "file is empty" ;
        }
        String fileName = file.getOriginalFilename() ;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String path = fileRoot+fileName ;
        File localFile = new File(path) ;
        if(!localFile.getParentFile().exists())
        {
            localFile.getParentFile().mkdirs() ;
        }
        try {
            file.transferTo(localFile);
            msg = "success" ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return  MyResult.success(msg,path) ;
    }
}
