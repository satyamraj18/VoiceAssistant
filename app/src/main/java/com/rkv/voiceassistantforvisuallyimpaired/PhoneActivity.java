package com.rkv.voiceassistantforvisuallyimpaired;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Locale;
public class PhoneActivity extends AppCompatActivity
{
    private static final int MY_PERMISSION_REQUEST=1;
    Button b_0,b_1,b_2,b_3,b_4,b_5,b_6,b_7,b_8,b_9,b_clear,b_call;
    String number="";
    TextToSpeech t4;
    Intent intent2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        Intent intent1=getIntent();
        String a=intent1.getStringExtra("");
        b_0=(Button)findViewById(R.id.b_0);
        b_1=(Button)findViewById(R.id.b_1);
        b_2=(Button)findViewById(R.id.b_2);
        b_3=(Button)findViewById(R.id.b_3);
        b_4=(Button)findViewById(R.id.b_4);
        b_5=(Button)findViewById(R.id.b_5);
        b_6=(Button)findViewById(R.id.b_6);
        b_7=(Button)findViewById(R.id.b_7);
        b_8=(Button)findViewById(R.id.b_8);
        b_9=(Button)findViewById(R.id.b_9);
        b_clear=(Button)findViewById(R.id.b_clear);
        b_call=(Button)findViewById(R.id.b_call);
        t4=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status)
            {
                if (status != TextToSpeech.ERROR)
                {
                    t4.setLanguage(Locale.UK);
                }
            }
        });
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_REQUEST);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_REQUEST);
            }
        }
        t4.speak("Press the numbers to call", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int [] grantResults)
    {
        switch (requestCode)
        {
            case MY_PERMISSION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(PhoneActivity.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(PhoneActivity.this, "No Permission Granted!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

        }
    }
    public void b1(View view)
    {
        number=number+"1";
        t4.speak("1", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void b2(View view)
    {
        number=number+"2";
        t4.speak("2", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void b3(View view)
    {
        number=number+"3";
        t4.speak("3", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void b4(View view)
    {
        number=number+"4";
        t4.speak("4", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void b5(View view)
    {
        number=number+"5";
        t4.speak("5", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void b6(View view)
    {
        number=number+"6";
        t4.speak("6", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void b7(View view)
    {
        number=number+"7";
        t4.speak("7", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void b8(View view)
    {
        number=number+"8";
        t4.speak("8", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void b9(View view)
    {
        number=number+"9";
        t4.speak("9", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void b0(View view)
    {
        number=number+"0";
        t4.speak("0", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }
    public void bclear(View view)
    {
        number="";
        t4.speak("Numbers Cleared", TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
    }

    public void bcall(View view)
    {

        intent2=new Intent(Intent.ACTION_CALL);
        intent2.setData(Uri.parse("tel:"+number));
        t4.speak("Calling"+number, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
        startActivity(intent2);

    }
}
