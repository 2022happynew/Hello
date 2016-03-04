package com.zhushi.hello.weather.presenter;

import com.zhushi.hello.beans.WeatherBean;
import com.zhushi.hello.weather.model.WeatherModel;
import com.zhushi.hello.weather.model.WeatherModelImpl;
import com.zhushi.hello.weather.view.WeatherView;

import java.util.List;

/**
 * Created by zhushi on 2016/3/4.
 */
public class WeatherPresenterImpl implements WeatherPresenter, WeatherModelImpl.LoadWeatherListener {
    private WeatherView mWeatherView;
    private WeatherModel mWeatherModel;

    public WeatherPresenterImpl(WeatherView weatherView) {
        this.mWeatherView = weatherView;
        mWeatherModel = new WeatherModelImpl();
    }

    @Override
    public void loadWeatherData() {

    }

    @Override
    public void onSuccess(List<WeatherBean> list) {

    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
