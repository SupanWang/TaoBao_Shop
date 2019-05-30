package com.example.nice.taobao_shop.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nice.taobao_shop.R;
import com.example.nice.taobao_shop.base.BaseActivity;
import com.example.nice.taobao_shop.mvp.presenter.EmptyPresenter;
import com.example.nice.taobao_shop.mvp.view.EmptyView;

public class MainActivity extends BaseActivity<EmptyView , EmptyPresenter> implements EmptyView {


    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
