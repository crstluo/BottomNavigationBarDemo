package com.channelst.navigationbardemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用RadioGroup+RadioButton实现底部导航栏
 * 优点:逻辑简单
 * 缺点:无法显示右上角标和信息数量,也不太好调整UI因为RadioButton不好设置图片大小
 * 所以看起来相当丑
 */
public class RadioMethodActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private static final String TAG = "RadioMethodActivity";
    @BindView(R.id.tab_home_button)
    RadioButton tabHomeButton;
    @BindView(R.id.tab_scene_button)
    RadioButton tabSceneButton;
    @BindView(R.id.tab_find_button)
    RadioButton tabFindButton;
    @BindView(R.id.tab_me_button)
    RadioButton tabMeButton;
    @BindView(R.id.tab_group)
    RadioGroup tabGroup;
    @BindView(R.id.content_layout)
    FrameLayout contentLayout;

    ArrayList<Fragment> tabFragments = new ArrayList<>(4);
    FragmentManager fm = null;
    private int currentSelectedTab = -1;
    public static final int POSITION_HOME = 0;
    public static final int POSITION_SCENE = 1;
    public static final int POSITION_FOUND = 2;
    public static final int POSITION_PERSONAL = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_method);
        ButterKnife.bind(this);

        tabGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = null;
        clearTabSelected();
        switch (checkedId){
            case R.id.tab_home_button:
                fragment = tabFragments.get(POSITION_HOME);
                Log.d(TAG, "fragment.isAdded() : " + fragment.isAdded());
                currentSelectedTab = POSITION_HOME;
                tabHomeButton.setSelected(true);
                break;
            case R.id.tab_scene_button:
                fragment = tabFragments.get(POSITION_SCENE);
                currentSelectedTab = POSITION_SCENE;
                tabSceneButton.setSelected(true);
                break;
            case R.id.tab_find_button:
                fragment = tabFragments.get(POSITION_FOUND);
                currentSelectedTab = POSITION_FOUND;
                tabFindButton.setSelected(true);
                break;
            case R.id.tab_me_button:
                fragment = tabFragments.get(POSITION_PERSONAL);
                currentSelectedTab = POSITION_PERSONAL;
                tabMeButton.setSelected(true);
                break;
        }
        ft.replace(R.id.content_layout, fragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void onStart() {
        super.onStart();

        initFragments();
        initTabView();

        setDefaultTab();
    }


    private void initFragments() {
        fm = this.getFragmentManager();
        tabFragments.add(new Fragment1());
        tabFragments.add(new Fragment2());
        tabFragments.add(new Fragment3());
        tabFragments.add(new Fragment4());
    }

    /**
     * 初始化底部tabView
     * 主要是处理系统底部虚拟按键的遮盖问题
     */
    private void initTabView() {
        int navBarHeight = AppUtil.getNavigationBarHeight(this);
        Log.e(TAG, "navBarHeight : " + navBarHeight);
        //ToastUtils.show(this,"navBarHeight : " + navBarHeight);

        //int screenHeight = DeviceValueUtils.getScreenHeightPx(this);
        int tabLayoutHeight = DeviceValueUtil.getRealityHeightPx(this, Constants.DESIGN_TABLAYOUT_HEIGHT);
        /*Math.round(screenHeight * Constants.DESIGN_TABLAYOUT_HEIGHT
                / (float) Constants.DESIGN_HEIGHT);*/
        RelativeLayout.LayoutParams tabLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, tabLayoutHeight);
        tabLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        tabLayoutParams.setMargins(0, 0, 0, navBarHeight);
        tabGroup.setLayoutParams(tabLayoutParams);
        Log.d(TAG, "tabLayoutHeight : " + tabLayoutHeight);

    }


    private void setDefaultTab() {
        clearTabSelected();
        tabHomeButton.setChecked(true);
        tabHomeButton.setSelected(true);
    }

    /**
     * 清除之前选择的tab的图标颜色和字体颜色
     */
    private void clearTabSelected() {
        switch (currentSelectedTab) {
            case POSITION_HOME:
                tabHomeButton.setSelected(false);
                break;
            case POSITION_SCENE:
                tabSceneButton.setSelected(false);
                break;
            case POSITION_FOUND:
                tabFindButton.setSelected(false);
                break;
            case POSITION_PERSONAL:
                tabMeButton.setSelected(false);
                break;
        }
    }
}
