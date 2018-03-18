package com.example.ryan.ryanfaizal_1202154188_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    private ProgressDialog progress;
    private String [] name = {"ADIT", "MAMAN", "BATSO", "TOYIB",
            "EKI", "DOYOK", "AAN",
            "BAGOES", "JUPE", "ROCHIM",
            "FAJAR", "AKSA", "ALAUWIY", "WAHYU"};
    private MyTask task;
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.list_nama);

        list.setVisibility(View.VISIBLE);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
    }

    public void Start(View view) {
        progress = new ProgressDialog(MainActivity.this);
        progress.setMax(15);
        progress.setMessage("Loading Data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        progress.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                task.cancel(true);
                dialogInterface.dismiss();
            }
        });
        progress.show();

        new MyTask().execute();
    }

    public void Cari (View view) {
        Intent intent = new Intent(MainActivity.this, Search.class);
        startActivity(intent);
    }

    class MyTask extends AsyncTask<Void, String, String> {

        ArrayAdapter<String> adapter;
        private int counter=1;

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>)list.getAdapter();
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (String Name : name){
                publishProgress(Name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);

            Integer current_status = (int)((counter/(float)name.length)*100);
            progress.setProgress(current_status);
            progress.setProgress(current_status);
            progress.setMessage(String.valueOf(current_status+"%"));
            counter++;
        }

        @Override
        protected void onPostExecute(String result){
            progress.dismiss();
            list.setVisibility(View.VISIBLE);
        }

    }
}
