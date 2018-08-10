package com.alpha.module_main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.alpha.module_common.base.BaseActivity;
import com.alpha.module_common.base.BaseFragment;
import com.alpha.module_common.manager.FragmentPageManager;
import com.alpha.module_common.widget.NoScrollViewPager;
import com.alpha.module_main.adapter.FragmentAdapter;

import java.util.List;

public class MainActivity extends BaseActivity {

    private NoScrollViewPager mPager;
    private List<BaseFragment> mFragments;
    private FragmentAdapter mAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.main_navigation_home) {
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.main_navigation_message) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.main_navigation_mine) {
                mPager.setCurrentItem(2);
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initViewPager();
    }

    private void initViewPager() {
        mFragments = FragmentPageManager.getInstance().getAllFragment();//这几个Fragment是主动添加到ViewManager中的
//        BaseFragment mineFragment = getNewsFragment();//主动寻找
//        mFragments.add(BaseFragment.newInstance("新闻"));
        mPager = (NoScrollViewPager) findViewById(R.id.container_pager);
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mPager.setPagerEnabled(false);
        mPager.setAdapter(mAdapter);
    }


    /**
     * 在News模块中寻找实现的Fragment
     *
     * @return Fragment
     */
//    private BaseFragment getNewsFragment() {
//        BaseFragment newsFragment = null;
//        List<IViewDelegate> viewDelegates = ClassUtils.getObjectsWithInterface(this, IViewDelegate.class, "com.guiying.module.news");
//        if (viewDelegates != null && !viewDelegates.isEmpty()) {
//            newsFragment = viewDelegates.get(0).getFragment("");
//        }
//        return newsFragment;
//    }

}
