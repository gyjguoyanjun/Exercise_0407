package com.gyj.exercise_0407.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyj.exercise_0407.adapaters.MyBaseAdapter;
import com.gyj.exercise_0407.bean.JsonBean;
import com.gyj.exercise_0407.utils.StreamUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * data:2017/4/7
 * author:郭彦君(Administrator)
 * function:
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String> {
    private Context context;
    private ListView listView;
    private ArrayList<JsonBean.ListBean> data;
    private String s;

    public MyAsyncTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        URL url = null;
        try {
            url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            InputStream inputStream = connection.getInputStream();
            s = StreamUtils.jsonToString(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return s;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
        final List<JsonBean.ListBean> list = jsonBean.getList();
        final MyBaseAdapter adapter = new MyBaseAdapter(context, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, list.get(position).getId()+"", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                list.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
