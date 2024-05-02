package com.example.async;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public TextView text1;
    public Button StartTask;
    private static final String Text_State = "current text";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 =  findViewById(R.id.textview1);
        StartTask = findViewById(R.id.start);

        if(savedInstanceState != null){
            text1.setText(savedInstanceState.getString(Text_State));
        }
        StartTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("click", "onClick: ");
                text1.setText("napping");
                new SimpleAsyncTask(text1).execute();
            }
        });




    }



    public class SimpleAsyncTask extends AsyncTask <Void, Void, String>{

        private WeakReference<TextView > mtextview;

        @Override
        protected String doInBackground(Void... voids) {
          Random r =  new Random();
          int n = r.nextInt(11);
          int s = n * 200;


        try {
            Thread.sleep(s);
        }
       catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Awake at last after sleeping for " + s + " milliseconds";

        }

        SimpleAsyncTask(TextView tv){
            mtextview = new WeakReference<>(tv);
        }

        @Override
        protected void onPostExecute(String s) {
            mtextview.get().setText(s);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putString(Text_State, text1.getText().toString());


    }


}