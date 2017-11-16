package com.channelst.navigationbardemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 使用google官方控件BottomNavigationBar实现
 * 优点,使用简单,无需开发者定义控件UI了,点击还有动画效果
 * 缺点:背景不好设置,无法自定义UI效果
 */
public class NavBarMethodActivity extends AppCompatActivity
        implements BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "NavBarMethodActivity";
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    ArrayList<Fragment> tabFragments = new ArrayList<>(4);
    FragmentManager fm = null;
    private int currentSelectedTab = POSITION_HOME;
    public static final int POSITION_HOME = 0;
    public static final int POSITION_SCENE = 1;
    public static final int POSITION_FOUND = 2;
    public static final int POSITION_PERSONAL = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar_method);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //initTabView();

        initFragments();

        setDefaultFragment();
    }

    private void initFragments() {
        fm = this.getFragmentManager();
        tabFragments.add(new Fragment1());
        tabFragments.add(new Fragment2());
        tabFragments.add(new Fragment3());
        tabFragments.add(new Fragment4());

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.tab_home_img_selector, "家")
                        .setActiveColor(R.color.colorHomeTabTextGreen)
                        .setInActiveColor(R.color.colorHomeTabTextGray))
                .addItem(new BottomNavigationItem(R.drawable.tab_scene_img_selector, "场景")
                        .setActiveColor(R.color.colorHomeTabTextGreen)
                        .setInActiveColor(R.color.colorHomeTabTextGray))
                .addItem(new BottomNavigationItem(R.drawable.tab_found_img_selector, "发现")
                        .setActiveColor(R.color.colorHomeTabTextGreen)
                        .setInActiveColor(R.color.colorHomeTabTextGray))
                .addItem(new BottomNavigationItem(R.drawable.tab_personal_img_selector, "我的")
                        .setActiveColor(R.color.colorHomeTabTextGreen)
                        .setInActiveColor(R.color.colorHomeTabTextGray))
                .setFirstSelectedPosition(currentSelectedTab)
                .setBarBackgroundColor("#FFFFFF")
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);
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
        bottomNavigationBar.setLayoutParams(tabLayoutParams);
        Log.d(TAG, "tabLayoutHeight : " + tabLayoutHeight);

    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment = tabFragments.get(currentSelectedTab);
        transaction.replace(R.id.content_layout, fragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment = tabFragments.get(position);
        transaction.replace(R.id.content_layout, fragment);
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
