package com.alpha.module_main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alpha.module_common.base.BaseActivity;
import com.alpha.module_common.base.BaseFragment;
import com.alpha.module_common.manager.FragmentPageManager;
import com.alpha.module_common.utils.NLog;
import com.alpha.module_main.adapter.FragmentsPagerAdapter;

import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private ViewPager vpPager;
    private List<BaseFragment> mFragments;
    private FragmentsPagerAdapter mAdapter;

    private BottomNavigationView bnvNavigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                int i = item.getItemId();
                if (i == R.id.main_navigation_home) {
                    vpPager.setCurrentItem(0);
                    return true;
                } else if (i == R.id.main_navigation_message) {
                    vpPager.setCurrentItem(1);
                    return true;
                } else if (i == R.id.main_navigation_mine) {
                    vpPager.setCurrentItem(2);
                    return true;
                }
                return false;
            };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
        NLog.d(TAG,"onCreate");
        bnvNavigation = findViewById(R.id.bnv_bottom_navigation);
        vpPager = findViewById(R.id.vp_pager_container);
        bnvNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initViewPager();
    }



    private void initViewPager() {
        mFragments = FragmentPageManager.getInstance().getAllFragment();//这几个Fragment是主动添加到ViewManager中的
//        BaseFragment homeFragments = getFragment("com.alpha.module_home");
//        BaseFragment messageFragments = getFragment("com.alpha.module_message");
//        BaseFragment mineFragments = getFragment("com.alpha.module_mine");
//        mFragments.add(homeFragments);
//        mFragments.add(messageFragments);
//        mFragments.add(mineFragments);
        mAdapter = new FragmentsPagerAdapter(getSupportFragmentManager(), mFragments);
        vpPager.setAdapter(mAdapter);
    }

//    private BaseFragment getFragment(String fragment) {
//        BaseFragment mFragment = null;
//        List<IViewDelegate> viewDelegates = ClassUtils.getObjectsWithInterface(this, IViewDelegate.class, fragment);
//        if (viewDelegates != null && !viewDelegates.isEmpty()) {
//            mFragment = viewDelegates.get(0).getFragment("");
//        }
//        return mFragment;
//    }

}
