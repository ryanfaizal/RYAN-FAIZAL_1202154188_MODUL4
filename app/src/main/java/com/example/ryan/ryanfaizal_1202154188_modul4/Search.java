package com.example.ryan.ryanfaizal_1202154188_modul4;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

public class Search extends AppCompatActivity {
    EditText link;
    Button search ;
    ImageView image;
    ProgressDialog progress;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        link =(EditText)findViewById(R.id.edit_link);
        image = (ImageView)findViewById(R.id.imageView);
        search = (Button) findViewById(R.id.button_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = link.getText().toString();
                new DownloadImage().execute(url);
            }
        });
    }
    private class DownloadImage extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // membuat progressdialog
            progress = new ProgressDialog(Search.this);
            // Mengeset judul progressdialog
            progress.setTitle("Download Image");
            // Mengeset pesan / message progressdialog
            progress.setMessage("Loading...");
            progress.setIndeterminate(false);
            // Menampilkan progressdialog
            progress.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

          String imageURL = URL [0];

            Bitmap bitmap = null;
            try {
                // MenDownload gambar dari URL
               InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception a) {
                a.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Menset bitmap kedalam ImageView
            image.setImageBitmap(result);
            // Menutup progressdialog
            progress.dismiss();
        }
    }
}
