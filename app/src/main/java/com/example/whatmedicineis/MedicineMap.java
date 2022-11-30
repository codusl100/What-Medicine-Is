package com.example.whatmedicineis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import net.daum.mf.map.api.MapView;
import androidx.appcompat.app.AppCompatActivity;

public class MedicineMap extends AppCompatActivity {

//    MapView mapView = new MapView(this);
//
//    ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
//    mapViewContainer.addView(mapView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_map);
        
        }
}
