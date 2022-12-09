package com.example.lab12;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.main_list);
        listView.setOnItemClickListener(this);

        // 항목 구성 데이터 획득
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select location from tb_data where category='0'", null);

        ArrayList<String> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            data.add(cursor.getString(0));
        }
        db.close();

        // Adapter 적용으로 ListView 구성
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        handleActivityResult(result);
                    }
                    });
                }


    private void handleActivityResult(ActivityResult result) {
        if (result.getResultCode() != RESULT_OK) return;

        Intent data = result.getData();
        // DetailActivity가 넘긴 데이터 획득
        String category = data.getStringExtra("category");
        String location = data.getStringExtra("location");
        // 결괏값 Toast로 출력
        Toast toast = Toast.makeText(this, category + ": " + location, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        String category = textView.getText().toString();
        // Extra 데이터를 넘기면서 Intent 발생
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("category", category);
        launcher.launch(intent);
    }
}