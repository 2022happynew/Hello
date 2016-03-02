package com.zhushi.hello.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhushi.hello.R;
import com.zhushi.hello.beans.NewsBean;
import com.zhushi.hello.news.presenter.NewsPresenter;
import com.zhushi.hello.news.presenter.NewsPresenterImlp;
import com.zhushi.hello.news.view.NewsView;

import java.util.List;

/**
 * Created by zhushi on 2016/3/2.
 */
public class NewsListFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private NewsPresenter mNewsPresenter;
    private int mTpye = NewsFragmemt.NEWS_TYPE_TOP;

    public static Fragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenterImlp();
        mTpye = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.primary, R.color.primary_dark, R.color.primary_light, R
                .color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView= (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailMsg() {

    }

    @Override
    public void addNews(List<NewsBean> newsBeanList) {

    }

    @Override
    public void onRefresh() {

    }
}
