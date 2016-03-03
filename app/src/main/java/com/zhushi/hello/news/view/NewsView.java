package com.zhushi.hello.news.view;

import com.zhushi.hello.beans.NewsBean;

import java.util.List;

/**
 * 如何定义view接口？
 * 该操作需要什么？
 * 该操作的结果，对应的反馈？ void addNews(List<NewsBean> newsBeanList); void showLoadFailMsg();
 * 该操作过程中对应的友好的交互？ void showProgress();   void hideProgress();
 * Created by zhushi on 2016/3/2.
 */
public interface NewsView {
    void showProgress();

    void hideProgress();

    void showLoadFailMsg();

    void addNews(List<NewsBean> newsBeanList);
}
