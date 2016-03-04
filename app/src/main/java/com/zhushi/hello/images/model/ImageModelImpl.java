package com.zhushi.hello.images.model;

import com.zhushi.hello.beans.ImageBean;
import com.zhushi.hello.commons.Urls;
import com.zhushi.hello.images.ImageJsonUtils;
import com.zhushi.hello.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by zhushi on 2016/3/4.
 */
public class ImageModelImpl implements ImageModel {

    @Override
    public void loadImageList(final OnLoadImageListListener listener) {
        String url = Urls.IMAGES_URL;
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<ImageBean> iamgeBeanList = ImageJsonUtils.readJsonImageBeans(response);
                listener.onSuccess(iamgeBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load image list failure.", e);
            }
        };
        OkHttpUtils.get(url, loadNewsCallback);
    }

    public interface OnLoadImageListListener {
        void onSuccess(List<ImageBean> list);

        void onFailure(String msg, Exception e);
    }
}
