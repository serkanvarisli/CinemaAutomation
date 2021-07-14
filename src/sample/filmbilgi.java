package sample;

public class filmbilgi extends Bilet {
    int id;
    String film,bilgi,konu,oyuncular;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOyuncular() {
        return oyuncular;
    }

    public void setOyuncular(String oyuncular) {
        this.oyuncular = oyuncular;
    }

    int yetki;

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

    public String getBilgi() {
        return bilgi;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }

    public String getKonu() {
        return konu;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public int getYetki() {
        return yetki;
    }

    public void setYetki(int yetki) {
        this.yetki = yetki;
    }

    filmbilgi(){
        this.yetki=0;
    }
    public filmbilgi(int id, String film, String bilgi, String konu, String oyuncular) {
        this.id = id;
        this.film = film;
        this.bilgi = bilgi;
        this.konu = konu;
        this.oyuncular=oyuncular;

    }
}