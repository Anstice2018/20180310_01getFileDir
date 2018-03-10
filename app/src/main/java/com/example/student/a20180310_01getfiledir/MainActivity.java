package com.example.student.a20180310_01getfiledir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    File p;
    File myFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p = getFilesDir();                                                                  // 1 getFilesDir
        myFile = new File(p, "myFile.txt");                           // 2 new File
        Log.d("MyFile", myFile.toString());
    }

    public void clickread(View v)
    {
        try{
            FileReader fr = new FileReader(myFile);                           // 3 read
            BufferedReader br = new BufferedReader(fr);                  // 4 進入緩衝
            String str = br.readLine();                                                 // 5 變成文字? readline
            Log.d("DATA", str);
            Gson gson = new Gson();                                                // 6 Gson
            Student mystu = gson.fromJson(str, Student.class);         // 7 gson.fromJson
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void clickwrite(View v)
    {
        try{
            Student stu = new Student();
            stu.ID = 1;
            stu.name = "Bob";
            stu.score = 95;
            FileWriter fw = new FileWriter(myFile);                             // 1 FileWriter
            Gson gson = new Gson();                                                 // 2 Gson

            String str = gson.toJson(stu);                                            // 3 gson.toJson
            fw.write(str);
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
