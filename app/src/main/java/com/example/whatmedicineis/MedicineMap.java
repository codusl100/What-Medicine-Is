package com.example.whatmedicineis;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MedicineMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // 구글맵에서 검색창 editText 와 검색된 위치 불러올 textView 초기화
        searchBox = findViewById(R.id.sBox);

        // 구글맵 검색 하는 부분
        Button searchButton = findViewById(R.id.button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 검색창에서 텍스트를 가져온다
                String searchText = searchBox.getText().toString();

                Geocoder geocoder = new Geocoder(getBaseContext());
                List<Address> addresses = null;

                try {
                    addresses = geocoder.getFromLocationName(searchText, 3);
                    if (addresses != null && !addresses.equals(" ")) {
                        search(addresses);
                    }
                } catch(Exception e) {

                }

            }
        });

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        // 마커 초기 위치 설정
        P_Api parser = new P_Api();
        ArrayList<MapPoint> mapPoint = new ArrayList<MapPoint>();
        try {
            mapPoint = parser.apiParserSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMap = googleMap;

        // 초기 중심 - 서울
        LatLng SEOUL = new LatLng(37.56, 126.97);

        for (MapPoint entity : mapPoint) {
            MarkerOptions options = new MarkerOptions();
            options.position(new LatLng(entity.getLatitude(), entity.getLongitude()));
            options.title(entity.getName());
            options.snippet(entity.getPhone());
            mMap.addMarker(options);
        }


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));

    }
    // 구글맵 주소 검색 메서드
    protected void search(List<Address> addresses) {
        Address address = addresses.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("내 위치");

        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

}
