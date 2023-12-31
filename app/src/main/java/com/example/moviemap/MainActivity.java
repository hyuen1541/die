package com.example.moviemap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityItemAdapter.onItemListener {

    private MainActivityItemAdapter adapter;
    private List<MainActivityItemModel> itemList;
    private LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.mainLayout);

        setUpRecyclerView();

        Button homeButton = findViewById(R.id.homeButton);
        Button searchButton = findViewById(R.id.searchButton);
        Button bookmarkButton = findViewById(R.id.bookmarkButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                Toast.makeText(MainActivity.this, "홈 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                Toast.makeText(MainActivity.this, "검색 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });

        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(MainActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        itemList = new ArrayList<>();
        fillData();
        adapter = new MainActivityItemAdapter(itemList, this);
        adapter.setOnItemListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void fillData() {
        itemList = new ArrayList<>();
        itemList.add(new MainActivityItemModel(R.drawable.movie1, "호텔 델루나", "부여 성흥산성 충남 부여군 임천면 군사리 산7-10"));
        itemList.add(new MainActivityItemModel(R.drawable.movie2, "미스터 션샤인", "논산 션샤인 스튜디오 충청남도 논산시 연무읍 황화정리 859-55 KR"));
        itemList.add(new MainActivityItemModel(R.drawable.movie3, "7번방의 선물", "전라북도 익산시 성당면 함낭로 207"));
        itemList.add(new MainActivityItemModel(R.drawable.menlove, "남자가 사랑할때", "군산 경암동 철길마을 전라북도 군산시 경촌4길 14" ));
        itemList.add(new MainActivityItemModel(R.drawable.buenho, "변호인", "청사포 철길 부산 해운대구 중동 청사포로 58번길"));
        itemList.add(new MainActivityItemModel(R.drawable.fores, "리틀 포레스트", "경북 군위군 우보면 미성리 1007-1"));
        itemList.add(new MainActivityItemModel(R.drawable.dongvac, "동백꽃 필 무렵", "경북 포항시 남구 구룡포읍 구룡포리 249-36"));
        itemList.add(new MainActivityItemModel(R.drawable.chlac, "최악의 하루", "서울특별시 종로구 돈화문로11다길 46-1"));
        itemList.add(new MainActivityItemModel(R.drawable.namza, "남자친구", "강원도 속초시 대포동"));
    }

    @Override
    public void onItemClicked(int position) {
        MainActivityItemModel clickedItem = itemList.get(position);
        Intent intent = new Intent(MainActivity.this, MovieDetail.class);
        intent.putExtra("imageResource", clickedItem.getImageResource());
        intent.putExtra("text1", clickedItem.getText1());
        intent.putExtra("text2", clickedItem.getText2());
        startActivity(intent);
    }
}
