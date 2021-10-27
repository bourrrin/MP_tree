
package luca.reymann.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;

public class MediaPlayer extends AppCompatActivity {
    TextView titre, artiste;
    LinearLayout parent_btn;
    ImageView pochette;
    Ecouteur ec = new Ecouteur();

    int extraId;
    Ensemble_chanson ensemble_chanson;

    MusicService musicSrv;
    android.media.MediaPlayer mediaPlayer;
    boolean musicBound;
    Intent playIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        parent_btn = findViewById(R.id.parent_btn);
        for (int i = 0; i < parent_btn.getChildCount(); i++) {
            (parent_btn.getChildAt(i)).setOnClickListener(ec);
        }

        Intent i = getIntent();
        String extraArtiste = i.getStringExtra("artiste");

        ensemble_chanson = Ensemble_chanson.getInstance(this);
        ensemble_chanson.setToutesChansons(this, extraArtiste);

        extraId = i.getIntExtra("chanson",0);
        ensemble_chanson.indexChansonJouee = extraId;

        titre = findViewById(R.id.chanson_txt);
        artiste = findViewById(R.id.artiste_txt);
        pochette = findViewById(R.id.Alnum_cover_img);
        titre.setText(ensemble_chanson.getChanson().getNom());
        artiste.setText(ensemble_chanson.getChanson().getArtiste());
        pochette.setImageBitmap(ensemble_chanson.getChanson().getPochette());
    }

    public class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

//    private class MusiqueConnection implements ServiceConnection  {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//
//            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
//            musicSrv = binder.getService(); //permet d’initialiser variable déclaréee
//            mediaPlayer = musicSrv.getDiffuseur(); // permet d’initialiser variable déclarée
//
//            gd = new GestionDiffuseur();
//            mediaPlayer.setOnPreparedListener(gd);
//            mediaPlayer.setOnCompletionListener(gd);
//            mediaPlayer.setOnErrorListener(gd);
//            mediaPlayer.setOnSeekCompleteListener(gd);
//
//
//            musicBound = true;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            musicBound=false;
//        }
//    }
}