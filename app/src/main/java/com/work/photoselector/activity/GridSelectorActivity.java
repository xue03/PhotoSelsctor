package com.work.photoselector.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.work.photoselector.R;
import com.work.photoselector.adapter.BaseAdapter;
import com.work.photoselector.adapter.RecycleAdapter;
import com.work.photoselector.bean.ImageItem;
import com.work.photoselector.util.ImageDataSource;

import java.util.ArrayList;
import java.util.List;

public class GridSelectorActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private List<ImageItem> recyclerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_selector);
        initView();

    }

    private void initData() {
        List<ImageItem> imageData = ImageDataSource.getImageData(this);
        recyclerList.addAll(imageData);
    }

    private void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerList=new ArrayList<>();
        initData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.addItemDecoration(new DividerItemDecoration(GridSelectorActivity.this,DividerItemDecoration.HORIZONTAL));
        recyclerview.addItemDecoration(new DividerItemDecoration(GridSelectorActivity.this,DividerItemDecoration.VERTICAL));

        RecycleAdapter recycleAdapter = new RecycleAdapter(recyclerList, this,true);
        recyclerview.setAdapter(recycleAdapter);
        recycleAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(ImageItem image, int position) {

                Toast.makeText(GridSelectorActivity.this,"点击了："+position,Toast.LENGTH_LONG).show();
            }

            @Override
            public void OnCameraClick() {
                Toast.makeText(GridSelectorActivity.this,"打开相机啦",Toast.LENGTH_LONG).show();

            }
        });


    }
}