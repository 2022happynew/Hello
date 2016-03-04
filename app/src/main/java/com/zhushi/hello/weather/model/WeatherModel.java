package com.zhushi.hello.weather.model;

import android.content.Context;

/**
 * 天气
 * Created by zhushi on 2016/3/4.
 */
public interface WeatherModel {
    void loadWeatherData(String cityName, WeatherModelImpl.LoadWeatherListener listener);

    void loadLocation(Context context, WeatherModelImpl.LoadLocationListener listener);
}
