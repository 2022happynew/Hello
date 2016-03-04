package com.zhushi.hello.news.presenter;

import android.content.Context;

import com.zhushi.hello.beans.NewsDetailBean;
import com.zhushi.hello.news.model.NewsModel;
import com.zhushi.hello.news.model.NewsModelImpl;
import com.zhushi.hello.news.view.NewsDetailView;

/**
 * Created by zhushi on 2016/3/4.
 */
public class NewDetailPresenterImpl implements NewDetailPresenter, NewsModelImpl.OnLoadNewsDetailListener {

    private Context mContex;
    private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;

    public NewDetailPresenterImpl(Context contex, NewsDetailView newsDetailView) {
        this.mContex = contex;
        this.mNewsDetailView = newsDetailView;
        mNewsModel = new NewsModelImpl();

    }

    @Override
    public void loadNewsDetail(String docId) {
        mNewsDetailView.showProgress();
        mNewsModel.loadNewsDetail(docId, this);
    }

    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        if (newsDetailBean != null) {
            mNewsDetailView.showNewsDetailViewContent(newsDetailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }
}
