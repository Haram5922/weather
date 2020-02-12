package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class InfoCheck extends AppCompatActivity {

    Button ag, dag;
    CheckBox pr, pe, lo;
    View.OnClickListener cl;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infocheck);

        ag = (Button)findViewById(R.id.agree);
        dag = (Button)findViewById(R.id.disagree);
        pr = (CheckBox)findViewById(R.id.project);
        pe = (CheckBox)findViewById(R.id.personal);
        lo = (CheckBox)findViewById(R.id.location);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.agree :
                        if(pr.isChecked() && pe.isChecked() && lo.isChecked()){
                            i = new Intent(InfoCheck.this, MemberShip.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"약관에 동의 체크를 해주세요!",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.disagree :
                        i = new Intent(InfoCheck.this, LoginMain.class);
                        startActivity(i);
                        finish();
                        break;
                }

            }
        };
        ag.setOnClickListener(cl);
        dag.setOnClickListener(cl);
        pr.setOnClickListener(cl);
        pe.setOnClickListener(cl);
        lo.setOnClickListener(cl);
    }
}
