package com.zhushi.hello.weather.model;

import android.content.Context;

/**
 * 天气
 * Created by zhushi on 2016/3/4.
 */
public interface WeatherModel {

    /**
     * 加载天气数据
     * @param cityName
     * @param listener
     */
    void loadWeatherData(String cityName, WeatherModelImpl.LoadWeatherListener listener);

    /**
     * 加载定位城市
     * @param context
     * @param listener
     */
    void loadLocation(Context context, WeatherModelImpl.LoadLocationListener listener);
}
