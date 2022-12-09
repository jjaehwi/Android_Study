package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // 자신을 실행시킨 Intent 획득
        Intent intent = getIntent();
        // MainActivity에서 넘어온 데이터 획득
        String category = intent.getStringExtra("category");

        ListView listView = findViewById(R.id.detail_list);
        listView.setOnItemClickListener(this);

        // 항목 구성
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select location from tb_data where category=?", new String[]{category});
        ArrayList<String> data = new ArrayList<>();
        while (cursor.moveToNext()){
            data.add(cursor.getString(0));
        }
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        // 자신을 실행시킨 Intent에 결과 데이터 추가
        Intent intent = getIntent();
        intent.putExtra("location", textView.getText().toString());
        // 업무 수행 결과 추가
        setResult(RESULT_OK, intent);
        // 자신을 종료시키면서 자동으로 이전 화면으로 넘어가게 설정
        finish();
    }
}