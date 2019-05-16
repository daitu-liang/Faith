package com.nbfox.component_base.network.uploaddown;


import com.nbfox.component_base.utils.image.ImageFileUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by leixiaoliang on 2017/6/6.
 * 邮箱：lxliang1101@163.com
 */

public class UploadPart {
    public static  MultipartBody.Part getFilePart(String partName, String filePath){
        File file = ImageFileUtils.saveBitmapFile(filePath);//压缩处理
//        File file=new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data;charset=utf-8"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
        return body;
    }
    public static  MultipartBody.Part getFilePartNoPrees(String partName, String filePath){
//        File file = ImageFileUtils.saveBitmapFile(filePath);//压缩处理
        File file=new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data;charset=utf-8"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
        return body;
    }
    /**
     * 将文字参数类型转换为“text/plain”
     * @param value
     * @return
     */
    public static RequestBody toRequestBody(String value){
        return RequestBody.create(MediaType.parse("multipart/form-data"),value);
    }
}
