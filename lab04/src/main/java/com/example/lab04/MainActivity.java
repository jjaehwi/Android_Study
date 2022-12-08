package com.example.lab04;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04.R;

public class MainActivity extends AppCompatActivity implements View .OnClickListener, CompoundButton.OnCheckedChangeListener {

    TextView bellTextView;
    TextView labelTextView;
    CheckBox repeatCheckView;
    CheckBox vibrateCheckView;
    Switch switchView;

    float initX;

    long initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bellTextView = findViewById(R.id.bell_name);
        labelTextView = findViewById(R.id.label);
        repeatCheckView = findViewById(R.id.repeatCheck);
        vibrateCheckView = findViewById(R.id.vibrate);
        switchView = findViewById(R.id.onOff);

        bellTextView.setOnClickListener(this);
        labelTextView.setOnClickListener(this);

        repeatCheckView.setOnCheckedChangeListener(this);
        vibrateCheckView.setOnCheckedChangeListener(this);
        switchView.setOnCheckedChangeListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initX = event.getRawX();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float diffX = initX - event.getRawX(); if (diffX > 30) {
                showToast("왼쪽으로 화면을 밀었습니다"); } else if (diffX < -30) {
                showToast("오른쪽으로 화면을 밀었습니다."); }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - initTime > 3000) {
                showToast("종료할려면 한번 더 누르세요.");
                initTime = System.currentTimeMillis();
        } else {
            finish();
        }
        return true;
    }
    return super.onKeyDown(keyCode, event);
}

    private void showToast(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View view) {
        if(view ==bellTextView){
            showToast("bell text click event...");
        } else if (view == labelTextView){
            showToast("label text click event...");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView == repeatCheckView){
            showToast("repeat checkbox is " + isChecked);
        } else if (buttonView == vibrateCheckView) {
            showToast("vibrate checkbox is " + isChecked);
        } else if (buttonView == switchView){
            showToast("switch is " + isChecked);
        }
    }
}
