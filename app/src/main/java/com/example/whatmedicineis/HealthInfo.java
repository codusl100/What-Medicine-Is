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
            "건강하게 살 빼는 법 9가지",
            "비염에 좋은 약",
            "환절기 건강 관리법!                         ",
            "관절염 예방에 좋은 운동",
            "당뇨 관리법"
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
            "1. 신진대사를 증가시키기 \n" +
                    "2. 포만감을 주는 음식 먼저 먹기 \n" +
                    "3. 물 많이 마시기 \n" +
                    "4. 체내 액체 제거하기 \n" +
                    "5. 건강한 지방 선택하기 \n" +
                    "6. 고품질 단백질 섭취하기 \n" +
                    "7. 탄수화물은 적게 먹기 \n" +
                    "8. 꼭꼭 씹어 먹기 \n" +
                    "9. 즐겁게 먹기 \n",
            "액티피드, 콜콜코는 심한 비염으로 코가 꽉 막힐때 먹으면 좋습니다. 슈도에페드린이라는 성분이 코 안에 부어있는 점막의 붓기를 완화시켜주어 코막힘에 도움을 줍니다. 하지만 트리프롤리딘이라는 항이스타민제가 졸음이 심하게 유발하고 약 효과가 3~5시간으로 짧은 게 단점입니다. 1일 3회 정도 복용하는 것이 효과적입니다. 약국에서 약 3,000원~3,500원 정도 합니다.",
            "1. 옷을 따뜻하게 입습니다.\n" +
                    "2. 가벼운 야외 활동을 합니다. \n" +
                    "3. 청결을 유지하고 실내 환기에 신경을 씁니다. \n" +
                    "4. 물을 자주 마십니다. \n" +
                    "5. 충분한 수면을 취하고 과로를 피합니다. \n" +
                    "6. 면역력을 높여주는 음식을 골고루 섭취합니다. \n" +
                    "7. 가벼운 운동을 규칙적으로 합니다.\n",
            "관절에 체중이 많이 가해지지 않는 운동이 도움이 됩니다. 예를 들어 유산소 운동 중에서도 걷기 보다는 물속 걷기, 수영, 실내자전거 타기, 서서 타는 자전거(이클립스 또는 싸이클런)등이 이에 해당 되겠습니다.\n",
            "1. 적정 체중을 유지하라 \n" +
                    "2. 혈당 수치를 자주 점검하라 \n" +
                    "3. 혈당 조절에 좋은 식품이 있다 \n" +
                    "4. 혈압도 잘 체크하라 \n" +
                    "5. 꾸준히 운동하라 \n" +
                    "6. 잠을 충분히 자라 \n" +
                    "7. 스트레스를 줄여라 \n" +
                    "8. 담배를 끊어라\n"
    };

    String[] dates = {
            "2022-12-01",
            "2022-11-30",
            "2022-11-30",
            "2022-11-28",
            "2022-11-27",
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
