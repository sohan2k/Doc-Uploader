package com.sohan.DocUploader.service;

import com.sohan.DocUploader.model.Doc;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ImageUploadService {

    //public String path="D:/SprngScurty/IMAGES/upload/";//path="D:\\SprngScurty\\IMAGES";

    OkHttpClient client = new OkHttpClient();
    MediaType mediaType = MediaType.parse("multipart/form-data; " );

    public void upload(String filename,String uploadedFile) throws IOException {
        RequestBody fbody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",filename,RequestBody.create(
                        mediaType,new File(uploadedFile)
                )).build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/upload")
                .post(fbody)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "96700d83-a4c0-f30d-4826-791278ad679c")
                .build();

        client.newCall(request).execute();
    }

//    public ImageUploadService() throws IOException {
//        upload();
//        getAllFiles();
//    }
}
