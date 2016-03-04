package com.zhushi.hello.images.model;

/**
 * 图片model数据获取
 * Created by zhushi on 2016/3/4.
 */
public interface ImageModel {
    void loadImageList(ImageModelImpl.OnLoadImageListListener onLoadImageListListener);
}
