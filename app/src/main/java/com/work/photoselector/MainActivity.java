package com.work.photoselector;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.work.photoselector.activity.GridSelectorActivity;
import com.work.photoselector.util.CheckPermission;
import com.work.photoselector.util.ImageDataSource;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] per=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    private Button btn_showPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_showPhoto = (Button) findViewById(R.id.btn_showPhoto);
        btn_showPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_showPhoto:
                boolean permission = CheckPermission.checkPermission(this, this, per, 100);
                if (permission){
                    startActivity(new Intent(this, GridSelectorActivity.class));
                    //ImageDataSource.getImageData(this);
                }else {
                    ActivityCompat.requestPermissions(this, per, 100);
                }
                break;
        }
    }
}