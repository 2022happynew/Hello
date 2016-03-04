package com.zhushi.hello.news.view;

/**
 * Created by zhushi on 2016/3/4.
 */
public interface NewsDetailView {
    void showNewsDetailViewContent(String newsContent);

    void showProgress();

    void hideProgress();
}
