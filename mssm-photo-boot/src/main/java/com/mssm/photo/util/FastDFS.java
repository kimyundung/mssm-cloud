package com.mssm.photo.util;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FastDFS {

    private static TrackerClient trackerClient;


    static {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            trackerClient = new TrackerClient();
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    public static String uploadPhoto(MultipartFile file) throws Exception {

        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        NameValuePair[] list = new NameValuePair[1];
        list[0] = new NameValuePair("fileName",file.getOriginalFilename());
        String fileID = client.upload_file1(file.getBytes(), "jpg", list);
        System.out.println("fileID = " + fileID);

        return fileID;
    }

    // 0 for success, none zero for fail (error code)
    public static int deletePhoto(String fileID) throws Exception {
        System.out.println(".....................delete : " + fileID);

        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        int i = client.delete_file1(fileID);

        return i;
    }
}
