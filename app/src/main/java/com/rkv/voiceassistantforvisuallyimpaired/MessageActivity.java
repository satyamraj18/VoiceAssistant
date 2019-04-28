package com.rkv.voiceassistantforvisuallyimpaired;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.PatternsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Handler;
import java.util.regex.Pattern;

public class MessageActivity extends AppCompatActivity
{   ListView lv;
    TextToSpeech t2,t3;
    ArrayList<String> sms;
    String address,body,date;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent=getIntent();
        String a=intent.getStringExtra("");
        t3 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR)
                {
                    t2.setLanguage(Locale.UK);
                }
            }
        });
        t2 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR)
                {
                    t2.setLanguage(Locale.UK);
                }
            }
        });

        if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED)
        {   //t2.speak("You inside the inbox press any message to read", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
            t3.speak("You inside the inbox press any message to read", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
            ListView lv= (ListView) findViewById(R.id.lv);
            if(fetchInbox()!=null)
            {
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, fetchInbox());
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        String delimiter="~";
                        Pattern pattern = Pattern.compile(delimiter, Pattern.CASE_INSENSITIVE);
                        String[] result = pattern.split(sms.get(position));
                        String s1=result[0];
                        String s2=result[1];
                        t2.speak(s1+s2, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
                    }
                });
            }
        }
        else
        {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(MessageActivity.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        }
    }
      public ArrayList<String> fetchInbox()
      {
        sms=new ArrayList<String>();
        Uri uri=Uri.parse("content://sms/inbox");
        Cursor cursor= getContentResolver().query(uri, new String[]{"_id","address", "date", "body"}, null, null, null);
        cursor.moveToFirst();
        while(cursor.moveToNext())
        {
             address=cursor.getString(1);
             body=cursor.getString(3);
             date=cursor.getString(2);
            sms.add("Message is sent by:"+address+"\n~Message is:"+body+"\n~Date:"+date);
        }
          cursor.close();
         return sms;
      }
}

