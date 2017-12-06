package com.halfopen.iwash;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.halfopen.iwash.util.FileUtil;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText tvLog;
    FileUtil fileUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLog = findViewById(R.id.tv_log);
        fileUtil = new FileUtil(getApplicationContext());
        // 首先判断设备是否挂载SDCard
        boolean isMounted = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

        tvLog.setText(fileUtil.read());
    }
}
