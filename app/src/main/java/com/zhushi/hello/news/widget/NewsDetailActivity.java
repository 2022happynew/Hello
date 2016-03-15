package com.zhushi.hello.news.widget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.zhushi.hello.R;
import com.zhushi.hello.beans.NewsBean;
import com.zhushi.hello.news.presenter.NewDetailPresenter;
import com.zhushi.hello.news.presenter.NewDetailPresenterImpl;
import com.zhushi.hello.news.view.NewsDetailView;
import com.zhushi.hello.swipeback.SwipeBackActivity;
import com.zhushi.hello.swipeback.SwipeBackLayout;
import com.zhushi.hello.utils.ImageLoaderUtils;
import com.zhushi.hello.utils.ToolsUtil;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * 新闻详细页
 * Created by zhushi on 2016/3/4.
 */
public class NewsDetailActivity extends SwipeBackActivity implements NewsDetailView {

    private NewsBean mNews;
    private HtmlTextView mHtmlTextView;
    private ProgressBar mProgressBar;
    private SwipeBackLayout mSwipeBackLayout;
    private NewDetailPresenter mNewsDetailPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mNews = (NewsBean) getIntent().getSerializableExtra("news");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mHtmlTextView = (HtmlTextView) findViewById(R.id.htNewsContent);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(ToolsUtil.getWidthInPx(this));
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mNews.getTitle());

        ImageLoaderUtils.display(this, (ImageView) findViewById(R.id.ivImage), mNews.getImgsrc());

        mNewsDetailPresenter = new NewDetailPresenterImpl(getApplication(), this);
        mNewsDetailPresenter.loadNewsDetail(mNews.getDocid());
    }

    @Override
    public void showNewsDetailViewContent(String newsContent) {
        mHtmlTextView.setHtmlFromString(newsContent, new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
