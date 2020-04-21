package com.example.mobilodev1.KullaniciAyarlari;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mobilodev1.Menu.MenuActivity;
import com.example.mobilodev1.R;

import java.util.ArrayList;
import java.util.List;
//by Arda Dizdaroglu 19574016
public class InfosActivity  extends AppCompatActivity {

    private EditText name, age, weight,height;
    private Spinner spinnerGender;
    SharedPreferences LastSelectGender;
    SharedPreferences.Editor myEditorGender;
    private Spinner spinnerAppMode;
    SharedPreferences LastSelectAppMode;
    SharedPreferences.Editor myEditorAppMode;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        name = findViewById(R.id.edit1);
        age = findViewById(R.id.edit2);
        weight = findViewById(R.id.edit4);
        height = findViewById(R.id.edit5);

        LastSelectGender = getSharedPreferences("LastSetting", Context.MODE_PRIVATE);
        myEditorGender = LastSelectGender.edit();
        final int LastClickSimpleSpinner = LastSelectGender.getInt("LastClickSimpleSpinner",0);
        spinnerGender = findViewById(R.id.spinner1);

        final List<String> myList = new ArrayList<>();
        myList.add("Erkek");
        myList.add("Kiz");

        ArrayAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myList);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(myAdapter);
        spinnerGender.setSelection(LastClickSimpleSpinner);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                myEditorGender.putInt("LastClickSimpleSpinner",position).apply();
                //Toast.makeText(MainActivity.this,myList.get(position),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

        LastSelectAppMode = getSharedPreferences("LastSettingAppMode", Context.MODE_PRIVATE);
        myEditorAppMode = LastSelectAppMode.edit();
        final int LastClickSimpleSpinnerAppMode = LastSelectAppMode.getInt("LastClickSimpleSpinnerAppMode",0);
        spinnerAppMode = findViewById(R.id.spinner2);

        final List<String> myListAppMode = new ArrayList<>();
        myListAppMode.add("Dark");
        myListAppMode.add("Light");

        ArrayAdapter myAdapterAppMode = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myListAppMode);
        myAdapterAppMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAppMode.setAdapter(myAdapterAppMode);
        spinnerAppMode.setSelection(LastClickSimpleSpinnerAppMode);
        spinnerAppMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                myEditorAppMode.putInt("LastClickSimpleSpinnerAppMode",position).apply();
                //Toast.makeText(MainActivity.this,myList.get(position),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });

    }

    // Depolanan verileri onResume () öğesine getirmeliyiz. Çünkü uygulama tekrar açıldığında çağrılacak olan budur.
    @Override
    protected void onResume()
    {
        super.onResume();

        // Saklanan verileri SharedPreference'den getirme
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

        String s1 = sh.getString("name", "");
        String s2 = sh.getString("age", "");
        String s3 = sh.getString("weight", "");
        String s4 = sh.getString("height", "");

        // Alınan verileri EditTexts'te ayarlama
        name.setText(s1);
        age.setText(s2);
        weight.setText(s3);
        height.setText(s4);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        // Özel modda "MySharedPref" dosya adıyla paylaşılan bir pref nesnesi oluşturma
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("name", name.getText().toString());
        myEdit.putString("age", age.getText().toString());
        myEdit.putString("weight", weight.getText().toString());
        myEdit.putString("height", height.getText().toString());
        myEdit.commit();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InfosActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
