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

    public String path="D:/SprngScurty/IMAGES/upload/";//path="D:\\SprngScurty\\IMAGES";

    OkHttpClient client = new OkHttpClient();
    MediaType mediaType = MediaType.parse("multipart/form-data; " );
    List<String> docList=new ArrayList<>();
    public List<String> getAllFiles(){
        File folder = new File(path);

        File[] listOfFiles = folder.listFiles();
        for(int i=0;i<listOfFiles.length;i++){
            docList.add(listOfFiles[i].getName());
        }
        System.out.println(docList);
        return docList;
    }

    public void upload() throws IOException {
//        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r" +
//                "\nContent-Disposition: form-data; name=\"file\"; filename=\"image02.jpg\"" +
//                "\r\nContent-Type: image/jpeg\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        RequestBody fbody=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",getAllFiles().get(3),RequestBody.create(
                        mediaType,new File(path+getAllFiles().get(3))
                )).build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/upload")
                .post(fbody)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "96700d83-a4c0-f30d-4826-791278ad679c")
                .build();

        Response response = client.newCall(request).execute();
    }

    public ImageUploadService() throws IOException {
        upload();
        getAllFiles();
    }
}
