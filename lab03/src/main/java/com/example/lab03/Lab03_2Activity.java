package com.example.lab03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Lab03_2Activity extends AppCompatActivity implements View.OnClickListener{

    Button alertBtn;
    Button listBtn;
    Button dateBtn;
    Button timeBtn;
    Button customDialogBtn;

    // 이벤트 처리를 위해 dialog 객체를 멤버변수로 선언
    AlertDialog customDialog; // android.app.AlertDialog
    AlertDialog listDialog; // android.app.AlertDialog
    AlertDialog alertDialog; // android.app.AlertDialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab03_2);
        // View 객체 획득
        alertBtn = findViewById(R.id.btn_alert);
        listBtn = findViewById(R.id.btn_list);
        dateBtn = findViewById(R.id.btn_date);
        timeBtn = findViewById(R.id.btn_time);
        customDialogBtn = findViewById(R.id.btn_custom);

        // Button 이벤트 등록
        alertBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        customDialogBtn.setOnClickListener(this);
    }
    // 매개변수의 문자열을 Toast로 띄우는 개발자 함수
    private void showToast(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT); toast.show();
    }
    // Dialog Button 이벤트 처리
    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(dialog==customDialog && which == DialogInterface.BUTTON_POSITIVE){
                showToast("custom dialog 확인 click...");
            }else if(dialog==listDialog){
                String[] data = getResources().getStringArray(R.array.dialog_array);
                showToast(data[which] + " 선택하셨습니다.");
            } else if(dialog == alertDialog && which == DialogInterface.BUTTON_POSITIVE){
                showToast("alert dialog ok click...");
            }
        }
    };

    @Override
    public void onClick(View view){
        if (view == alertBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("알림");
            builder.setMessage("정말 종료 하시겠습니까?");
            builder.setPositiveButton("OK", dialogListener);
            builder.setNegativeButton("NO", null);
            alertDialog = builder.create();
            alertDialog.show();
        }
        else if (view == listBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("알람 벨소리");
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);
            builder.setPositiveButton("확인", null);
            builder.setNegativeButton("취소", null);
            listDialog = builder.create();
            listDialog.show();
        }
        else if (view == dateBtn) {
            // 현재 날짜로 dialog를 띄우기 위해 날짜를 구함
            Calendar c = Calendar.getInstance(); // java.util.Calendar
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    showToast(year + "-" + (month-1) + "-" + dayOfMonth);
                }
            }, year,month,day);
            dateDialog.show();
        }
        else if (view == timeBtn) {
            // 현재 시간으로 Dialog를 띄우기 위해 시간을 구함
            Calendar c = Calendar.getInstance(); // java.util.Calendar
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    showToast(hourOfDay + ":" + minute);
                }
            },hour,minute,false);
            timeDialog.show();
        }
        else if (view == customDialogBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // custom dialog를 위한 layout xml 초기화
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View customDialogView = inflater.inflate(R.layout.dialog_layout, null);
            builder.setView(customDialogView);

            builder.setPositiveButton("확인", dialogListener);
            builder.setNegativeButton("취소", null);

            customDialog = builder.create();
            customDialog.show();
        }
    }
}