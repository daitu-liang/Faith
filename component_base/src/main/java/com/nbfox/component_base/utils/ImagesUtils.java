package com.nbfox.component_base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.nbfox.component_base.app.BaseApplicattion;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class ImagesUtils {

    private static final String TAG = ImagesUtils.class.getSimpleName();

    public static File saveBitmapFile(String srcPath) {
//        return saveBitmapFile(getimage(srcPath));
        return saveBitmap(getimage(srcPath));
    }

    public static String getDiskCachePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath();
        } else {
            return context.getCacheDir().getPath();
        }
    }
    public static File saveBitmapFile(Bitmap bitmap) {
        //压缩好比例大小后再进行质量压缩
        bitmap = compressImage(bitmap);
        File file = new File(getDiskCachePath(BaseApplicattion.getBaseApplicattion()));//将要保存图片的路径
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            Log.d("ImagesUtils", "###宽：" + bitmap.getWidth() + "   ###高：" + bitmap.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    private static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 60, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 80;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        Log.d("ImagesUtils", "质量压缩后的宽：" + bitmap.getWidth() + "   质量压缩后的高：" + bitmap.getHeight());
        return bitmap;
    }

    private static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 2;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

//        Log.d("ImagesUtils", "缩放后的宽：" + bitmap.getWidth() + "   缩放后的高：" + bitmap.getHeight());
        return bitmap;
    }

    public static File saveBitmap(Bitmap bitmap) {
        return saveBitmap(bitmap, BaseApplicattion.getBaseApplicattion().getCacheDir() + "/img", System.currentTimeMillis() + ".jpg");
    }

    /**
     * 保存图片到指定路径
     */
    public static File saveBitmap(Bitmap bitmap, String path, String name) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        f = new File(path, name);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {

                }
        }
        return f;
    }

    /**
     * 重命名某个文件的名字
     *
     * @param file
     * @param newName
     */
    public static boolean reNameFile(File file, String newName) {
        if (file != null && !file.isDirectory() && file.exists()) {
            return file.renameTo(new File(file.getParent() + "/" + newName));
        }
        return false;
    }

    /**
     * 指定某个imageview旋转
     *
     * @param from
     * @param to
     * @param imageView
     */
  /*  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void rotationExpandIcon(float from, float to, ImageView imageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
            valueAnimator.setDuration(300);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(animator -> {
                imageView.setRotation((Float) animator.getAnimatedValue());
            });
            valueAnimator.start();
        }
    }*/
}
