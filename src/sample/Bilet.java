package sample;

import java.sql.Time;
import java.util.Date;
import java.sql.Connection;
public class Bilet {
    int id;
    String adSoyad;
    String Film,Seans;
    int yetki;
    public Bilet(int id, String adSoyad, String film, String seans) {
        this.id = id;
        this.adSoyad = adSoyad;
        this.Film = film;
        this.Seans = seans;

    }
    Bilet(){
        this.yetki=0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getFilm() {
        return Film;
    }

    public void setFilm(String film) {
        Film = film;
    }

    public String getSeans() {
        return Seans;
    }

    public void setSeans(String seans) {
        Seans = seans;
    }

    public int getYetki() {
        return yetki;
    }

    public void setYetki(int yetki) {
        this.yetki = yetki;
    }




}
