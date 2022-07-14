package com.sohan.DocUploader.controller;

import com.sohan.DocUploader.service.SingleTextUploadService;
import com.sohan.DocUploader.service.ImageUploadService;
import com.sohan.DocUploader.service.MultiUploadService;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class UploadController {
    private ImageUploadService imageUploadService;
    private MultiUploadService multiUploadService;
    private SingleTextUploadService singleTextUploadService;

    public UploadController(ImageUploadService imageUploadService,
                            MultiUploadService multiUploadService,
                            SingleTextUploadService singleTextUploadService) throws IOException {
        this.imageUploadService = imageUploadService;
        this.multiUploadService = multiUploadService;
        this.singleTextUploadService = singleTextUploadService;
        getMultiUpload();
    }

    public void getSingleText() throws IOException {
        singleTextUploadService.call();
    }
    public void getSingleUpload() throws IOException {
        String path="D:/SprngScurty/IMAGES/upload/";
        String file=path+"image02.jpg";
        imageUploadService.upload(path,file);
    }
    public void getMultiUpload() throws IOException {
        String url="http://localhost:8080/upload";
        String path="D:/SprngScurty/IMAGES/random/";
        multiUploadService.uploadAllFiles(path,url);
    }

}
