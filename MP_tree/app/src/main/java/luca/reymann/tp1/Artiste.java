package luca.reymann.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Vector;

public class Artiste extends AppCompatActivity {

    ListView liste;
    Ensemble_chanson chanson;
    Vector<String> artistes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artiste);
        chanson = Ensemble_chanson.getInstance(this);
        chanson.setToutesChansons(this, null);
        artistes = chanson.getToutArtistes();
        liste = findViewById(R.id.liste_artiste);
        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_list_item_1, chanson.getToutArtistes());
        liste.setAdapter(a);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                Intent i = new Intent(Artiste.this, chansonActivity.class);
                i.putExtra("artiste", artistes.elementAt(position));
                startActivity(i);
            }
        });
    }

}