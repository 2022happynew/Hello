package com.zhushi.hello.news.model;

/**
 * 新闻列表数据获取
 * Created by zhushi on 2016/3/2.
 */
public interface NewsModel {

    void loadNews(String url, int type, NewsModelImpl.OnLoadNewsListListener listener);

    void loadNewsDetail(String docid, NewsModelImpl.OnLoadNewsDetailListener listener);
}
