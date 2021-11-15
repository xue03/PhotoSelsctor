package com.work.photoselector.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.work.photoselector.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:2021/11/15
 * Description:
 * Author:XueTingTing
 */
public class ImageDataSource {

    public static List<ImageItem> getImageData(Context context){
        ArrayList<ImageItem> imageList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            String data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));

            int height = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT));
            int width = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.WIDTH));
            String type = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.MIME_TYPE));
            long added = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));

            ImageItem imageItem = new ImageItem(name, data, size, width, height, type, added);
            imageList.add(imageItem);
            stringBuilder.append(type+"=="+data+"\n");
        }
        return imageList;
    }
}
