package sample;

import java.sql.Time;
import java.util.Date;
import java.sql.Connection;

public class Filmler {
    int id;
    String film;
    String filmturu,seans1,seans1salon,seans2,seans2salon,seans3,seans3salon,tarih;
    int yetki;

    public int getYetki() {
        return yetki;
    }

    public void setYetki(int yetki) {
        this.yetki = yetki;
    }

    Filmler(){
        this.yetki=0;
    }
    public Filmler(int id, String film, String filmturu, String seans1, String seans1salon, String seans2, String seans2salon, String seans3, String seans3salon, String tarih) {
        this.id = id;
        this.film = film;
        this.filmturu = filmturu;
        this.seans1 = seans1;
        this.seans1salon = seans1salon;
        this.seans2=seans2;
        this.seans2salon = seans2salon;
        this.seans3 = seans3;
        this.seans3salon = seans3salon;
        this.tarih = tarih;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getFilmturu() {
        return filmturu;
    }

    public void setFilmturu(String filmturu) {
        this.filmturu = filmturu;
    }

    public String getSeans1() {
        return seans1;
    }

    public void setSeans1(String seans1) {
        this.seans1 = seans1;
    }

    public String getSeans1salon() {
        return seans1salon;
    }

    public void setSeans1salon(String seans1salon) {
        this.seans1salon = seans1salon;
    }

    public String getSeans2() {
        return seans2;
    }

    public void setSeans2(String seans2) {
        this.seans2 = seans2;
    }

    public String getSeans2salon() {
        return seans2salon;
    }

    public void setSeans2salon(String seans2salon) {
        this.seans2salon = seans2salon;
    }

    public String getSeans3() {
        return seans3;
    }

    public void setSeans3(String seans3) {
        this.seans3 = seans3;
    }

    public String getSeans3salon() {
        return seans3salon;
    }

    public void setSeans3salon(String seans3salon) {
        this.seans3salon = seans3salon;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
