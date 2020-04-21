package com.example.mobilodev1.Sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilodev1.Menu.MenuActivity;
import com.example.mobilodev1.R;

import java.util.ArrayList;
import java.util.List;
//by Arda Dizdaroglu 19574016
public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> list;
    SensorManager sensorManager ;
    List<Sensor> listsensor;
    private RecyclerAdapter adapter;
    Sensor sensor;
    TextView textView;
    LinearLayout linearLayout;
    private Sensor accelerometer;
    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    private boolean basladi;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<String>();
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        textView = findViewById(R.id.tw1);
        linearLayout = findViewById(R.id.ll1);

        listsensor = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(int i=0; i<listsensor.size(); i++){
            list.add(listsensor.get(i).getName());
        }
        adapter = new RecyclerAdapter(list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        basladi = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            if(event.values[0] < 10.0){
                recyclerView.setBackgroundColor(Color.BLACK);
                adapter.notifyDataSetChanged();
                adapter.setChanged(false);
                textView.setBackgroundColor(Color.BLACK);
                textView.setTextColor(Color.WHITE);
                linearLayout.setBackgroundColor(Color.BLACK);
            }
            else{
                recyclerView.setBackgroundColor(Color.WHITE);
                adapter.notifyDataSetChanged();
                adapter.setChanged(true);
                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
                linearLayout.setBackgroundColor(Color.WHITE);
            }
        }
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values.clone();
            // Sallanma tespiti
            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float)Math.sqrt(x*x + y*y + z*z);
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if(mAccel > 1){ //degistirilebilir
                basladi = false;
            }
            else{
                //int minute=1;
                //int min= minute*60*1000;
                int min= 5000; //ms cinsinden kac sn isteniyorsa (5000=5000ms=5sn)
                if(!basladi){
                    counter(min);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private void counter(long min) {
        basladi = true;
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(min, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
                textView.setText(String.format("%d:%d:%d", hours, minutes, seconds));
            }
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Sure doldu.",
                        Toast.LENGTH_LONG).show();
                basladi = false;
                finishAffinity();
                System.exit(0);
            }
        };
        timer.start();
    }
    @Override
    public void onBackPressed() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
        Intent intent = new Intent(SensorActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
