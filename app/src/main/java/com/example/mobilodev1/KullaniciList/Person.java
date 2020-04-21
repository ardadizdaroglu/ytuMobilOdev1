package com.example.mobilodev1.KullaniciList;
//by Arda Dizdaroglu 19574016
public class Person {

    private String username;
    private String pss;
    private int photo_id;

    public String getUsername()
    {
        return this.username;
    }
    public String getPss()
    {
        return this.pss;
    }
    public int getPhoto_id()
    {
        return this.photo_id;
    }

    public Person(String username,String pss,int photo_id)
    {
        this.username = username;
        this.pss = pss;
        this.photo_id = photo_id;
    }
    public Person()
    {

    }


}
