package com.example.andrew.texteditor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrew.texteditor.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Andrew on 12.04.2017.
 */

public class CreateFileActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = CreateFileActivity.class.getSimpleName();

    private EditText editText, editTextName;
    private Button buttonSave;

    private String name;
    private String data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_file_activity);

        editTextName = (EditText) findViewById(R.id.create_file_activity_edit_text_name);
        editText = (EditText) findViewById(R.id.create_file_activity_edit_text);
        buttonSave = (Button) findViewById(R.id.create_file_activity_button_save);

        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_file_activity_button_save:
                String name = editTextName.getText().toString();
                String data = editText.getText().toString();
                writeToFile(name, data);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        name = editTextName.getText().toString();
        data = editText.getText().toString();
        Log.v(TAG, "onPause() - file name: " + name + "; data: " + data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextName.setText(name);
        editText.setText(data);
        Log.v(TAG, "onResume() - file name: " + name + "; data: " + data);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateFileActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //this method writes data to a file in shared/external storage directory
    private void writeToFile(String fileName, String data){
        FileOutputStream fos = null;
        File dir = new File(Environment.getExternalStorageDirectory() + "/AIO/");

        try {
            fos = new FileOutputStream(dir + File.separator + fileName);
            byte[] buffer = data.getBytes();
            fos.write(buffer, 0, buffer.length);
            fos.close();
            Toast.makeText(CreateFileActivity.this, "added info insert file", Toast.LENGTH_LONG).show();
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
}
