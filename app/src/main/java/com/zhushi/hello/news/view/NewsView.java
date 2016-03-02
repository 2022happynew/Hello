package com.zhushi.hello.news.view;

import com.zhushi.hello.beans.NewsBean;

import java.util.List;

/**
 * Created by zhushi on 2016/3/2.
 */
public interface NewsView {
    void showProgress();

    void hideProgress();

    void showLoadFailMsg();

    void addNews(List<NewsBean> newsBeanList);
}
