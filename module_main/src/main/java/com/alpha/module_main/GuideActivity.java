package com.alpha.module_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alpha.module_common.Constants;
import com.alpha.module_common.base.BaseActivity;
import com.alpha.module_common.manager.PreferencesManager;
import com.alpha.module_main.adapter.ViewsPagerAdapter;

import java.util.ArrayList;

@Route(path = "/main/GuideActivity")
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private String TAG = GuideActivity.class.getSimpleName();

    private ViewPager vpPageContainer;
    private ArrayList<View> vGuidePages;
    private ImageView imageView;
    private ImageView[] imageViews;
    private ViewGroup indicators;
    private int DSI = 0;//滑动状态数值总和
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_guide);
        setTopBarVisibility(View.GONE);
        PreferencesManager.getInstance(mContext).put(Constants.IS_FIRST_RUN, false);
        initView();
    }

    private void initView(){
        vpPageContainer = findViewById(R.id.main_vp_guide_pages_container);
        LayoutInflater inflater = getLayoutInflater();
        vGuidePages = new ArrayList<>();
        vGuidePages.add(inflater.inflate(R.layout.main_activity_guide_item_one, null));
        vGuidePages.add(inflater.inflate(R.layout.main_activity_guide_item_two, null));
        vGuidePages.add(inflater.inflate(R.layout.main_activity_guide_item_three, null));
        vGuidePages.add(inflater.inflate(R.layout.main_activity_guide_item_four, null));

        imageViews = new ImageView[vGuidePages.size()];

        indicators = findViewById(R.id.main_ll_guide_indicators);

        for (int i = 0; i < vGuidePages.size(); i++) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(20, 0, 20, 0);
            imageViews[i] = imageView;

            // 默认选中第一张图片
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.drawable.main_guide_page_indicator_focused);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.main_guide_page_indicator_normal);
            }
            indicators.addView(imageViews[i]);
        }
        vpPageContainer.setAdapter(new ViewsPagerAdapter(vGuidePages));
        vpPageContainer.addOnPageChangeListener(this);
    }

    /**
     * 引导界面末页滑动进入应用首页
     * 非边界滑动：DSI=SCROLL_STATE_DRAGGING+SCROLL_STATE_SETTLING+SCROLL_STATE_IDLE=3
     * 边界滑动：DSI=SCROLL_STATE_DRAGGING+SCROLL_STATE_IDLE=1
     *
     * @param state 滑动状态
     */
    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                DSI+=1;
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                DSI+=2;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (vpPageContainer.getAdapter() != null){
                    if (vpPageContainer.getCurrentItem() == vpPageContainer.getAdapter().getCount() - 1 && DSI==1) {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                DSI=0;
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[position].setBackgroundResource(R.drawable.main_guide_page_indicator_focused);
            if (position != i) {
                imageViews[i].setBackgroundResource(R.drawable.main_guide_page_indicator_normal);
            }
        }
        //引导界面滑动到末页点击进入应用首页
        if(position == 3){
            View item = vGuidePages.get(position);
            item.setOnClickListener(arg0 -> {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}
