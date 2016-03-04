package com.zhushi.hello.images.presenter;

import com.zhushi.hello.beans.ImageBean;
import com.zhushi.hello.images.model.ImageModel;
import com.zhushi.hello.images.model.ImageModelImpl;
import com.zhushi.hello.images.view.ImageView;

import java.util.List;

/**
 * Created by zhushi on 2016/3/4.
 */
public class ImagePresenterImpl implements ImagePresenter, ImageModelImpl.OnLoadImageListListener {
    private ImageView mImageView;
    private ImageModel mImageModel;

    public ImagePresenterImpl(ImageView mImageView) {
        this.mImageView = mImageView;
        mImageModel = new ImageModelImpl();
    }

    @Override
    public void loadImageList() {
        mImageView.showProgress();
        mImageModel.loadImageList(this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mImageView.addImages(list);
        mImageView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mImageView.hideProgress();
        mImageView.showLoadFailMsg();
    }
}
