package com.zhushi.hello.images.view;

import com.zhushi.hello.beans.ImageBean;

import java.util.List;

/**
 * Created by zhushi on 2016/3/4.
 */
public interface ImageView {
    void addImages(List<ImageBean> list);

    void showProgress();

    void hideProgress();

    void showLoadFailMsg();
}
