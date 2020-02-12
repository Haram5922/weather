package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MemberShip_Complete extends AppCompatActivity {
    TextView te,te2,te3;
    Button comm;
    View.OnClickListener cl;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membershipcomplete);
        comm = (Button)findViewById(R.id.comm);
        final Intent intent = getIntent();
        final String a = intent.getStringExtra("a");
        final String b = intent.getStringExtra("b");

        comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final StringBuffer sb = new StringBuffer();
                        try{


                            URL url = new URL("http://shingu.freehost.kr/2_project/member_insert.php?pid_email=" +
                                    a +
                                    "&pwd=" +
                                    b
                            );

                            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                            if (connection != null){
                                connection.setConnectTimeout(2000);
                                connection.setUseCaches(false);
                                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"euc-kr"));
                                    while (true){
                                        String line = br.readLine();
                                        if(line == null) break;
                                        sb.append(line+"\n");
                                    }
                                    br.close();
                                }
                                connection.disconnect();
                            }
                            Log.d("test",sb.toString());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                }
                            });
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();

                Intent i = new Intent(MemberShip_Complete.this,LoginMain.class);
                startActivity(i);
                finish();
                Toast.makeText(getApplicationContext(),"가입 성공",Toast.LENGTH_LONG).show();
            }
        });


    }



}

