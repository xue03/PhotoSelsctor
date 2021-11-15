package com.work.photoselector.bean;

/**
 * Date:2021/11/15
 * Description:
 * Author:XueTingTing
 */
public class ImageItem{
    public String name;       //图片的名字
    public String path;       //图片的路径
    public long size;         //图片的大小
    public int width;         //图片的宽度
    public int height;        //图片的高度
    public String mimeType;   //图片的类型
    public long addTime;      //图片的创建时间

    public ImageItem(String name, String path, long size, int width, int height, String mimeType, long addTime) {
        this.name = name;
        this.path = path;
        this.size = size;
        this.width = width;
        this.height = height;
        this.mimeType = mimeType;
        this.addTime = addTime;
    }
}
