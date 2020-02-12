package com.example;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SNS_Main extends AppCompatActivity {
    ImageView lg, ba;
    ImageButton cam, lo, pr, pr2, pr3, me, st, co, pl, ho;
    Button de, up, ca;
    View tr;
    EditText te;
    TextView te1, te2, te3, te4;
    View.OnClickListener cl;
    Intent i;
    //private final int m_nMaxLengthOfText = 500; //텍스트 제한 개수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sns_main);


        // te.setFilters(new InputFilter[] { new InputFilter.LengthFilter(m_nMaxLengthOfText) });
        lg = (ImageView) findViewById(R.id.logobar); //오여지 로고

        cam = (ImageButton) findViewById(R.id.camera);

        lo = (ImageButton) findViewById(R.id.location);
        pr = (ImageButton) findViewById(R.id.profile);
        pr2 = (ImageButton) findViewById(R.id.profile2);
        ba = (ImageView) findViewById(R.id.back);
        pr3 = (ImageButton) findViewById(R.id.profile3);
        up = (Button)findViewById(R.id.upload);
        me = (ImageButton) findViewById(R.id.menu);
        st = (ImageButton) findViewById(R.id.star);
        co = (ImageButton) findViewById(R.id.comu); //별 옆 댓글 아이콘
        pl = (ImageButton) findViewById(R.id.plus);
        ho = (ImageButton) findViewById(R.id.home);
        ca = (Button)findViewById(R.id.cancel);
        de = (Button)findViewById(R.id.delete);
        tr = (View)findViewById(R.id.transparent);

        te = (EditText) findViewById(R.id.text); //댓글

        te1 = (TextView) findViewById(R.id.text1); //글 업로드 한 사용자 아이디
        te2 = (TextView) findViewById(R.id.text2); //글 업로드 한 사용자 위치
        te3 = (TextView) findViewById(R.id.text3); //글 업로드 한 사용자 아이디
        te4 = (TextView) findViewById(R.id.text4); //업로드 한 게시물 문구

        lg.setImageResource(R.drawable.icon_theme2);
        ba.setImageResource(R.drawable.icon_background);

        tr.setVisibility(View.INVISIBLE);
        ca.setVisibility(View.INVISIBLE);
        de.setVisibility(View.INVISIBLE);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.camera : //카메라기능
                        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
                        if(permissionCheck == PackageManager.PERMISSION_DENIED) { // 권한 없음
                            ActivityCompat.requestPermissions(SNS_Main.this, new String[]{Manifest.permission.CAMERA},0);
                        } else { // 권한 있음
                            i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(i, 1);
                        }
                        break;
                    case R.id.location : //위치검색화면으로 이동
                        //i = new Intent(SNS_Main.this, .class); //위치 검색화면 이름
                        //startActivity(i);
                        //finish();
                        break;
                    case R.id.profile : //글 올린 사람의 프로필로 이동
                        //i = new Intent(SNS_Main.this, .class); //다른 유저 프로필 화면
                        //startActivity(i);
                        //finish();
                        break;
                    case R.id.profile3 : //내 활동으로 이동
                        i = new Intent(SNS_Main.this, MyActivity.class); //내 활동화면 이름
                        startActivity(i);
                        finish();
                        break;
                    case R.id.menu :
                        tr.setVisibility(View.VISIBLE);
                        ca.setVisibility(View.VISIBLE);
                        de.setVisibility(View.VISIBLE);
//                        tr.bringToFront();
//                        up.invalidate();
//                        up.setBackgroundColor(Color.argb(128,107,150,147));
                        break;
                    case R.id.cancel :
                        tr.setVisibility(View.INVISIBLE);
                        ca.setVisibility(View.INVISIBLE);
                        de.setVisibility(View.INVISIBLE);
//                        up.setBackgroundColor(Color.argb(255,107,150,147));
                        break;
                    case R.id.delete : //게시물 삭제
                        break;
                    case R.id.star : //좋아요 버튼
                        st.setImageResource(R.drawable.icon_star2);
                        Toast.makeText(getApplicationContext(),"이 게시물을 좋아합니다",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.comu :
                        te.setHint("댓글을 입력하세요");
                        break;
                    case R.id.plus : //게시글 쓰는 화면 이동
                        i = new Intent(SNS_Main.this,PostingUpload.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.home :
                        i = new Intent(SNS_Main.this,SNS_Main.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.up : //댓글 게시
                        //getText(te)???
                        break;

                }

            }
        };
        cam.setOnClickListener(cl);
        lo.setOnClickListener(cl);
        pr.setOnClickListener(cl);
        pr3.setOnClickListener(cl);
        me.setOnClickListener(cl);
        ca.setOnClickListener(cl);
        de.setOnClickListener(cl);
        st.setOnClickListener(cl);
        co.setOnClickListener(cl);
        pl.setOnClickListener(cl);
        ho.setOnClickListener(cl);
        up.setOnClickListener(cl);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==0) {
            if(grantResults[0]==0) {
                Toast.makeText(this,"카메라 권한 승인 완료!",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"카메라 권한 승인 실패, 권한 승낙 요청 바람",Toast.LENGTH_SHORT).show();
            }
        }
    }
}