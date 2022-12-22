package com.example.whatmedicineis;

public class MapPoint {
    private String Name; //대피소 이름
    private double  latitude; //위도
    private  double longitude; //경도
    private String Phone; // 전화번호

    public MapPoint() {
        super();
    }

    public  MapPoint(String Name, double latitude, double longitude, String Phone) {
        this.Name = Name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.Phone = Phone;
    }

    public String getName() { //대피소 이름
        return Name;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public double getLatitude() { //위도
        return latitude;
    }

    public  void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLongitude() { //경도
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhone() { return Phone; }

    public void setPhone(String Phone) { this.Phone = Phone; }
}