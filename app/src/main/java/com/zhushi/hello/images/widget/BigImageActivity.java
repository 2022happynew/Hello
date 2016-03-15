package com.zhushi.hello.images.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhushi.hello.R;
import com.zhushi.hello.beans.ImageBean;
import com.zhushi.hello.swipeback.SwipeBackActivity;
import com.zhushi.hello.utils.ImageLoaderUtils;

/**
 * Created by zhushi on 2016/3/14.
 */
public class BigImageActivity extends SwipeBackActivity {
    private ImageView imageView;
    private ImageBean mImageBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mImageBean = (ImageBean) getIntent().getSerializableExtra("image");
        imageView = (ImageView) findViewById(R.id.imageView);
        ImageLoaderUtils.display(this, imageView, mImageBean.getSourceurl());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
