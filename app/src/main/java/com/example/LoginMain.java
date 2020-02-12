package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginMain extends AppCompatActivity {
    TextView te6,te7;
    ImageView iv;
    EditText em, pw;
    Button log, member;
    View.OnClickListener cl;
    Button button;
    Intent i;
    String ip = "";
    private static String Address;
    private static URL url;
    private static BufferedReader br;
    private static HttpURLConnection conn;
    private static String protocol = "GET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginmain);

        te6 = (TextView)findViewById(R.id.te6);

        te7 = (TextView)findViewById(R.id.te7);
        iv = (ImageView)findViewById(R.id.imageView4);
        em = (EditText)findViewById(R.id.email);
        pw = (EditText)findViewById(R.id.password);
        log = (Button)findViewById(R.id.login);
        member = (Button)findViewById(R.id.membership);
        button = (Button)findViewById(R.id.button);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.login :

//                        id = em.getText().toString();
//                        pw = pw.getText().toString();
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{

                                    String zzz = em.getText().toString();
                                    String zzz2 = pw.getText().toString();
                                    Address = "http://shingu.freehost.kr/2_project/login_check.php?pid_email=" +
                                            zzz +
                                            "&pwd=" +
                                            zzz2;

                                    url = new URL(Address);
                                    conn = (HttpURLConnection)url.openConnection();
                                    conn.setRequestMethod(protocol);
                                    br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

                                    String line;
                                    while((line = br.readLine()) != null) {
                                        if(line.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\" ?><root><item>")) {

                                            ip = line.replace("<?xml version=\"1.0\" encoding=\"utf-8\" ?><root><item>", "").replace("</item></root>", "");

                                           te6.setText(ip);


                                        }else{

                                        }


                                    }
                                    br.close();

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();


                        if (te6.getText().toString().equals("ok")){
                            i = new Intent(LoginMain.this, SNS_Main.class);
                            startActivity(i);
                            finish();
                        }

                        else if(!te6.getText().toString().equals("ok")){

                        te7.setText("회원정보를 확인하시고 두번씩 눌러주세요");
                        te7.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(),"한번더 눌러주세요",Toast.LENGTH_LONG).show();

                        }

                        break;
                    case R.id.membership :
                        i = new Intent(LoginMain.this, InfoCheck.class);
                        startActivity(i);
                        finish();
                        break;
                }

            }
        };
        log.setOnClickListener(cl);
        member.setOnClickListener(cl);

    }
}