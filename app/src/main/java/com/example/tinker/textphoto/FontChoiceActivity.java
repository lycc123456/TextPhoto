package com.example.tinker.textphoto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class FontChoiceActivity extends AppCompatActivity implements OnRefreshListener {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_font_choice);

        initView();
    }

    private void initView() {
        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initRecycle();
    }


    private void initRecycle() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
//
//        recordAdapter = new BatteryCirculationRecordAdapter(this, mDatas);
//        recyclerView.setAdapter(recordAdapter);

        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(false).setColorSchemeColors(getResources().getColor(R.color.color_fed130))); //设置刷新为官方推介
        smartRefreshLayout.setEnableHeaderTranslationContent(false);//刷新时和官方一致   内容不随刷新动
        smartRefreshLayout.setPrimaryColorsId(android.R.color.holo_orange_light, android.R.color.holo_orange_dark, android.R.color.holo_orange_light); //圈圈颜色
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshFile();
    }

    /**
     * 刷新本地文字
     */
    private void onRefreshFile() {

    }
}
