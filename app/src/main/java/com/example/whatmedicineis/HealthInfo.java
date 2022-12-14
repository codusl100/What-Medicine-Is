package com.example.whatmedicineis;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HealthInfo extends AppCompatActivity {

    ListView list;
    String[] titles = {
            "건강하게 살 빼는 법",
            "비염에 좋은 약",
            "환절기 건강 관리법!                         ",
            "관절염 예방에 좋은 운동",
            "고혈압/당뇨 건강 관리",
            "하루 4분 명상",
            "눈건강에 도움되는 눈 운동"
    };
    Integer[] images = {
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3,
            R.drawable.photo4,
            R.drawable.photo5,
            R.drawable.photo6,
            R.drawable.photo7
    };

    String[] topics = {
            "다이어트",
            "비염",
            "환절기",
            "관절염",
            "당뇨",
            "정신",
            "눈"
    };

    String[] dates = {
            "2022-12-01",
            "2022-11-30",
            "2022-11-30",
            "2022-11-28",
            "2022-11-27",
            "2022-11-25",
            "2022-11-22",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_health);

        CustomList adapter = new CustomList(HealthInfo.this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), titles[+position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context) {
            super(context, R.layout.listitem, titles);
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);

            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView topic = (TextView) rowView.findViewById(R.id.topic);
            TextView date = (TextView) rowView.findViewById(R.id.date);

            title.setText(titles[position]);
            imageView.setImageResource(images[position]);
            topic.setText(topics[position]);
            date.setText(dates[position]);

            return rowView;
        }
    }
}
