package com.example.whatmedicineis;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

public class P_Api {
    private static String ServiceKey = "OXdYmp2R87KM6WBJDET4ITb2bqp5BmYkfEftSnKiAZJWZh%2BbTg45Pov36PQwMjqpTVm%2FdsALOELrjIVFb7%2B3hw%3D%3D";
    public P_Api(){
        try{
            apiParserSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MapPoint> apiParserSearch() throws Exception{
        URL url = new URL(getURLParam(null));

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        xpp.setInput(bis, "utf-8");

        String tag = null;
        int event_type = xpp.getEventType();

        ArrayList<MapPoint> mapPoint = new ArrayList<MapPoint>();
        String pharmarcy = null, longitude = null, latitude = null, Name = null, Phone = null;

        boolean bpharmarcy = false, blatitude = false, blongitude = false, bName = false, bPhone = false;

        while(event_type != XmlPullParser.END_DOCUMENT){
            if(event_type == XmlPullParser.START_TAG){
                tag = xpp.getName();
                if(tag.equals("yadmNm")){
                    bpharmarcy = true;
                }
                if(tag.equals("wgs84Lat")){
                    blatitude = true;
                }
                if(tag.equals("wgs84Lon")){
                    blongitude = true;
                }
                if(tag.equals("dutyName")){
                    bName = true;
                }
                if(tag.equals("dutyTel1")){
                    bPhone = true;
                }
            } else if (event_type == XmlPullParser.TEXT){
                if(bpharmarcy == true){
                    pharmarcy = xpp.getText();
                    bpharmarcy = false;
                }
                else if(blatitude == true){
                    latitude = xpp.getText();
                    blatitude = false;
                }
                else if(blongitude == true){
                    longitude = xpp.getText();
                    blongitude = false;
                }
                else if(bName == true){
                    Name = xpp.getText();
                    bName = false;
                }
                else if(bPhone == true){
                    Phone = xpp.getText();
                    bPhone = false;
                }
            } else if (event_type == XmlPullParser.END_TAG){
                tag = xpp.getName();
                if(tag.equals("item")) {
                    MapPoint entity = new MapPoint();
                    entity.setName(Name);
                    entity.setLatitude(Double.valueOf(latitude));
                    entity.setLongitude(Double.valueOf(longitude));
                    entity.setPhone(Phone);
                    mapPoint.add(entity);
                    System.out.println(mapPoint.size());
                }
            }
            event_type = xpp.next();
        }
        System.out.println(mapPoint.size());
        return mapPoint;
    }
    private String getURLParam(String search){
        String url = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire?ServiceKey=" + ServiceKey +
                "&numOfRows=100";   // 전국 100개 약국 점포 위치 마커를 불러옴
        return url;
    }
    // 잘 파싱되었는지 확인
    public static void main(String[] args){
        new P_Api();
    }
}