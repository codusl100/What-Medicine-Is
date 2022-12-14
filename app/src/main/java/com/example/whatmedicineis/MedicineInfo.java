package com.example.whatmedicineis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;

public class MedicineInfo extends AppCompatActivity {
    final static String TAG = "Medicine";

    private String Medicine_data;
    EditText edit;
    TextView result;
    XmlPullParser xpp;

    String serviceKey = "OXdYmp2R87KM6WBJDET4ITb2bqp5BmYkfEftSnKiAZJWZh%2BbTg45Pov36PQwMjqpTVm%2FdsALOELrjIVFb7%2B3hw%3D%3D";
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_info);

        // 약 이름 입력
        edit = findViewById(R.id.edit);
        // 결과값
        result = (TextView) findViewById(R.id.result);
    }
    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.searchbtn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            data=getMedicineXmlData();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                result.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    }
    // xml parsing part
    String getMedicineXmlData() throws UnsupportedEncodingException {
        StringBuffer buffer = new StringBuffer();

        String str= edit.getText().toString(); // editText에 작성된 text 얻어오기
        String name = URLEncoder.encode(str, "UTF-8");
        String query="%EC%A0%84%EB%A0%A5%EB%A1%9C";

        String queryUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?ServiceKey=" + serviceKey + "&itemName=" + name;

        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8"));

            String tag;
            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if (tag.equals("item")) ;
                        else if (tag.equals("entpName")) {
                            buffer.append("약 제조사 : ");
                            xpp.next();
                            buffer.append("\n\n");
                        }
                        else if (tag.equals("useMethodQesitm")) {
                            buffer.append("사용 방법 : ");
                            xpp.next();
                            buffer.append("\n\n");
                        }
                        else if (tag.equals("itemName")) {
                            buffer.append("약 이름 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n\n");
                        } else if (tag.equals("atpnWarnQesitm")) {
                            buffer.append("주의사항 경고 : ");
                            xpp.next();
                            buffer.append("\n\n");
                        } else if (tag.equals("atpnQesitm")) {
                            buffer.append("주의사항 : ");
                            xpp.next();
                            buffer.append("\n\n");
                        } else if (tag.equals("intrcQesitm")) {
                            buffer.append("상호작용 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n\n");
                        } else if (tag.equals("seQesitm")) {
                            buffer.append("부작용 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n\n");
                        } else if (tag.equals("depositMethodQesitm")) {
                            buffer.append("보관법 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n\n");
                        }
                        else if (tag.equals("itemImage")) {
                            buffer.append("약 사진 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n\n");
                            buffer.append("-----------------------------\n\n");
                        }
                        break;
                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName();
                        if (tag.equals("item")) buffer.append("\n");
                        break;
                }
                eventType = xpp.next();


            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO Auto-generated catch blocke.printStackTrace();
        }

        buffer.append("파싱 끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }
}

