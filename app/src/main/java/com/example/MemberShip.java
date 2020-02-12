package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MemberShip extends AppCompatActivity {

    TextView pwcf,te6,possible;
    Button log, golo, button;
    ImageButton fb;
    EditText nic, pw;
    View.OnClickListener cl;
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
        setContentView(R.layout.membership);
        nic = (EditText) findViewById(R.id.nickname);
        pw = (EditText) findViewById(R.id.password);
        pwcf = (TextView) findViewById(R.id.passwordConfirm);
        log = (Button) findViewById(R.id.login);
        fb = (ImageButton) findViewById(R.id.feedback);
        golo = (Button) findViewById(R.id.gologin);
        button =(Button)findViewById(R.id.button);
        te6= (TextView)findViewById(R.id.te6);
        possible =(TextView)findViewById(R.id.possible);


        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login:
                        if (nic.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "아이디를 입력해주세요!", Toast.LENGTH_LONG).show();
                        } else if (pw.getText().toString().equals(pwcf.getText().toString())) {
                        } else
                            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다!", Toast.LENGTH_LONG).show();

                        if (pw.getText().toString().equals("") || pwcf.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요!", Toast.LENGTH_LONG).show();
                        } else if (nic.getText().toString().length() > 0 && pw.getText().toString().equals(pwcf.getText().toString())&& possible.getText().toString().equals("사용 가능한 아이디 입니다.")) {

                            i = new Intent(MemberShip.this, MemberShip_Complete.class);
                            i.putExtra("a", nic.getText().toString());
                            i.putExtra("b", pw.getText().toString());
                            startActivity(i);
                            finish();
                        }
                        break;
                    case R.id.feedback:
                        pw.setText("");
                        break;
                    case R.id.gologin:
                        i = new Intent(MemberShip.this, LoginMain.class);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.button:

                        if (nic.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(),"아이디를 입력해 주세요",Toast.LENGTH_LONG).show();
                        }else if (nic.getText().toString().length()<6){
                            Toast.makeText(getApplicationContext(),"아이디를 6자 이상 입력해 주세요",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"다시 눌러주세요",Toast.LENGTH_LONG).show();
                        }
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{

                                    String zzz = nic.getText().toString();

                                    Address = "http://shingu.freehost.kr/2_project/id_check.php?pid_email=" +
                                            zzz;


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
                        if(te6.getText().toString().length()>4&&nic.getText().toString().length()>6){
                            possible.setText("사용 가능한 아이디 입니다.");
                            log.setVisibility(View.VISIBLE);
                            possible.setVisibility(View.VISIBLE);
                        }
                        else if (te6.getText().toString().equals("no")){
                           possible.setText("사용중인 아이디 입니다.");
                           possible.setVisibility(View.VISIBLE);
                        }else if (nic.getText().toString().length()<6){
                            possible.setText("아이디가 너무 짧습니다.");
                            possible.setVisibility(View.VISIBLE);
                        }

                        break;
                }
            }
        };
        log.setOnClickListener(cl);
        fb.setOnClickListener(cl);
        golo.setOnClickListener(cl);
        button.setOnClickListener(cl);
    }
}
