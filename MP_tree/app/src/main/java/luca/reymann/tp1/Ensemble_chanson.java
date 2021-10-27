package luca.reymann.tp1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Size;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

public class Ensemble_chanson {

    private static Ensemble_chanson instance;
    private Vector<Chanson> chansons;
    public int indexChansonJouee;
    private ContentResolver musicResolver;

    public static Ensemble_chanson getInstance(Context context) {
        if (instance == null)
            instance = new Ensemble_chanson();
        return instance;
    }

    private Ensemble_chanson() {
        chansons = new Vector<Chanson>();
    }

    private Chanson ChansonSuivante() {
        Chanson chansonSuivante;
        if (indexChansonJouee >= chansons.size()) {
            indexChansonJouee = 0;
        } else if (indexChansonJouee <= 0) {
            indexChansonJouee = chansons.size();
        }

        chansonSuivante = chansons.elementAt(indexChansonJouee);
        return chansonSuivante;
    }

    public void setToutesChansons(Activity a, String artisteParam) {
        Vector<Chanson> temp = new Vector<Chanson>();
        musicResolver = a.getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Bitmap pochette = null;
        Cursor cursor;
        if (artisteParam != null) {
            cursor = musicResolver.query(musicUri, new String[]{"_id", "artist", "title", "album", "duration"}, "artist = ?", new String[]{artisteParam}, null);
        } else {
            cursor = musicResolver.query(musicUri, new String[]{"_id", "artist", "title", "album", "duration"}, null, null, null);
        }

        while (cursor.moveToNext()) {
            Chanson c = new Chanson((int) cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), null);
            Uri uriChanson = ContentUris.withAppendedId(musicUri, c.getId());
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) // SDK_INT version de l'Ã©mulateur / du tÃ©lÃ©phone , nous 29
                    pochette = musicResolver.loadThumbnail(uriChanson, new Size(640, 480), null);
                else
                    pochette = MediaStore.Images.Thumbnails.getThumbnail(musicResolver, c.getId(), MediaStore.Images.Thumbnails.MINI_KIND, null); // pour versions prÃ©cÃ©dentes
            } catch (IOException ioe) // si pas de thumbnail dans les infos du fichier, on utilise l'image noImage
            {
                pochette = BitmapFactory.decodeResource(a.getResources(), R.drawable.noimage);
                ioe.printStackTrace();
            }
            c.setPochette(pochette);
            temp.add(c);
        }
        chansons = temp;
    }

    public Vector<String> getToutArtistes() {
        Vector<String> listArtistes = new Vector<String>();
        for (int i = 0; i < chansons.size(); i++) {
            if (!listArtistes.contains(chansons.elementAt(i).getArtiste())) {
                listArtistes.add(chansons.elementAt(i).getArtiste());
            }
        }
        return listArtistes;
    }

    public Vector<Hashtable<String, ?>> getToutChanson() {
        Vector<Hashtable<String, ?>> listChanson = new Vector();
        for (int i = 0; i < chansons.size(); i++) {
            Hashtable<String, Object> temp = new Hashtable<>();
            temp.put("pochette", chansons.elementAt(i).getPochette());
            temp.put("nom", chansons.elementAt(i).getNom());
            temp.put("artiste", chansons.elementAt(i).getArtiste());
            listChanson.add(temp);
        }
        return listChanson;
    }

    public Chanson getChanson() {
        return chansons.elementAt(indexChansonJouee);
    }
}
