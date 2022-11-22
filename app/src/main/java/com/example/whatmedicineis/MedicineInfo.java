package com.example.whatmedicineis;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;

public class MedicineInfo extends AppCompatActivity {
    final static String TAG = "Medicine";


    EditText edit;
    TextView Medicine_text;
    String Medicine_data;
    String serviceKey = "service Key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_info);

        Medicine_text = (TextView) findViewById(R.id.medicineinfo);
        edit = (EditText) findViewById(R.id.edit);

    }

    //Button을 클릭했을 때 자동으로 호출되는 callback method
    public void mOnClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        Medicine_data = getMedicineXmlData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                Medicine_text.setText(Medicine_data);
                            }
                        });

                    }
                }).start();

                break;
        }

    }

    // xml parsing part
    String getMedicineXmlData() {
        StringBuffer buffer = new StringBuffer();

        String str= edit.getText().toString();
        String itemName = URLEncoder.encode(str);


        String queryUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?itemName=" + itemName + "&ServiceKey=" + serviceKey;
        ;

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
                        break;

                    case XmlPullParser.START_TAG:
                        tag = xpp.getName();

                        if (tag.equals("itemImage")) {
                            buffer.append("약 사진 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n\n");
                        } else if (tag.equals("itemName")) {
                            buffer.append("약 이름 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n\n");
                        } else if (tag.equals("entpName")) {
                            buffer.append("약 제조사 : ");
                            xpp.next();
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
            // TODO Auto-generated catch blocke.printStackTrace();
        }
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }
}

