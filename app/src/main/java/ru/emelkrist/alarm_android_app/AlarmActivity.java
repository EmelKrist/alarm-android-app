package ru.emelkrist.alarm_android_app;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {
    private Vibrator vibrator;
    private Ringtone ringtone;
    private final long[] vibratorPattern = {500, 300, 400, 200};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        setRingtone();
        setVibrator();
        startAlarm();
    }

    private void setRingtone() {
        Uri notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this, notificationUri);
        if (ringtone == null) {
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            ringtone = RingtoneManager.getRingtone(this, notificationUri);
        }
    }

    private void setVibrator() {
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    private void startAlarm() {
        ringtone.play();

        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(vibratorPattern, 0);
        }
    }

    public void onClickStopAlarmButton(View view) {
        if (ringtone != null && ringtone.isPlaying())
            ringtone.stop();

        if (vibrator != null)
            vibrator.cancel();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onDestroy();
    }

}
