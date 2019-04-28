package com.rkv.voiceassistantforvisuallyimpaired;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.Locale;
public class MainActivity extends AppCompatActivity
{   TextToSpeech t1,t3;
    int clickCounter1=0,clickCounter2=0;
    Intent intent,intent1;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status)
            {
                if (status != TextToSpeech.ERROR)
                {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        t3=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status)
            {
                if (status != TextToSpeech.ERROR)
                {
                    t3.setLanguage(Locale.UK);
                }
            }
        });
    }

    public void onMessage(View view)
    {
        clickCounter1 = clickCounter1 + 1;
        if (clickCounter1%2!=0)
        {
            t3.speak("You have pressed Message press again to confirm", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
        }
        if (clickCounter1%2==0)
        {
            intent = new Intent(getApplicationContext(), MessageActivity.class);
            intent.putExtra("","");
            startActivity(intent);
        }
    }
    public void onBattery(View view)
    {
        BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        Log.i("Battery", String.valueOf(batLevel));
        t1.speak("Your battery percentage is"+String.valueOf(batLevel)+" percent", TextToSpeech.QUEUE_FLUSH,null,TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void onPhone(View view)
    {   clickCounter2 = clickCounter2 + 1;
        if(clickCounter2%2!=0)
        {
            t3.speak("You have pressed Phone press again to confirm", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
        }
        if(clickCounter2%2==0)
        {
            intent1=new Intent(getApplicationContext(), PhoneActivity.class);
            intent1.putExtra("","");
            startActivity(intent1);
        }
    }
   public void onTime(View view)
   {
       Date currentTime = Calendar.getInstance().getTime();
       Calendar calendar=Calendar.getInstance();
       SimpleDateFormat format=new SimpleDateFormat("HH:mm");
       t3.speak("Date is "+currentTime+" and current time is"+format.format(calendar.getTime()), TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);

   }
}
