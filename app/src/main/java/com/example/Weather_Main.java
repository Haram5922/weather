package com.example;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Weather_Main extends AppCompatActivity {

    ImageButton sns;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH00");
    SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("E요일", Locale.KOREA);
    String formatDate = simpleDateFormat.format(date);
    String formatDate2 = simpleDateFormat2.format(date);
    String formatDate3 = simpleDateFormat3.format(date);
    int timeplus = Integer.parseInt(formatDate2);
    public static int TO_GRID = 0;
    public static int TO_GPS = 1;
    private Button btnShowLocation;
    private TextView txtLat;
    private TextView txtLon;



    ImageView weathericon;
    TextView te, te2, te3, te5, mylocation,w_low,w_high,cc, cc2;
    TextView c_06,c_09,c_12,c_15,c_18,c_21,c_0;
    TextView h_06,h_09,h_12,h_15,h_18,h_21,h_0;
    TextView reh_06,reh_09,reh_12,reh_15,reh_18,reh_21,reh_00;
    TextView pop_06,pop_09,pop_12,pop_15,pop_18,pop_21,pop_00;
    TextView wsd_06,wsd_09,wsd_12,wsd_15,wsd_18,wsd_21,wsd_00;
    TextView vec_06,vec_09,vec_12,vec_15,vec_18,vec_21,vec_00;
    ImageView w_06,w_09,w_12,w_15,w_18,w_21,w_0;;
    TextView days;
    TextView feel;
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;
    Geocoder geocoder ;
    // GPSTracker class
    private GpsInfo gps;
    LatXLngY rs = new LatXLngY();





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_main);
        gps = new GpsInfo(Weather_Main.this);

        sns = (ImageButton) findViewById(R.id.sns);

        sns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginMain.class);
                startActivity(i);
            }
        });

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        btnShowLocation = (Button) findViewById(R.id.btn_start);
        txtLat = (TextView) findViewById(R.id.tv_latitude);
        txtLon = (TextView) findViewById(R.id.tv_longitude);
        te = (TextView) findViewById(R.id.textView);
        te2 = (TextView) findViewById(R.id.textView2);
        te3 = (TextView) findViewById(R.id.textView3);
        te5 = (TextView) findViewById(R.id.textView5);
        weathericon = (ImageView) findViewById(R.id.weathericon);

        w_high = (TextView) findViewById(R.id.w_high);
        w_low = (TextView) findViewById(R.id.w_low);
        cc = (TextView) findViewById(R.id.cc);
        cc2 = (TextView) findViewById(R.id.cc2);
        mylocation = (TextView) findViewById(R.id.mylocation);
        days = (TextView) findViewById(R.id.days);
        days.setText(formatDate3);

        feel = (TextView)findViewById(R.id.feel);


        w_0 =(ImageView) findViewById(R.id.w_0);
        w_06 =(ImageView) findViewById(R.id.w_06);
        w_09 =(ImageView) findViewById(R.id.w_09);
        w_12 =(ImageView) findViewById(R.id.w_12);
        w_15 =(ImageView) findViewById(R.id.w_15);
        w_18 =(ImageView) findViewById(R.id.w_18);
        w_21 =(ImageView) findViewById(R.id.w_21);


        c_0 = (TextView)findViewById(R.id.c_0);
        c_06 = (TextView)findViewById(R.id.c_06);
        c_09 = (TextView)findViewById(R.id.c_09);
        c_12 = (TextView)findViewById(R.id.c_12);
        c_15 = (TextView)findViewById(R.id.c_15);
        c_18 = (TextView)findViewById(R.id.c_18);
        c_21 = (TextView)findViewById(R.id.c_21);

        h_0 = (TextView)findViewById(R.id.h_0);
        h_06 = (TextView)findViewById(R.id.h_06);
        h_09 = (TextView)findViewById(R.id.h_09);
        h_12 = (TextView)findViewById(R.id.h_12);
        h_15 = (TextView)findViewById(R.id.h_15);
        h_18 = (TextView)findViewById(R.id.h_21);
        h_21 = (TextView)findViewById(R.id.h_18);

        reh_06 = (TextView)findViewById(R.id.reh_06);
        reh_09 = (TextView)findViewById(R.id.reh_09);
        reh_12 = (TextView)findViewById(R.id.reh_12);
        reh_15 = (TextView)findViewById(R.id.reh_15);
        reh_18 = (TextView)findViewById(R.id.reh_18);
        reh_21 = (TextView)findViewById(R.id.reh_21);
        reh_00 = (TextView)findViewById(R.id.reh_00);

        pop_06 = (TextView)findViewById(R.id.pop_06);
        pop_09 = (TextView)findViewById(R.id.pop_09);
        pop_12 = (TextView)findViewById(R.id.pop_12);
        pop_15 = (TextView)findViewById(R.id.pop_15);
        pop_18 = (TextView)findViewById(R.id.pop_18);
        pop_21 = (TextView)findViewById(R.id.pop_21);
        pop_00 = (TextView)findViewById(R.id.pop_00);


        wsd_06 = (TextView)findViewById(R.id.wsd_06);
        wsd_09 = (TextView)findViewById(R.id.wsd_09);
        wsd_12 = (TextView)findViewById(R.id.wsd_12);
        wsd_15 = (TextView)findViewById(R.id.wsd_15);
        wsd_18 = (TextView)findViewById(R.id.wsd_18);
        wsd_21 = (TextView)findViewById(R.id.wsd_21);
        wsd_00 = (TextView)findViewById(R.id.wsd_00);

        vec_06 = (TextView)findViewById(R.id.vec_06);
        vec_09 = (TextView)findViewById(R.id.vec_09);
        vec_12 = (TextView)findViewById(R.id.vec_12);
        vec_15 = (TextView)findViewById(R.id.vec_15);
        vec_18 = (TextView)findViewById(R.id.vec_18);
        vec_21 = (TextView)findViewById(R.id.vec_21);
        vec_00 = (TextView)findViewById(R.id.vec_00);



        LatXLngY tmp = convertGRID_GPS(TO_GRID,latitude , longitude); // 실질적 좌표 찍는곳
        geocoder = new Geocoder(this);
        reverseCoding();
        int a = (int)rs.x;
        int b = (int)rs.y;

        StrictMode.enableDefaults();
        boolean initem =false;
        boolean inbaseDate =false;
        boolean inbaseTime =false;
        boolean incategory =false;
        boolean infcstDate =false;
        boolean infcstTime =false;
        boolean infcstValue =false;
        boolean innx =false;
        boolean inny =false;

        String baseDate = null;
        String baseTime = null;
        String category = null;
        String fcstDate = null;
        String fcstTime = null;
        String fcstValue = null;
        String nx = null;
        String ny = null;

        try{
            URL url = new URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?serviceKey=rbBK0cTvcc%2F1TeCQZtMIo6g8T%2F8vXRg7f0n2a0jur88N3emy0193MJcZS9vuc%2F3VqBh5bBlmAefCD0WQnaSqsw%3D%3D&base_date=" +
                    formatDate +
                    "&base_time=" +
                    "0200"+
                    "&nx=" +
                    a +
                    "&ny=" +
                    b +
                    "&numOfRows=100&pageNo=1&_type=xml");
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.print("파싱시작");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch (parserEvent){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("baseDate")){
                            inbaseDate = true;
                        }
                        if(parser.getName().equals("baseTime")){
                            inbaseTime = true;
                        }
                        if(parser.getName().equals("category")){
                            incategory = true;
                        }
                        if(parser.getName().equals("fcstDate")){
                            infcstDate = true;
                        }
                        if(parser.getName().equals("fcstTime")){
                            infcstTime = true;
                        }
                        if(parser.getName().equals("fcstValue")){
                            infcstValue = true;
                        }
                        if(parser.getName().equals("nx")){
                            innx = true;
                        }
                        if(parser.getName().equals("ny")){
                            inny = true;
                        }
                        if(parser.getName().equals("message")){
                            te.setText(te.getText()+"에러");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if(inbaseDate){
                            baseDate = parser.getText();
                            inbaseDate = false;
                        }
                        if(inbaseTime){
                            baseTime = parser.getText();
                            inbaseTime = false;
                        }
                        if(incategory){
                            category = parser.getText();
                            incategory = false;
                        }
                        if(infcstDate){
                            fcstDate = parser.getText();
                            infcstDate = false;
                        }
                        if(infcstTime){
                            fcstTime = parser.getText();
                            infcstTime = false;
                        }
                        if(infcstValue){
                            fcstValue = parser.getText();
                            infcstValue = false;
                        }if(innx){
                        nx = parser.getText();
                        innx = false;
                    }
                        if(inny){
                            ny = parser.getText();
                            inny = false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")){
//                            te3.setText(te3.getText()+"날짜 : " + baseDate + "\n시간 : " +baseTime
//                                    + "\n종류 : " + category +"\n측정날짜 : " + fcstDate + "\n측정 시간 : "+ fcstTime
//                                    + "\n값 : "+ fcstValue +"\n위도 : " +nx +"\n경도 : " + ny
//                            );
//------------------------------------------------------온도-------------------------------------------------------------------//
                            if(category.equals("T3H")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                c_06.setText("온도 :"+fcstValue+"°");
                            }if (category.equals("T3H")&& fcstTime.equals("0900")&&fcstDate.equals(formatDate)){
                                c_09.setText("온도 :"+fcstValue+"°");
                            }
                            if (category.equals("T3H")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                c_12.setText("온도 :"+fcstValue+"°");
                            }
                            if (category.equals("T3H")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                c_15.setText("온도 :"+fcstValue+"°");
                            }
                            if (category.equals("T3H")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                c_18.setText("온도 :"+fcstValue+"°");
                            }
                            if (category.equals("T3H")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                c_21.setText("온도 :"+fcstValue+"°");
                            }
                            if (category.equals("T3H")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                c_0.setText("온도 :"+fcstValue+"°");
                            }
                            //------------------------------------------------------습도-------------------------------------------------------------------//
                            if(category.equals("REH")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                h_06.setText("습도 :"+fcstValue+"%");
                            }if (category.equals("REH")&& fcstTime.equals("0900")&&fcstDate.equals(formatDate)){
                                h_09.setText("습도 :"+fcstValue+"%");
                            }
                            if (category.equals("REH")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                h_12.setText("습도 :"+fcstValue+"%");
                            }
                            if (category.equals("REH")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                h_15.setText("습도 :"+fcstValue+"%");
                            }
                            if (category.equals("REH")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                h_18.setText("습도 :"+fcstValue+"%");
                            }
                            if (category.equals("REH")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                h_21.setText("습도 :"+fcstValue+"%");
                            }
                            if (category.equals("REH")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                h_0.setText("습도 :"+fcstValue+"%");
                            }
                            //------------------------------------------------------최고, 최저 기온-------------------------------------------------------------------//
                            if(category.equals("TMN")&& fcstDate.equals(formatDate)){
                                w_low.setText(fcstValue+"°");
                            }
                            if(category.equals("TMX")&& fcstDate.equals(formatDate)){
                                w_high.setText(fcstValue+"°");
                            }
                            //------------------------------------------------------강수확률-------------------------------------------------------------------//
                            if (category.equals("POP")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                reh_06.setText("강수확률 :"+fcstValue+"%");
                            }
                            if (category.equals("POP")&& fcstTime.equals("0900")&&fcstDate.equals(formatDate)){
                                reh_09.setText("강수확률 :"+fcstValue+"%");
                            }
                            if (category.equals("POP")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                reh_12.setText("강수확률 :"+fcstValue+"%");
                            }
                            if (category.equals("POP")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                reh_15.setText("강수확률 :"+fcstValue+"%");
                            }
                            if (category.equals("POP")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                reh_18.setText("강수확률 :"+fcstValue+"%");
                            }
                            if (category.equals("POP")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                reh_21.setText("강수확률 :"+fcstValue+"%");
                            }
                            if (category.equals("POP")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                reh_00.setText("강수확률 :"+fcstValue+"%");
                            }
                            //------------------------------------------------------강수량-------------------------------------------------------------------//
                            if (category.equals("R06")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                pop_06.setText("강수량 :"+fcstValue+"mm");
                            }
                            if (category.equals("R06")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                pop_09.setText("강수량 :"+fcstValue+"mm");
                            }
                            if (category.equals("R06")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                pop_12.setText("강수량 :"+fcstValue+"mm");
                            }
                            if (category.equals("R06")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                pop_15.setText("강수량 :"+fcstValue+"mm");
                            }
                            if (category.equals("R06")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                pop_18.setText("강수량 :"+fcstValue+"mm");
                            }
                            if (category.equals("R06")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                pop_21.setText("강수량 :"+fcstValue+"mm");
                            }
                            if (category.equals("R06")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                pop_00.setText("강수량 :"+fcstValue+"mm");
                            }

                            //------------------------------------------------------풍속-------------------------------------------------------------------//
                            if (category.equals("WSD")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                wsd_06.setText("풍속 :"+fcstValue+" m/s");
                            }
                            if (category.equals("WSD")&& fcstTime.equals("0900")&&fcstDate.equals(formatDate)){
                                wsd_09.setText("풍속 :"+fcstValue+" m/s");
                            }
                            if (category.equals("WSD")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                wsd_12.setText("풍속 :"+fcstValue+" m/s");
                            }
                            if (category.equals("WSD")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                wsd_15.setText("풍속 :"+fcstValue+" m/s");
                            }
                            if (category.equals("WSD")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                wsd_18.setText("풍속 :"+fcstValue+"m/s");
                            }
                            if (category.equals("WSD")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                wsd_21.setText("풍속 :"+fcstValue+" m/s");
                            }
                            if (category.equals("WSD")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                wsd_00.setText("풍속 :"+fcstValue+" m/s");
                            }
                            //------------------------------------------------------날씨에 맞게 이미지 변경-------------------------------------------------------------------//






                            //------------------------------------------------------시간 날씨에 맞게----------------------------------------------------------
                            if (category.equals("SKY")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                if (fcstValue.equals("1")){
                                    w_06.setImageResource(R.drawable.sunny);

                                }else if(fcstValue.equals("2")){
                                    w_06.setImageResource(R.drawable.few_sunny);

                                }else if(fcstValue.equals("3")){
                                    w_06.setImageResource(R.drawable.few_sunny);

                                }
                                else if(fcstValue.equals("4")){
                                    w_06.setImageResource(R.drawable.cloud);

                                }
                            }
                            if (category.equals("PTY")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                if(fcstValue.equals("1")){
                                    w_06.setImageResource(R.drawable.rainy);
                                }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                    w_06.setImageResource(R.drawable.sun_snow);
                                }
                            }


                            if (category.equals("SKY")&& fcstTime.equals("0900")&&fcstDate.equals(formatDate)){
                                if (fcstValue.equals("1")){
                                    w_09.setImageResource(R.drawable.sunny);

                                }else if(fcstValue.equals("2")){
                                    w_09.setImageResource(R.drawable.few_sunny);

                                }else if(fcstValue.equals("3")){
                                    w_09.setImageResource(R.drawable.few_sunny);

                                }
                                else if(fcstValue.equals("4")){
                                    w_09.setImageResource(R.drawable.cloud);

                                }
                            }
                            if (category.equals("PTY")&& fcstTime.equals("0900")&&fcstDate.equals(formatDate)){
                                if(fcstValue.equals("1")){
                                    w_09.setImageResource(R.drawable.rainy);
                                }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                    w_09.setImageResource(R.drawable.sun_snow);
                                }
                            }

                            if (category.equals("SKY")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                if (fcstValue.equals("1")){
                                    w_12.setImageResource(R.drawable.sunny);

                                }else if(fcstValue.equals("2")){
                                    w_12.setImageResource(R.drawable.few_sunny);

                                }else if(fcstValue.equals("3")){
                                    w_12.setImageResource(R.drawable.few_sunny);

                                }
                                else if(fcstValue.equals("4")){
                                    w_12.setImageResource(R.drawable.cloud);

                                }
                            }       if (category.equals("PTY")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                if(fcstValue.equals("1")){
                                    w_12.setImageResource(R.drawable.rainy);
                                }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                    w_12.setImageResource(R.drawable.sun_snow);
                                }
                            }



                            if (category.equals("SKY")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                if (fcstValue.equals("1")){
                                    w_15.setImageResource(R.drawable.sunny);

                                }else if(fcstValue.equals("2")){
                                    w_15.setImageResource(R.drawable.few_sunny);

                                }else if(fcstValue.equals("3")){
                                    w_15.setImageResource(R.drawable.few_sunny);

                                }
                                else if(fcstValue.equals("4")){
                                    w_15.setImageResource(R.drawable.cloud);

                                }
                            }
                            if (category.equals("PTY")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                if(fcstValue.equals("1")){
                                    w_15.setImageResource(R.drawable.rainy);
                                }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                    w_15.setImageResource(R.drawable.sun_snow);
                                }
                            }


                            if (category.equals("SKY")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                if (fcstValue.equals("1")){
                                    w_18.setImageResource(R.drawable.sunny);

                                }else if(fcstValue.equals("2")){
                                    w_18.setImageResource(R.drawable.few_sunny);

                                }else if(fcstValue.equals("3")){
                                    w_18.setImageResource(R.drawable.few_sunny);

                                }
                                else if(fcstValue.equals("4")){
                                    w_18.setImageResource(R.drawable.cloud);

                                }
                            }
                            if (category.equals("PTY")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                if(fcstValue.equals("1")){
                                    w_18.setImageResource(R.drawable.rainy);
                                }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                    w_18.setImageResource(R.drawable.sun_snow);
                                }
                            }


                            if (category.equals("SKY")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                if (fcstValue.equals("1")){
                                    w_21.setImageResource(R.drawable.sunny);

                                }else if(fcstValue.equals("2")){
                                    w_21.setImageResource(R.drawable.few_sunny);

                                }else if(fcstValue.equals("3")){
                                    w_21.setImageResource(R.drawable.few_sunny);

                                }
                                else if(fcstValue.equals("4")){
                                    w_21.setImageResource(R.drawable.cloud);

                                }
                            }
                            if (category.equals("PTY")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                if(fcstValue.equals("1")){
                                    w_21.setImageResource(R.drawable.rainy);
                                }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                    w_21.setImageResource(R.drawable.sun_snow);
                                }
                            }



                            if (category.equals("SKY")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                if (fcstValue.equals("1")){
                                    w_0.setImageResource(R.drawable.sunny);

                                }else if(fcstValue.equals("2")){
                                    w_0.setImageResource(R.drawable.few_sunny);

                                }else if(fcstValue.equals("3")){
                                    w_0.setImageResource(R.drawable.few_sunny);

                                }
                                else if(fcstValue.equals("4")){
                                    w_0.setImageResource(R.drawable.cloud);

                                }
                            }

                            if (category.equals("PTY")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                if(fcstValue.equals("1")){
                                    w_0.setImageResource(R.drawable.rainy);
                                }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                    w_0.setImageResource(R.drawable.sun_snow);
                                }
                            }


                            //------------------------------------------------------풍향-------------------------------------------------------------------//
                            if (category.equals("VEC")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                if (Integer.parseInt(fcstValue)<45){
                                    vec_06.setText("풍향 : N-NE");
                                }
                                else if(Integer.parseInt(fcstValue)>44 && Integer.parseInt(fcstValue)<90){
                                    vec_06.setText("풍향 : NE-E");
                                }
                                else if(Integer.parseInt(fcstValue)>89 && Integer.parseInt(fcstValue)<135){
                                    vec_06.setText("풍향 : E-SE");
                                }
                                else if(Integer.parseInt(fcstValue)>134 && Integer.parseInt(fcstValue)<180){
                                    vec_06.setText("풍향 : SE-S");
                                }
                                else if(Integer.parseInt(fcstValue)>179 && Integer.parseInt(fcstValue)<225){
                                    vec_06.setText("풍향 : S-SW");
                                }
                                else if(Integer.parseInt(fcstValue)>224 && Integer.parseInt(fcstValue)<270){
                                    vec_06.setText("풍향 : SW-W");
                                }
                                else if(Integer.parseInt(fcstValue)>269 && Integer.parseInt(fcstValue)<315){
                                    vec_06.setText("풍향 : W-NW");
                                }
                                else if(Integer.parseInt(fcstValue)>314 && Integer.parseInt(fcstValue)<361){
                                    vec_06.setText("풍향 : NW-N");
                                }
                            }
                            if (category.equals("VEC")&& fcstTime.equals("0900")&&fcstDate.equals(formatDate)){
                                if (Integer.parseInt(fcstValue)<45){
                                    vec_09.setText("풍향 : N-NE");
                                }
                                else if(Integer.parseInt(fcstValue)>44 && Integer.parseInt(fcstValue)<90){
                                    vec_09.setText("풍향 : NE-E");
                                }
                                else if(Integer.parseInt(fcstValue)>89 && Integer.parseInt(fcstValue)<135){
                                    vec_09.setText("풍향 : E-SE");
                                }
                                else if(Integer.parseInt(fcstValue)>134 && Integer.parseInt(fcstValue)<180){
                                    vec_09.setText("풍향 : SE-S");
                                }
                                else if(Integer.parseInt(fcstValue)>179 && Integer.parseInt(fcstValue)<225){
                                    vec_09.setText("풍향 : S-SW");
                                }
                                else if(Integer.parseInt(fcstValue)>224 && Integer.parseInt(fcstValue)<270){
                                    vec_09.setText("풍향 : SW-W");
                                }
                                else if(Integer.parseInt(fcstValue)>269 && Integer.parseInt(fcstValue)<315){
                                    vec_09.setText("풍향 : W-NW");
                                }
                                else if(Integer.parseInt(fcstValue)>314 && Integer.parseInt(fcstValue)<361){
                                    vec_09.setText("풍향 : NW-N");
                                }
                            }
                            if (category.equals("VEC")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                if (Integer.parseInt(fcstValue)<45){
                                    vec_12.setText("풍향 : N-NE");
                                }
                                else if(Integer.parseInt(fcstValue)>44 && Integer.parseInt(fcstValue)<90){
                                    vec_12.setText("풍향 : NE-E");
                                }
                                else if(Integer.parseInt(fcstValue)>89 && Integer.parseInt(fcstValue)<135){
                                    vec_12.setText("풍향 : E-SE");
                                }
                                else if(Integer.parseInt(fcstValue)>134 && Integer.parseInt(fcstValue)<180){
                                    vec_12.setText("풍향 : SE-S");
                                }
                                else if(Integer.parseInt(fcstValue)>179 && Integer.parseInt(fcstValue)<225){
                                    vec_12.setText("풍향 : S-SW");
                                }
                                else if(Integer.parseInt(fcstValue)>224 && Integer.parseInt(fcstValue)<270){
                                    vec_12.setText("풍향 : SW-W");
                                }
                                else if(Integer.parseInt(fcstValue)>269 && Integer.parseInt(fcstValue)<315){
                                    vec_12.setText("풍향 : W-NW");
                                }
                                else if(Integer.parseInt(fcstValue)>314 && Integer.parseInt(fcstValue)<361){
                                    vec_12.setText("풍향 : NW-N");
                                }
                            }
                            if (category.equals("VEC")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                if (Integer.parseInt(fcstValue)<45){
                                    vec_15.setText("풍향 : N-NE");
                                }
                                else if(Integer.parseInt(fcstValue)>44 && Integer.parseInt(fcstValue)<90){
                                    vec_15.setText("풍향 : NE-E");
                                }
                                else if(Integer.parseInt(fcstValue)>89 && Integer.parseInt(fcstValue)<135){
                                    vec_15.setText("풍향 : E-SE");
                                }
                                else if(Integer.parseInt(fcstValue)>134 && Integer.parseInt(fcstValue)<180){
                                    vec_15.setText("풍향 : SE-S");
                                }
                                else if(Integer.parseInt(fcstValue)>179 && Integer.parseInt(fcstValue)<225){
                                    vec_15.setText("풍향 : S-SW");
                                }
                                else if(Integer.parseInt(fcstValue)>224 && Integer.parseInt(fcstValue)<270){
                                    vec_15.setText("풍향 : SW-W");
                                }
                                else if(Integer.parseInt(fcstValue)>269 && Integer.parseInt(fcstValue)<315){
                                    vec_15.setText("풍향 : W-NW");
                                }
                                else if(Integer.parseInt(fcstValue)>314 && Integer.parseInt(fcstValue)<361){
                                    vec_15.setText("풍향 : NW-N");
                                }
                            }
                            if (category.equals("VEC")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                if (Integer.parseInt(fcstValue)<45){
                                    vec_18.setText("풍향 : N-NE");
                                }
                                else if(Integer.parseInt(fcstValue)>44 && Integer.parseInt(fcstValue)<90){
                                    vec_18.setText("풍향 : NE-E");
                                }
                                else if(Integer.parseInt(fcstValue)>89 && Integer.parseInt(fcstValue)<135){
                                    vec_18.setText("풍향 : E-SE");
                                }
                                else if(Integer.parseInt(fcstValue)>134 && Integer.parseInt(fcstValue)<180){
                                    vec_18.setText("풍향 : SE-S");
                                }
                                else if(Integer.parseInt(fcstValue)>179 && Integer.parseInt(fcstValue)<225){
                                    vec_18.setText("풍향 : S-SW");
                                }
                                else if(Integer.parseInt(fcstValue)>224 && Integer.parseInt(fcstValue)<270){
                                    vec_18.setText("풍향 : SW-W");
                                }
                                else if(Integer.parseInt(fcstValue)>269 && Integer.parseInt(fcstValue)<315){
                                    vec_18.setText("풍향 : W-NW");
                                }
                                else if(Integer.parseInt(fcstValue)>314 && Integer.parseInt(fcstValue)<361){
                                    vec_18.setText("풍향 : NW-N");
                                }
                            }
                            if (category.equals("VEC")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                if (Integer.parseInt(fcstValue)<45){
                                    vec_21.setText("풍향 : N-NE");
                                }
                                else if(Integer.parseInt(fcstValue)>44 && Integer.parseInt(fcstValue)<90){
                                    vec_21.setText("풍향 : NE-E");
                                }
                                else if(Integer.parseInt(fcstValue)>89 && Integer.parseInt(fcstValue)<135){
                                    vec_21.setText("풍향 : E-SE");
                                }
                                else if(Integer.parseInt(fcstValue)>134 && Integer.parseInt(fcstValue)<180){
                                    vec_21.setText("풍향 : SE-S");
                                }
                                else if(Integer.parseInt(fcstValue)>179 && Integer.parseInt(fcstValue)<225){
                                    vec_21.setText("풍향 : S-SW");
                                }
                                else if(Integer.parseInt(fcstValue)>224 && Integer.parseInt(fcstValue)<270){
                                    vec_21.setText("풍향 : SW-W");
                                }
                                else if(Integer.parseInt(fcstValue)>269 && Integer.parseInt(fcstValue)<315){
                                    vec_21.setText("풍향 : W-NW");
                                }
                                else if(Integer.parseInt(fcstValue)>314 && Integer.parseInt(fcstValue)<361){
                                    vec_21.setText("풍향 : NW-N");
                                }
                            }
                            if (category.equals("VEC")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                if (Integer.parseInt(fcstValue)<45){
                                    vec_00.setText("풍향 : N-NE");
                                }
                                else if(Integer.parseInt(fcstValue)>44 && Integer.parseInt(fcstValue)<90){
                                    vec_00.setText("풍향 : NE-E");
                                }
                                else if(Integer.parseInt(fcstValue)>89 && Integer.parseInt(fcstValue)<135){
                                    vec_00.setText("풍향 : E-SE");
                                }
                                else if(Integer.parseInt(fcstValue)>134 && Integer.parseInt(fcstValue)<180){
                                    vec_00.setText("풍향 : SE-S");
                                }
                                else if(Integer.parseInt(fcstValue)>179 && Integer.parseInt(fcstValue)<225){
                                    vec_00.setText("풍향 : S-SW");
                                }
                                else if(Integer.parseInt(fcstValue)>224 && Integer.parseInt(fcstValue)<270){
                                    vec_00.setText("풍향 : SW-W");
                                }
                                else if(Integer.parseInt(fcstValue)>269 && Integer.parseInt(fcstValue)<315){
                                    vec_00.setText("풍향 : W-NW");
                                }
                                else if(Integer.parseInt(fcstValue)>314 && Integer.parseInt(fcstValue)<361){
                                    vec_00.setText("풍향 : NW-N");
                                }
                            }
//--------------------------------------------------------------------------시간 조정----------------------------------------------//
                            if (timeplus<0600){
                                if (category.equals("T3H")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                    te5.setText(fcstValue+"°");
                                    feel.setText(Integer.parseInt(fcstValue)+2+"°");
                                    if(Integer.parseInt(fcstValue)<0){
                                        cc2.setText("추워요");
                                    }else if(Integer.parseInt(fcstValue)>0 && Integer.parseInt(fcstValue) <10){
                                        cc2.setText("쌀쌀 해요");
                                    }else if (Integer.parseInt(fcstValue)>9 && Integer.parseInt(fcstValue) < 15){
                                        cc2.setText("조금 추워요");
                                    }else if (Integer.parseInt(fcstValue)> 14 && Integer.parseInt(fcstValue) < 22){
                                        cc2.setText("선선 해요");
                                    }else if (Integer.parseInt(fcstValue)>21 && Integer.parseInt(fcstValue)<27){
                                        cc2.setText("적당 해요");
                                    }else if (Integer.parseInt(fcstValue) > 26 && Integer.parseInt(fcstValue)<30){
                                        cc2.setText("더워요");
                                    }else if (Integer.parseInt(fcstValue)>29){
                                        cc2.setText("너무 더워요");
                                    }
                                }
                                if (category.equals("SKY")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                    if (fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.sunny);
                                        cc.setText("맑음");
                                    }else if(fcstValue.equals("2")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 조금");
                                    }else if(fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 많음");
                                    }
                                    else if(fcstValue.equals("4")){
                                        weathericon.setImageResource(R.drawable.cloud);
                                        cc.setText("흐림");
                                    }
                                }
                                if (category.equals("PTY")&& fcstTime.equals("0300")&&baseDate.equals(formatDate)){
                                    if(fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.rainy);
                                    }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.sun_snow);
                                    }
                                }

                            }else if(timeplus>0500 && timeplus<900){
                                if (category.equals("T3H")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                    te5.setText(fcstValue+"°");
                                    feel.setText(Integer.parseInt(fcstValue)+2+"°");
                                    if(Integer.parseInt(fcstValue)<0){
                                        cc2.setText("추워요");
                                    }else if(Integer.parseInt(fcstValue)>0 && Integer.parseInt(fcstValue) <10){
                                        cc2.setText("쌀쌀 해요");
                                    }else if (Integer.parseInt(fcstValue)>9 && Integer.parseInt(fcstValue) < 15){
                                        cc2.setText("조금 추워요");
                                    }else if (Integer.parseInt(fcstValue)> 14 && Integer.parseInt(fcstValue) < 22){
                                        cc2.setText("선선 해요");
                                    }else if (Integer.parseInt(fcstValue)>21 && Integer.parseInt(fcstValue)<27){
                                        cc2.setText("적당 해요");
                                    }else if (Integer.parseInt(fcstValue) > 26 && Integer.parseInt(fcstValue)<30){
                                        cc2.setText("더워요");
                                    }else if (Integer.parseInt(fcstValue)>29){
                                        cc2.setText("너무 더워요");
                                    }
                                }
                                if (category.equals("SKY")&& fcstTime.equals("0600")&&fcstDate.equals(formatDate)){
                                    if (fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.sunny);
                                        cc.setText("맑음");
                                    }else if(fcstValue.equals("2")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 조금");
                                    }else if(fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 많음");
                                    }
                                    else if(fcstValue.equals("4")){
                                        weathericon.setImageResource(R.drawable.cloud);
                                        cc.setText("흐림");
                                    }
                                }
                                if (category.equals("PTY")&& fcstTime.equals("0900")&&fcstDate.equals(formatDate)){
                                    if(fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.rainy);
                                    }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.sun_snow);
                                    }
                                }
                            }

                            else if(timeplus>900 && timeplus<1159){
                                if (category.equals("T3H")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                    te5.setText(fcstValue+"°");
                                    feel.setText(Integer.parseInt(fcstValue)+2+"°");
                                    if(Integer.parseInt(fcstValue)<0){
                                        cc2.setText("추워요");
                                    }else if(Integer.parseInt(fcstValue)>0 && Integer.parseInt(fcstValue) <10){
                                        cc2.setText("쌀쌀 해요");
                                    }else if (Integer.parseInt(fcstValue)>9 && Integer.parseInt(fcstValue) < 15){
                                        cc2.setText("조금 추워요");
                                    }else if (Integer.parseInt(fcstValue)> 14 && Integer.parseInt(fcstValue) < 22){
                                        cc2.setText("선선 해요");
                                    }else if (Integer.parseInt(fcstValue)>21 && Integer.parseInt(fcstValue)<27){
                                        cc2.setText("적당 해요");
                                    }else if (Integer.parseInt(fcstValue) > 26 && Integer.parseInt(fcstValue)<30){
                                        cc2.setText("더워요");
                                    }else if (Integer.parseInt(fcstValue)>29){
                                        cc2.setText("너무 더워요");
                                    }
                                }
                                if (category.equals("SKY")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                    if (fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.sunny);
                                        cc.setText("맑음");
                                    }else if(fcstValue.equals("2")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 조금");
                                    }else if(fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 많음");
                                    }
                                    else if(fcstValue.equals("4")){
                                        weathericon.setImageResource(R.drawable.cloud);
                                        cc.setText("흐림");
                                    }
                                }
                                if (category.equals("PTY")&& fcstTime.equals("1200")&&fcstDate.equals(formatDate)){
                                    if(fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.rainy);
                                    }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.sun_snow);
                                    }
                                }
                            }
                            else if(timeplus>1200 && timeplus<1500){
                                if (category.equals("T3H")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                    te5.setText(fcstValue+"°");
                                    feel.setText(Integer.parseInt(fcstValue)+2+"°");
                                    if(Integer.parseInt(fcstValue)<0){
                                        cc2.setText("추워요");
                                    }else if(Integer.parseInt(fcstValue)>0 && Integer.parseInt(fcstValue) <10){
                                        cc2.setText("쌀쌀 해요");
                                    }else if (Integer.parseInt(fcstValue)>9 && Integer.parseInt(fcstValue) < 15){
                                        cc2.setText("조금 추워요");
                                    }else if (Integer.parseInt(fcstValue)> 14 && Integer.parseInt(fcstValue) < 22){
                                        cc2.setText("선선 해요");
                                    }else if (Integer.parseInt(fcstValue)>21 && Integer.parseInt(fcstValue)<27){
                                        cc2.setText("적당 해요");
                                    }else if (Integer.parseInt(fcstValue) > 26 && Integer.parseInt(fcstValue)<30){
                                        cc2.setText("더워요");
                                    }else if (Integer.parseInt(fcstValue)>29){
                                        cc2.setText("너무 더워요");
                                    }
                                }
                                if (category.equals("SKY")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                    if (fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.sunny);
                                        cc.setText("맑음");
                                    }else if(fcstValue.equals("2")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 조금");
                                    }else if(fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 많음");
                                    }
                                    else if(fcstValue.equals("4")){
                                        weathericon.setImageResource(R.drawable.cloud);
                                        cc.setText("흐림");
                                    }
                                }

                                if (category.equals("PTY")&& fcstTime.equals("1500")&&fcstDate.equals(formatDate)){
                                    if(fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.rainy);
                                    }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.sun_snow);
                                    }
                                }
                            }else if(timeplus>1800 && timeplus<2100){
                                if (category.equals("T3H")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                    te5.setText(fcstValue+"°");
                                    feel.setText(Integer.parseInt(fcstValue)+2+"°");
                                    if(Integer.parseInt(fcstValue)<0){
                                        cc2.setText("추워요");
                                    }else if(Integer.parseInt(fcstValue)>0 && Integer.parseInt(fcstValue) <10){
                                        cc2.setText("쌀쌀 해요");
                                    }else if (Integer.parseInt(fcstValue)>9 && Integer.parseInt(fcstValue) < 15){
                                        cc2.setText("조금 추워요");
                                    }else if (Integer.parseInt(fcstValue)> 14 && Integer.parseInt(fcstValue) < 22){
                                        cc2.setText("선선 해요");
                                    }else if (Integer.parseInt(fcstValue)>21 && Integer.parseInt(fcstValue)<27){
                                        cc2.setText("적당 해요");
                                    }else if (Integer.parseInt(fcstValue) > 26 && Integer.parseInt(fcstValue)<30){
                                        cc2.setText("더워요");
                                    }else if (Integer.parseInt(fcstValue)>29){
                                        cc2.setText("너무 더워요");
                                    }
                                }
                                if (category.equals("SKY")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                    if (fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.sunny);
                                        cc.setText("맑음");
                                    }else if(fcstValue.equals("2")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 조금");
                                    }else if(fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 많음");
                                    }
                                    else if(fcstValue.equals("4")){
                                        weathericon.setImageResource(R.drawable.cloud);
                                        cc.setText("흐림");
                                    }
                                }
                                if (category.equals("PTY")&& fcstTime.equals("1800")&&fcstDate.equals(formatDate)){
                                    if(fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.rainy);
                                    }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.sun_snow);
                                    }
                                }
                            }else if (timeplus>2100 && timeplus<2400){
                                if (category.equals("T3H")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                    te5.setText(fcstValue+"°");
                                    feel.setText(Integer.parseInt(fcstValue)+2+"°");
                                    if(Integer.parseInt(fcstValue)<0){
                                        cc2.setText("추워요");
                                    }else if(Integer.parseInt(fcstValue)>0 && Integer.parseInt(fcstValue) <10){
                                        cc2.setText("쌀쌀 해요");
                                    }else if (Integer.parseInt(fcstValue)>9 && Integer.parseInt(fcstValue) < 15){
                                        cc2.setText("조금 추워요");
                                    }else if (Integer.parseInt(fcstValue)> 14 && Integer.parseInt(fcstValue) < 22){
                                        cc2.setText("선선 해요");
                                    }else if (Integer.parseInt(fcstValue)>21 && Integer.parseInt(fcstValue)<27){
                                        cc2.setText("적당 해요");
                                    }else if (Integer.parseInt(fcstValue) > 26 && Integer.parseInt(fcstValue)<30){
                                        cc2.setText("더워요");
                                    }else if (Integer.parseInt(fcstValue)>29){
                                        cc2.setText("너무 더워요");
                                    }
                                }
                                if (category.equals("SKY")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                    if (fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.sunny);
                                        cc.setText("맑음");
                                    }else if(fcstValue.equals("2")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 조금");
                                    }else if(fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.few_sunny);
                                        cc.setText("구름 많음");
                                    }
                                    else if(fcstValue.equals("4")){
                                        weathericon.setImageResource(R.drawable.cloud);
                                        cc.setText("흐림");
                                    }
                                }
                                if (category.equals("PTY")&& fcstTime.equals("2100")&&fcstDate.equals(formatDate)){
                                    if(fcstValue.equals("1")){
                                        weathericon.setImageResource(R.drawable.rainy);
                                    }else if (fcstValue.equals("2") || fcstValue.equals("3")){
                                        weathericon.setImageResource(R.drawable.sun_snow);
                                    }
                                }
                            }

                        }
                        break;
                }
                parserEvent = parser.next();
            }

        }catch (Exception e){
            te.setText("Error");
            e.printStackTrace();
        }

        // GPS 정보를 보여주기 위한 이벤트 클래스 등록
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // 권한 요청을 해야 함
                if (!isPermission) {
                    callPermission();
                    return;
                }

                gps = new GpsInfo(Weather_Main.this);
                // GPS 사용유무 가져오기
                if (gps.isGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    txtLat.setText(String.valueOf(latitude));
                    txtLon.setText(String.valueOf(longitude));

//                    Toast.makeText(
//                            getApplicationContext(),
//                            "당신의 위치 - \n위도: " + latitude + "\n경도: " + longitude,
//                            Toast.LENGTH_LONG).show();
                } else {
                    // GPS 를 사용할수 없으므로
                    gps.showSettingsAlert();
                }

            }
        });


        callPermission();  // 권한 요청을 해야 함
        btnShowLocation.performClick(); //바로실행
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessFineLocation = true;

        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            isAccessCoarseLocation = true;
        }

        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
        }
    }

    // 전화번호 권한 요청
    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
        }
    }

    private LatXLngY convertGRID_GPS(int mode, double lat_X, double lng_Y ) {
        double RE = 6371.00877; // 지구 반경(km)
        double GRID = 5.0; // 격자 간격(km)
        double SLAT1 = 30.0; // 투영 위도1(degree)
        double SLAT2 = 60.0; // 투영 위도2(degree)
        double OLON = 126.0; // 기준점 경도(degree)
        double OLAT = 38.0; // 기준점 위도(degree)
        double XO = 43; // 기준점 X좌표(GRID)
        double YO = 136; // 기1준점 Y좌표(GRID)

        //
        // LCC DFS 좌표변환 ( code : "TO_GRID"(위경도->좌표, lat_X:위도,  lng_Y:경도), "TO_GPS"(좌표->위경도,  lat_X:x, lng_Y:y) )
        //


        double DEGRAD = Math.PI / 180.0;
        double RADDEG = 180.0 / Math.PI;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);

        if (mode == TO_GRID) {
            rs.lat = lat_X;
            rs.lng = lng_Y;
            double ra = Math.tan(Math.PI * 0.25 + (lat_X) * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            double theta = lng_Y * DEGRAD - olon;
            if (theta > Math.PI) theta -= 2.0 * Math.PI;
            if (theta < -Math.PI) theta += 2.0 * Math.PI;
            theta *= sn;
            rs.x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
            rs.y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
        } else {
            rs.x = lat_X;
            rs.y = lng_Y;
            double xn = lat_X - XO;
            double yn = ro - lng_Y + YO;
            double ra = Math.sqrt(xn * xn + yn * yn);
            if (sn < 0.0) {
                ra = -ra;
            }
            double alat = Math.pow((re * sf / ra), (1.0 / sn));
            alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

            double theta = 0.0;
            if (Math.abs(xn) <= 0.0) {
                theta = 0.0;
            } else {
                if (Math.abs(yn) <= 0.0) {
                    theta = Math.PI * 0.5;
                    if (xn < 0.0) {
                        theta = -theta;
                    }
                } else theta = Math.atan2(xn, yn);
            }
            double alon = theta / sn + olon;
            rs.lat = alat * RADDEG;
            rs.lng = alon * RADDEG;
        }
        te.setText(""+rs.x+ "      "+rs.y); //rs.x, rs.y가 위에 찍힌 좌표값 위경도로 변환

        return rs;

    }

    public void reverseCoding(){ //위도 경도로 주소로 변환
        List<Address> list = null;
        try{
            list = geocoder.getFromLocation(gps.lat, gps.lon,10);
        }catch (IOException e){
            e.printStackTrace();
            Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        if(list != null){
            if(list.size()==0){
                te2.setText("해당되는 주소 정보는 없습니다.");
            }else{
                String cut[] = list.get(0).toString().split(" ");
                for (int i =0; i<cut.length; i++){
                    System.out.println("cut["+i+"]:" + cut[i]);
                }
                // te2.setText(cut[1]+""+cut[2]+""+cut[3]+""+cut[4]); //cut[1]경기도 [2]성남시 [3]중원구 [4]금광2동
                mylocation.setText(cut[3]);
            }
        }
    }

    class LatXLngY
    {
        public double lat;
        public double lng;

        public double x;
        public double y;



    }


}
