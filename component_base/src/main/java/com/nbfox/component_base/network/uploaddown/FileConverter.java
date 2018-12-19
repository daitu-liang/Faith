package com.nbfox.component_base.network.uploaddown;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Field;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public class FileConverter implements Converter<ResponseBody, File> {

    /**
     * 添加请求头的key,后面数字为了防止重复
     */
    public static final String SAVE_PATH = "savePath2019";

    static final FileConverter INSTANCE = new FileConverter();

    @Override
    public File convert(ResponseBody value) {
        String saveFilePath = getSaveFilePath(value);
        return FileUtils.writeResponseBodyToDisk(value, saveFilePath);
    }

    @Nullable
    private String getSaveFilePath(ResponseBody value) {
        String saveFilePath = null;
        String requestFileName = null;
        try {

            //使用反射获得我们自定义的response
            Class aClass = value.getClass();
            Field field = aClass.getDeclaredField("delegate");
            field.setAccessible(true);
            ResponseBody body = (ResponseBody) field.get(value);
            if(body instanceof DownloadResponseBody){
                DownloadResponseBody prBody = ((DownloadResponseBody)body);
                saveFilePath = prBody.getSavePath();
                requestFileName = prBody.getFileName();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        //请求的文件名为空则根据时间戳生成一个临时文件名
        if(TextUtils.isEmpty(requestFileName)){
            requestFileName = System.currentTimeMillis()+".tmp";
        }

        //如果保存路径是一个文件夹,则在后面加上请求文件名
        if(!TextUtils.isEmpty(saveFilePath)){
            File file = new File(saveFilePath);
            if(file.isDirectory()){
                saveFilePath = saveFilePath+ File.separator+requestFileName;
            }
        }

        //如果保存路径为null则设置默认保存到sdcard根目录
        if(TextUtils.isEmpty(saveFilePath)){
            saveFilePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+requestFileName;
        }

        return saveFilePath;
    }
}