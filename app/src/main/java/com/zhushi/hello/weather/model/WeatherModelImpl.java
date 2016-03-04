package com.zhushi.hello.weather.model;

import android.content.Context;

import com.zhushi.hello.beans.WeatherBean;

import java.util.List;

/**
 * Created by zhushi on 2016/3/4.
 */
public class WeatherModelImpl implements WeatherModel {
    @Override
    public void loadWeatherData(String cityName, WeatherModelImpl.LoadWeatherListener listener) {

    }

    @Override
    public void loadLocation(Context context, WeatherModelImpl.LoadLocationListener listener) {

    }

    public interface LoadLocationListener {
        void onSuccess(String cityName);

        void onFailure(String msg, Exception e);
    }

    public interface LoadWeatherListener {
        void onSuccess(List<WeatherBean> list);

        void onFailure(String msg, Exception e);
    }
}
