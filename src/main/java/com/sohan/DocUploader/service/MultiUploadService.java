package com.sohan.DocUploader.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class MultiUploadService {
    //public String path="D:/SprngScurty/IMAGES/upload/";//path="D:\\SprngScurty\\IMAGES";

    OkHttpClient client = new OkHttpClient();
    MediaType mediaType = MediaType.parse("multipart/form-data; " );
    public void uploadAllFiles(String path,String url) throws IOException {
        File folder = new File(path);

        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            String fileName = listOfFile.getName();
            System.out.println(fileName);
            upload(path,fileName,url);
        }
    }

    public void upload(String path ,String fileName,String link) throws IOException {
        RequestBody body=new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",fileName,RequestBody.create(
                        mediaType,new File(path+fileName)
                )).build();
        Request request = new Request.Builder()
                .url(link)
                .post(body)
                .build();

        client.newCall(request).execute();
    }
}
