package com.example.andrew.texteditor.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrew.texteditor.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Andrew on 12.04.2017.
 */

public class OpenFileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button buttonSave;

    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_file_activity);

        name = getIntent().getStringExtra("name");

        editText = (EditText) findViewById(R.id.open_file_activity_edit_text);
        buttonSave = (Button) findViewById(R.id.open_file_activity_button_save);

        editText.setText(readFromFile(name));
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_file_activity_button_save:
                String data = editText.getText().toString();
                writeToFile(name, data);
                break;
        }
    }

    //this method writes/rewrites data to a file in shared/external storage directory
    private void writeToFile(String fileName, String data){
        FileOutputStream fos = null;
        File dir = new File(Environment.getExternalStorageDirectory() + "/AIO/");

        try {
            fos = new FileOutputStream(dir + File.separator + fileName);
            byte[] buffer = data.getBytes();
            fos.write(buffer, 0, buffer.length);
            fos.close();
            Toast.makeText(OpenFileActivity.this, "added info insert file", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    //this method read selected file
    private String readFromFile(String fileName){
        String filepath = Environment.getExternalStorageDirectory() + "/AIO/" + fileName;

        StringBuilder sb = new StringBuilder();

        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(filepath)));
            while (scanner.hasNext()) {
                sb.append(scanner.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(scanner != null){
                try{
                    scanner.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
        return sb.toString();
    }
}
