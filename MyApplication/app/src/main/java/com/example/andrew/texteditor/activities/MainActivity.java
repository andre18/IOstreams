package com.example.andrew.texteditor.activities;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.andrew.texteditor.adapters.AdapterMainActivityList;
import com.example.andrew.texteditor.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button buttonCreate;
    private ListView listView;

    private List<String> fileNameList;

    private File dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileNameList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.activity_main_list_view);
        buttonCreate = (Button) findViewById(R.id.activity_main_button_create);

        fileNameList.addAll(getFiles());
        listView.setAdapter(new AdapterMainActivityList(fileNameList, MainActivity.this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = fileNameList.get(position);
                Intent intent = new Intent(MainActivity.this, OpenFileActivity.class);
                intent.putExtra("name", s);
                startActivity(intent);
            }
        });

        buttonCreate.setOnClickListener(this);
    }

    //this method get all file names and returns array
    private List<String> getFiles(){
        dir = new File(Environment.getExternalStorageDirectory() + "/AIO/");

        if(!dir.exists()){
            dir.mkdirs();
        }
        List<String> array = new ArrayList<>();

        String[] listFiles = dir.list();
        for (int i = 0; i < listFiles.length; i++) {
            array.add(listFiles[i]);
        }
        return array;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_button_create:
                Intent intent = new Intent(MainActivity.this, CreateFileActivity.class);
                startActivity(intent);
                break;
        }
    }
}
