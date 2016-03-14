package com.zhushi.hello.images.widget;

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
import com.zhushi.hello.beans.ImageBean;
import com.zhushi.hello.images.ImageAdapter;
import com.zhushi.hello.images.presenter.ImagePresenter;
import com.zhushi.hello.images.presenter.ImagePresenterImpl;
import com.zhushi.hello.images.view.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhushi on 2016/3/2.
 */
public class ImageFragment extends Fragment implements ImageView, SwipeRefreshLayout.OnRefreshListener {
    private ImagePresenter mImagePresenter;
    private ImageAdapter mImageAdapter;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private List<ImageBean> mData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImagePresenter = new ImagePresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images, null);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mImageAdapter = new ImageAdapter(getActivity().getApplicationContext());
        mImageAdapter.setmOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(mImageAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }

    private ImageAdapter.OnItemClickListener onItemClickListener = new ImageAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            ImageBean imageBean = mImageAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), BigImageActivity.class);
            intent.putExtra("image", imageBean);
            startActivity(intent);
        }
    };
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
                    && lastVisibleItem + 1 == mImageAdapter.getItemCount()) {
                //加载更多
                Snackbar.make(getActivity().findViewById(R.id.drawer_layout), getString(R.string.image_hit), Snackbar
                        .LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void addImages(List<ImageBean> list) {
        if (mData == null) {
            mData = new ArrayList<ImageBean>();
        }
        mData.addAll(list);
        mImageAdapter.setmData(mData);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id
                .drawer_layout);
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        if (mData != null) {
            mData.clear();
        }
        mImagePresenter.loadImageList();
    }
}
