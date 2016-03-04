package com.zhushi.hello.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.zhushi.hello.commons.Urls;
import com.zhushi.hello.news.NewsAdapter;
import com.zhushi.hello.news.presenter.NewsPresenter;
import com.zhushi.hello.news.presenter.NewsPresenterImpl;
import com.zhushi.hello.news.view.NewsView;
import com.zhushi.hello.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhushi on 2016/3/2.
 */
public class NewsListFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "NewsListFragment";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mNewsAdapter;
    private List<NewsBean> mData;
    private NewsPresenter mNewsPresenter;

    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;

    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsPresenter = new NewsPresenterImpl(this);
        mType = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.primary, R.color.primary_dark, R.color.primary_light, R
                .color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewsAdapter = new NewsAdapter(getActivity().getApplicationContext());
        mNewsAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mNewsAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();

        return view;
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mNewsAdapter.getItemCount()
                    && mNewsAdapter.isShowFooter()) {
                //加载更多
                LogUtils.d(TAG, "loading more data");
                mNewsPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
            }
        }


    };

    private NewsAdapter.OnItemClickListener mOnItemClickListener = new NewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            NewsBean news = mNewsAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", news);

            startActivity(intent);
        }
    };

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        if (pageIndex == 0) {
            mNewsAdapter.isShowFooter(false);
            mNewsAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id
                .drawer_layout);
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void addNews(List<NewsBean> newsList) {
        mNewsAdapter.isShowFooter(true);
        if (mData == null) {
            mData = new ArrayList<NewsBean>();
        }
        mData.addAll(newsList);
        if (pageIndex == 0) {
            mNewsAdapter.setmData(mData);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if (newsList == null || newsList.size() == 0) {
                mNewsAdapter.isShowFooter(false);
            }
            mNewsAdapter.notifyDataSetChanged();
        }
        pageIndex += Urls.PAZE_SIZE;
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        if (mData != null) {
            mData.clear();
        }
        mNewsPresenter.loadNews(mType, pageIndex);
    }
}
