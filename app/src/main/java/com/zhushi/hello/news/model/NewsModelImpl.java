package com.zhushi.hello.news.model;

import com.zhushi.hello.beans.NewsBean;

import java.util.List;

/**
 * Created by zhushi on 2016/3/2.
 */
public class NewsModelImpl implements NewsModel {

    @Override
    public void loadNews(String url, int type, OnLoadNewsListListener listener) {

    }

    public interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean> list);

        void onFailure(String msg, Exception e);
    }
}
