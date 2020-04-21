package com.example.mobilodev1.KullaniciList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mobilodev1.Menu.MenuActivity;
import com.example.mobilodev1.R;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
//by Arda Dizdaroglu 19574016
public class MemberListActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private List<Person> person_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);


        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        recycler_view.setLayoutManager(layoutManager);

        person_list = new ArrayList<Person>();

        person_list.add(new Person("Amac Guvensan", "123456", R.drawable.boy_img));
        person_list.add(new Person("Arda Dizdaroglu", "qwerty", R.drawable.computer_men));
        person_list.add(new Person("Ayse Fatma", "asdfgh", R.drawable.women_img));
        person_list.add(new Person("Ahmet Ali", "zxcvbn", R.drawable.male_img));
        person_list.add(new Person("Pelin Pelin", "123456", R.drawable.female_img));
        person_list.add(new Person("Osman Osman", "qwerty", R.drawable.gender_male));
        person_list.add(new Person("Sevgi Butun", "asdfgh", R.drawable.gender_female));
        person_list.add(new Person("Abulkadir Yavas", "zxcvbn", R.drawable.men_image));
        person_list.add(new Person("Ayse Mutlu", "123456", R.drawable.girl_worker));
        person_list.add(new Person("Ahmetcan Can", "qwerty", R.drawable.user_operator));
        person_list.add(new Person("Fatma Can", "asdfgh", R.drawable.girl_girl));
        person_list.add(new Person("Mustafa Altin", "zxcvbn", R.drawable.user_man_img));
        person_list.add(new Person("Caner Yalcin", "123456", R.drawable.worker_man));
        person_list.add(new Person("Gonul Yalcin", "qwerty", R.drawable.computer_girl));


        MemberAdapter adapter_items = new MemberAdapter(person_list, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d("position", "Tıklanan Pozisyon:" + position);
                Person person = person_list.get(position);
                Toast.makeText(getApplicationContext(),"Pozisyon:"+" "+position+" "+"Kullanici adi: "+person.getUsername(),Toast.LENGTH_SHORT).show();
            }
        });
        recycler_view.setHasFixedSize(true);

        recycler_view.setAdapter(adapter_items);

        recycler_view.setItemAnimator(new DefaultItemAnimator());


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MemberListActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
