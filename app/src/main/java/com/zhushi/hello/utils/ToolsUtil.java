package com.zhushi.hello.utils;

import android.content.Context;

/**
 * Created by zhushi on 2016/3/4.
 */
public class ToolsUtil {
    public static final int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }
}
