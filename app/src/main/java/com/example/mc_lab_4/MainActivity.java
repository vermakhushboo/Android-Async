package com.example.mc_lab_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button b1;
    ImageView imageView;
    ProgressDialog pd;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button3);
        imageView = (ImageView) findViewById(R.id.imageView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadImage().execute();
            }
        });
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected void onPreExecute(){
            pd = new ProgressDialog(MainActivity.this);
            pd.setTitle("Please wait..");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMessage("Downloading");
            pd.show();
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try{
                InputStream inputStream = new java.net.URL("https://cdn.pixabay.com/photo/2015/04/19/08/32/marguerite-729510__340.jpg").openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }catch(Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap){
            pd.dismiss();
            imageView.setImageBitmap(bitmap);
        }
    }

}