package com.alpha.module_common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class BitmapUtils {

    /**
     * 从资源文件中获取图片资源转化成bitmap
     *
     * @param context 上下文
     * @param resId 图片id
     * @return Bitmap
     */
    public static Bitmap getResBitmap(Context context, int resId) {
        Resources res = context.getResources();
        return BitmapFactory.decodeResource(res, resId);
    }

    /**
     * 屏幕截屏方法 获取当前屏幕bitmap
     *
     * @param activity 当前activity
     * @return Bitmap
     */
    public Bitmap printScreen(Activity activity) {
        View view = activity.getWindow().getDecorView();
        int width = CommonUtils.getScreenHeight(activity);
        int height = CommonUtils.getScreenHeight(activity);
        view.layout(0, 0, width, height);
        view.setDrawingCacheEnabled(true);
        return Bitmap.createBitmap(view.getDrawingCache());
    }

    /**
     * 图片缩放
     *
     * @param photo 图片
     * @param newHeight 新高度
     * @param context 上下文
     * @return Bitmap
     */
    public static Bitmap scaleBitmap(Bitmap photo, int newHeight, Context context) {
        final float densityMultiplier = context.getResources().getDisplayMetrics().density;
        int h = (int) (newHeight * densityMultiplier);
        int w = (int) (h * photo.getWidth() / ((double) photo.getHeight()));
        photo = Bitmap.createScaledBitmap(photo, w, h, true);
        return photo;
    }

    /**
     * byte[]转Bitmap
     *
     * @param bytes 字节数组
     * @return Bitmap
     */
    public static Bitmap Bytes2Bitmap(byte[] bytes) {
        if (bytes.length != 0) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return null;
    }

    /**
     * Bitmap转byte[]
     *
     * @param bitmap Bitmap
     * @return 字节数组
     */
    public static byte[] Bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 将Bitmap转换成InputStream
     *
     * @param bm Bitmap
     * @return 输入流
     */
    public InputStream Bitmap2InputStream(Bitmap bm) {
        return Bitmap2InputStream(bm, 100);
    }

    /**
     * 将Bitmap转换成InputStream
     *
     * @param bm Bitmap
     * @param quality 图片质量
     * @return 输入流
     */
    public InputStream Bitmap2InputStream(Bitmap bm, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, quality, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    /**
     * 将InputStream转换成Bitmap
     *
     * @param is 输入流
     * @return Bitmap
     */
    public Bitmap InputStream2Bitmap(InputStream is) {
        return BitmapFactory.decodeStream(is);
    }


    /**
     * Bitmap转换成Drawable
     *
     * @param bitmap Bitmap
     * @return Drawable
     */
    public Drawable bitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        return (Drawable) bd;
    }

    /**
     * Drawable转换成Bitmap
     *
     * @param drawable Drawable
     * @return Bitmap
     */
    public Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 字符串转换成Bitmap类型
     *
     * @param imgBase64Str 图片通过Base64编码成字符串
     * @return bitmap
     */
    public static Bitmap stringToBitmap(String imgBase64Str) {
        Bitmap bitmap = null;
        byte[] bitmapArray = null;
        try {
            bitmapArray = Base64.decode(imgBase64Str, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 将Bitmap转换成字符串
     *
     * @param bitmap Bitmap
     * @return 字符串
     */
    public static String bitmapToString(Bitmap bitmap) {
        String result = null;
        try {
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byte[] bytes = bStream.toByteArray();
            result = Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据图片路径读取经过压缩处理过的图片
     * 如果图片很大，不能全部加在到内存中处理，要是全部加载到内存中会内存溢出
     *
     * @param filePath 图片路径
     * @param reqWidth 要求的宽
     * @param reqHeight 要求的高
     * @return Bitmap
     */
    public static Bitmap decodeBitmapPath(String filePath, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 读取资源文件中经过压缩处理过的图片
     * 使用方法：decodeBitmapResource(getResources(), R.id.myimage, 100, 100));
     * @param res Resources对象
     * @param resId 资源id
     * @param reqWidth 要求的宽
     * @param reqHeight 要求的高
     * @return Bitmap
     */
    public static Bitmap decodeBitmapResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 计算压缩的值，谷歌官方方法
     * @param options BitmapFactory.Options
     * @param reqWidth 要求的宽
     * @param reqHeight 要求的高
     * @return 压缩值
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and  keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * 把图片变成圆角
     *
     * @param bitmap 需要修改的图片
     * @param pixels 圆角的弧度
     * @return 圆角图片
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels){
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, (float) pixels, (float) pixels, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
