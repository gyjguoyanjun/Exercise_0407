package com.gyj.exercise_0407.activitis;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyj.exercise_0407.R;
import com.gyj.exercise_0407.asyncTask.MyAsyncTask;
import com.gyj.exercise_0407.bean.JsonBean;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private int widthPixels;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        widthPixels = this.getResources().getDisplayMetrics().widthPixels;

        initView();
        initData();
        initSlidingMenu();

    }

    private void initSlidingMenu() {
        SlidingMenu menu = new SlidingMenu(MainActivity.this);
        //设置左边侧滑菜单
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置滑动菜单视图的宽度
        menu.setBehindWidth(widthPixels / 3 * 2);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        //设置左边侧滑菜单
        menu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.fragment_layout);
    }

    private void initData() {
        MyAsyncTask myAsyncTask = new MyAsyncTask(MainActivity.this, listView);
        myAsyncTask.execute("http://result.eolinker.com/hs8Lfqd8722a248fff2322d8013fac7521a90448d88fb4e?uri=hunhyt");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "sss", Toast.LENGTH_SHORT).show();
                initSlidingMenu();
            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.main_listview);
        imageView = (ImageView) findViewById(R.id.main_image);
    }

}
