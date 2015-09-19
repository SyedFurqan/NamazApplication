package com.syedfurqan.namazapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;


public class MainActivity extends Activity {

    private SpeechRecognizer sr;
    Vibrator v;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void startListening(View view) {
        if(sr!=null){
            Log.d("22222222", "Destroy");
            sr.destroy();
        }
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new listener());
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ar-eg");
        intent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{});
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "ar-eg");
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "ar-eg");
        sr.startListening(intent);
    }

    class listener implements RecognitionListener
    {
        public void onReadyForSpeech(Bundle params)
        {

        }
        public void onBeginningOfSpeech()
        {
            Log.d("Message........","Listening");
        }
        public void onRmsChanged(float rmsdB)
        {

        }
        public void onBufferReceived(byte[] buffer)
        {

        }
        public void onEndOfSpeech()
        {
            Log.d("Message........", "Stopped");
        }
        public void onError(int error)
        {
            Log.d("Message........", "Error" + error);
        }
        public void onResults(Bundle results)
        {
            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            for (int i = 0; i < data.size(); i++)
            {
                Log.d("Current Result........",data.get(i).toString());
            }
            if(data.size()>0){
                result=data.get(0).toString();

                check(result);
            }
        }
        public void onPartialResults(Bundle partialResults)
        {

        }
        public void onEvent(int eventType, Bundle params)
        {

        }
    }

    private void check(String result) {
        ArrayList array_list1=(new ArabicWordsArraylists()).getArrayList1();
        for (int i=0;i<array_list1.size();i++){
            if (array_list1.contains(result)){
                Log.d("Message......","Result Matched");
                break;
            }
        }
    }
}
