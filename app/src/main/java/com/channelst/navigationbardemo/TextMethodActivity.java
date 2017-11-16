package com.channelst.navigationbardemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用LinearLayout+TextView实现底部导航栏
 * 通过点击layout切换fragment实现
 * 优点:实现原理简单,逻辑清晰,可以自定义角标并显示数量
 * 缺点:由于没加ViewPager,所以不能左右滑动切换
 */
public class TextMethodActivity extends AppCompatActivity {

    private static final String TAG = "TextMethodActivity";
    @BindView(R.id.tab_home_icon)
    ImageView tabHomeIcon;
    @BindView(R.id.tab_home_badge)
    ImageView tabHomeBadge;
    @BindView(R.id.tab_home_title)
    TextView tabHomeTitle;
    @BindView(R.id.tab_home_layout)
    LinearLayout tabHomeLayout;
    @BindView(R.id.tab_scene_icon)
    ImageView tabSceneIcon;
    @BindView(R.id.tab_scene_badge)
    ImageView tabSceneBadge;
    @BindView(R.id.tab_scene_title)
    TextView tabSceneTitle;
    @BindView(R.id.tab_scene_layout)
    LinearLayout tabSceneLayout;
    @BindView(R.id.tab_find_icon)
    ImageView tabFindIcon;
    @BindView(R.id.tab_find_badge)
    ImageView tabFindBadge;
    @BindView(R.id.tab_find_title)
    TextView tabFindTitle;
    @BindView(R.id.tab_find_layout)
    LinearLayout tabFindLayout;
    @BindView(R.id.tab_me_icon)
    ImageView tabMeIcon;
    @BindView(R.id.tab_me_badge)
    ImageView tabMeBadge;
    @BindView(R.id.tab_me_title)
    TextView tabMeTitle;
    @BindView(R.id.tab_me_layout)
    LinearLayout tabMeLayout;
    @BindView(R.id.tab_layout)
    LinearLayout tabLayout;
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
        setContentView(R.layout.activity_text_method);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tab_home_layout, R.id.tab_scene_layout, R.id.tab_find_layout, R.id.tab_me_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_home_layout:
                setTabByPosition(POSITION_HOME);
                break;
            case R.id.tab_scene_layout:
                setTabByPosition(POSITION_SCENE);
                break;
            case R.id.tab_find_layout:
                setTabByPosition(POSITION_FOUND);
                break;
            case R.id.tab_me_layout:
                setTabByPosition(POSITION_PERSONAL);
                break;
        }
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
        tabLayout.setLayoutParams(tabLayoutParams);
        Log.d(TAG, "tabLayoutHeight : " + tabLayoutHeight);

    }


    private void setDefaultTab() {
        if (currentSelectedTab == -1) {
            setTabByPosition(POSITION_HOME);
        } else {
            setTabByPosition(currentSelectedTab);
        }
    }

    /**
     * 选择tab,显示tab
     */
    public void setTabByPosition(int currentPosition) {
        Log.d(TAG, "setTabByPosition  ---  currentPosition : " + currentPosition);
        clearTabSelected();
        onTabSelected(currentPosition);
    }

    /**
     * 清除之前选择的tab的图标颜色和字体颜色
     */
    private void clearTabSelected() {
        switch (currentSelectedTab) {
            case POSITION_HOME:
                tabHomeIcon.setSelected(false);
                tabHomeTitle.setSelected(false);
                break;
            case POSITION_SCENE:
                tabSceneIcon.setSelected(false);
                tabSceneTitle.setSelected(false);
                break;
            case POSITION_FOUND:
                tabFindIcon.setSelected(false);
                tabFindTitle.setSelected(false);
                break;
            case POSITION_PERSONAL:
                tabMeIcon.setSelected(false);
                tabMeTitle.setSelected(false);
                break;
        }
    }

    /**
     * 当相应tab被点击时调用
     *
     * @param position
     */
    private void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected  --- position : " + position);
        currentSelectedTab = position;
        //refresh view
        refreshView(position);
        //refresh content
        refreshFragment(position);
    }

    /**
     * 点击相应tab后,相应tab显示绿色图标和字体颜色
     *
     * @param position
     */
    private void refreshView(int position) {
        Log.d(TAG, "refreshView  --- position : " + position);
        switch (position) {
            case POSITION_HOME:
                tabHomeIcon.setSelected(true);
                tabHomeTitle.setSelected(true);
                break;
            case POSITION_SCENE:
                tabSceneIcon.setSelected(true);
                tabSceneTitle.setSelected(true);
                break;
            case POSITION_FOUND:
                tabFindIcon.setSelected(true);
                tabFindTitle.setSelected(true);
                break;
            case POSITION_PERSONAL:
                tabMeIcon.setSelected(true);
                tabMeTitle.setSelected(true);
                break;
            default:
                break;
        }
    }

    /**
     * 点击相应tab后,显示相应fragment,并传递所需参数
     *
     * @param position
     */
    private void refreshFragment(int position) {
        Log.d(TAG, "refreshFragment  --- position : " + position);
        if (tabFragments != null) {
            if (position < tabFragments.size()) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = tabFragments.get(position);
                Log.d(TAG, "fragment.isAdded() : " + fragment.isAdded());

                ft.replace(R.id.content_layout, fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }
}
