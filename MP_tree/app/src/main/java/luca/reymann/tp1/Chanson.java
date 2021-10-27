package luca.reymann.tp1;

import android.graphics.Bitmap;

public class Chanson {

    private Bitmap pochette;
    private int id, duree;
    private String artiste, nom, album;

    public Bitmap getPochette() {
        return pochette;
    }

    public void setPochette(Bitmap pochette) {
        this.pochette = pochette;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Chanson(int id, String artiste, String nom, String album, int duree, Bitmap pochette) {
        this.id = id;
        this.artiste = artiste;
        this.nom = nom;
        this.album = album;
        this.duree = duree;
        this.pochette = pochette;
    }
}
