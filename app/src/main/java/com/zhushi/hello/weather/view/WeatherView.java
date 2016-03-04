package com.zhushi.hello.weather.view;

import com.zhushi.hello.beans.WeatherBean;

import java.util.List;

/**
 * Created by zhushi on 2016/3/4.
 */
public interface WeatherView {
    void showProgress();

    void hideProgress();

    void showWeatherLayout();

    void setCity(String city);

    void setToday(String data);

    void setTemperature(String temperature);

    void setWind(String wind);

    void setWeather(String weather);

    void setWeatherImage(int res);

    void setWeatherData(List<WeatherBean> lists);

    void showErrorToast(String msg);
}
