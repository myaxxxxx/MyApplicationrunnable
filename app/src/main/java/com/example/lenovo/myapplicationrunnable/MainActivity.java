package com.example.lenovo.myapplicationrunnable;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bt1 =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button)super.findViewById(R.id.button);
        final Runnable myworker = new Runnable() {
            @Override
            public void run() {
                long endTime =System.currentTimeMillis()+10*1000;

                while(System.currentTimeMillis()<endTime){
                    while(!Thread.interrupted()){
                        synchronized (this){
                            try{
                                wait();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread workThread = new Thread(null, myworker,"WorkThread");
                workThread.start();
            }
        });
    }
}
