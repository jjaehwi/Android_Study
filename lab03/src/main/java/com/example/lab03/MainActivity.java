package com.example.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button vibrationBtn;
    Button systemBeepBtn;
    Button customBeepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrationBtn=findViewById(R.id.btn_vibration);
        systemBeepBtn=findViewById(R.id.btn_system_beep);
        customBeepBtn=findViewById(R.id.btn_custom_sound);

        vibrationBtn.setOnClickListener(this);
        systemBeepBtn.setOnClickListener(this);
        customBeepBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == vibrationBtn){
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        }
        else if (view == systemBeepBtn) {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        }
        else if(view == customBeepBtn){
            MediaPlayer player = MediaPlayer.create(this, R.raw.fallbackring);
            player.start();
        }
    }
}