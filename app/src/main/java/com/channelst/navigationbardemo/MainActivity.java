package com.channelst.navigationbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 底部导航栏的实现方式,此activity有三个button
 * 点击分别进入不同实现方式的activity中
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textViewMethod)
    Button textViewMethod;
    @BindView(R.id.radioBtnMethod)
    Button radioBtnMethod;
    @BindView(R.id.BottomNavigationBarMethod)
    Button BottomNavigationBarMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.textViewMethod, R.id.radioBtnMethod, R.id.BottomNavigationBarMethod})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textViewMethod:
                startActivity(new Intent(this, TextMethodActivity.class));
                break;
            case R.id.radioBtnMethod:
                startActivity(new Intent(this, RadioMethodActivity.class));
                break;
            case R.id.BottomNavigationBarMethod:
                startActivity(new Intent(this, NavBarMethodActivity.class));
                break;
        }
    }
}
