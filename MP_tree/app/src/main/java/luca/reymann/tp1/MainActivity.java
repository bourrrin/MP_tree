package luca.reymann.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

//===================================================
//HEY DUDE TU DEVRAS DEMANDER DES PERMISSIONS WESH (ACTIVITER 3B) !!!!!
//===================================================


public class MainActivity extends AppCompatActivity {

    Ecouteur ec = new Ecouteur();
    LinearLayout parent_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parent_btn = findViewById(R.id.parent_btn_main);
        for (int i = 0; i < parent_btn.getChildCount(); i++) {
            (parent_btn.getChildAt(i)).setOnClickListener(ec);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 666);
        }
    }

    public class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent i = new Intent();

            if (v.getId() == R.id.chanson_btn) {
                i = new Intent(MainActivity.this, chansonActivity.class);
            } else if (v.getId() == R.id.artiste_btn) {
                i = new Intent(MainActivity.this, Artiste.class);
            } else {
                finish();
            }

            startActivity(i);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 666)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Oui, utiliser les fichiers", Toast.LENGTH_LONG).show();
    }

}