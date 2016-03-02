package com.zhushi.hello.news.model;

/**
 * Created by zhushi on 2016/3/2.
 */
public interface NewsModel {
    void loadNews(String url, int type, NewsModelImpl.OnLoadNewsListListener listener);
}
