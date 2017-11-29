package com.webber.demos.customerview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.webber.demos.R;

import java.net.URL;

public class CustomerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        String str = "twmotherlode://article?a=1_1248785&m=10101";

        Uri uri = Uri.parse(str);

        Log.d("picher","Sche:"+uri.getScheme());
        Log.d("picher","Path:"+uri.getPath());
        Log.d("picher","Port:"+uri.getPort());
        Log.d("picher","Host"+uri.getHost());
        Toast.makeText(this,uri.getQueryParameter("a"),Toast.LENGTH_SHORT).show();
    }
}
