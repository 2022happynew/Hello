package com.zhushi.hello.weather.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhushi.hello.R;
import com.zhushi.hello.beans.WeatherBean;
import com.zhushi.hello.weather.presenter.WeatherPresenter;
import com.zhushi.hello.weather.presenter.WeatherPresenterImpl;
import com.zhushi.hello.weather.view.WeatherView;

import java.util.List;

/**
 * Created by zhushi on 2016/3/2.
 */
public class WeatherFragment extends Fragment implements WeatherView {
    private WeatherPresenter mWeatherPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeatherPresenter = new WeatherPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, null);
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showWeatherLayout() {

    }

    @Override
    public void setCity(String city) {

    }

    @Override
    public void setToday(String data) {

    }

    @Override
    public void setTemperature(String temperature) {

    }

    @Override
    public void setWind(String wind) {

    }

    @Override
    public void setWeather(String weather) {

    }

    @Override
    public void setWeatherImage(int res) {

    }

    @Override
    public void setWeatherData(List<WeatherBean> lists) {

    }

    @Override
    public void showErrorToast(String msg) {

    }
}
