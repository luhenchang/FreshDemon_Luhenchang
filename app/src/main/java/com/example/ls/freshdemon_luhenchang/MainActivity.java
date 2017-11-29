package com.example.ls.freshdemon_luhenchang;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IBottomView;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecylerViwe;
    private LastInforAdapter lastAdapter;
    private TwinklingRefreshLayout refreshLayout;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setData();
    }

    private void setData() {
        mRecylerViwe.setAdapter(lastAdapter);
        mRecylerViwe.setNestedScrollingEnabled(false);
    }

    private void initData() {
        mList = new ArrayList<>();
        lastAdapter = new LastInforAdapter(this,mList);


        for (int i = 0; i < 23; i++) {
            mList.add("第3个Item=" + i);
        }

        lastAdapter.notifyDataSetChanged();

    }

    private void initView() {
        final TextView[] refrush_tv = new TextView[1];
        final ImageView[] refrush_image = new ImageView[1];
        final ImageView[] refrush_image1 = new ImageView[1];
        refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);
        mRecylerViwe = (RecyclerView) findViewById(R.id.activity_last_infor_rv);
        mRecylerViwe.setLayoutManager(new LinearLayoutManager(this));
        //第一个中自己新建类实现接口



        refreshLayout.setHeaderView(new MyGoleView(this,0));
        //这里的1随便写不是0就可以了
        refreshLayout.setHeaderView(new MyGoleView(this,1));


       // 第二种这个地方用内部类的方式来实现，对于我们来说最好不要这样子吧，维护起来烦死了，而且代码太多，不能够复用。
       /* IHeaderView iHeaderView = new IHeaderView() {
            private AnimationDrawable animationDrawable;

            @Override
            public View getView() {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.refrush_header, null);
                refrush_tv[0] = view.findViewById(R.id.refrush_tv);
                refrush_image[0] = view.findViewById(R.id.refrush_image);
                return view;
            }

            @Override
            public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
                refrush_tv[0].setText("下拉刷新");
            }

            @Override
            public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
                refrush_tv[0].setText("正在刷新中");

            }

            @Override
            public void startAnim(float maxHeadHeight, float headHeight) {
                refrush_image[0].setBackgroundResource(R.drawable.anim_loading_view);
                animationDrawable = (AnimationDrawable)

                        refrush_image[0].getBackground();
                animationDrawable.start();
            }

            @Override
            public void onFinish() {
                animationDrawable.stop();
            }
        };
*/

        /*IBottomView ibootemview = new IBottomView() {
            private AnimationDrawable animationDrawable1;

            @Override
            public View getView() {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.refrush_bootom, null);
                refrush_image1[0] = view.findViewById(R.id.refrush_image1);
                return view;
            }

            @Override
            public void onPullingUp(float fraction, float maxHeadHeight, float headHeight) {

            }

            @Override
            public void startAnim(float maxHeadHeight, float headHeight) {
                refrush_image1[0].setBackgroundResource(R.drawable.anim_loading_view);
                animationDrawable1 = (AnimationDrawable)
                        refrush_image1[0].getBackground();
                animationDrawable1.start();
            }

            @Override
            public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {

            }

            @Override
            public void onFinish() {
                animationDrawable1.stop();
                ;
            }
        };
        refreshLayout.setBottomView(ibootemview);*/

        //下拉刷新
        refreshLayout.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 1000);
            }

            //上啦加载
            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 1000);
            }
        });

    }
}
