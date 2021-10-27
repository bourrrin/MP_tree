package luca.reymann.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class chansonActivity extends AppCompatActivity {
    ListView liste;
    SimpleAdapter adapter;
    Ensemble_chanson chanson;
    String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chanson);
        liste = findViewById(R.id.list_chansons);

        Intent i = getIntent();
        extra = i.getStringExtra("artiste");

        chanson = Ensemble_chanson.getInstance(this);
        chanson.setToutesChansons(this, extra);

        adapter = new SimpleAdapter(this, chanson.getToutChanson(), R.layout.element, new String[]{"pochette", "nom", "artiste"}, new int[]{R.id.pochette, R.id.titre, R.id.artiste});
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if ((view instanceof ImageView) & (data instanceof Bitmap)) {
                    ImageView imageView = (ImageView) view;
                    Bitmap bitmap = (Bitmap) data;
                    imageView.setImageBitmap(bitmap);
                    return true;
                }
                return false;
            }
        });
        liste.setAdapter(adapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                Intent i = new Intent(chansonActivity.this, MediaPlayer.class);
                i.putExtra("chanson", position);
                i.putExtra("artiste", extra);
                startActivity(i);
            }
        });
    }


}