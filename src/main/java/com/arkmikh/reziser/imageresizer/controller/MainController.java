package com.arkmikh.reziser.imageresizer.controller;

import com.arkmikh.reziser.imageresizer.resizer.ImageResizer;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@PropertySource("classpath:application.properties")
@Controller
public class MainController {

    // value read from myApp.properties
    @Value("${imagesToDownloadRootPath}")
    String dwnRootPath;
    @Value("${imagesToUploadRootPath}")
    String uplRootPath;

    // default main page
    @RequestMapping("/")
    public String showDefaultView(){
        return "default-view";
    }

    // download button action
    @PostMapping("/uploadImage")
    public String uploadImage(
            @RequestParam("downloadedImg") MultipartFile[] multipartFiles,
            HttpServletRequest request) {
        // value for filename
        String name;

        for(MultipartFile file: multipartFiles){



            // checks if there any file
            if(!file.isEmpty()){
                try {
                    byte[] bytes = file.getBytes();
                    name = file.getOriginalFilename();

                    File dir = new File(dwnRootPath + File.separator);

                    // checks directory exist and creates if not
                    boolean isDirectoryCreated = dir.exists();
                    if(!isDirectoryCreated){
                        isDirectoryCreated = dir.mkdirs();
                    }
                    if(isDirectoryCreated){
                        // if directory exists, write into rootPath
                        File uploadedImage = new File(dir.getAbsolutePath() + File.separator + name);
                        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedImage));
                        stream.write(bytes);
                        stream.flush();
                        stream.close();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }


        // width and height from http fields
        int width = Integer.parseInt(request.getParameter("imageScaledWidth"));
        int height = Integer.parseInt(request.getParameter("imageScaledHeight"));
        // extension from http field
        String extension = request.getParameter("extension");

        // calls resize from ImageResizer Class
        ImageResizer imageResizer = new ImageResizer();
        try {
            imageResizer.resize(dwnRootPath, uplRootPath, width, height, extension);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return "default-view";
    }

}
