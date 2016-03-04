package com.zhushi.hello.news.widget;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.zhushi.hello.R;
import com.zhushi.hello.swipeback.SwipeBackActivity;

/**
 * Created by zhushi on 2016/3/4.
 */
public class NewsDetailActivity extends SwipeBackActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_news_detail);
    }
}
