package com.example.linkagelayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.tv_left_title)
    TextView tvLeftTitle;
    @BindView(R.id.scTitle)
    ObservableScrollView scTitle;
    @BindView(R.id.ll_top_root)
    LinearLayout llTopRoot;
    @BindView(R.id.recycler_content)
    RecyclerView recyclerContent;
    private List<Entity> mEntities = new ArrayList<>();
    private List<String> rightMoveDatas = new ArrayList<>();
    private List<String> topTabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        for (int i = 0; i < 50; i++) {
            topTabs.add("类型" + i);
        }
        //处理内容部分
        recyclerContent.setLayoutManager(new LinearLayoutManager(this));
        recyclerContent.setHasFixedSize(true);
        final ContentAdapter contentAdapter = new ContentAdapter(this,scTitle);
        recyclerContent.setAdapter(contentAdapter);

        recyclerContent.postDelayed(() -> {
            for (int i = 0; i < 30; i++) {
                Entity entity = new Entity();
                entity.setLeftTitle("贵州茅台" + i);
                rightMoveDatas.clear();
                for (int j = 0; j < 50; j++) {
                    rightMoveDatas.add("年份" + j);
                }
                entity.setRightDatas(rightMoveDatas);
                mEntities.add(entity);
            }
            contentAdapter.setDatas(mEntities);
            Toast.makeText(MainActivity.this, "加载完毕,加载了30条数据", Toast.LENGTH_SHORT).show();
        }, 1500);

        //下拉刷新
    }
}
