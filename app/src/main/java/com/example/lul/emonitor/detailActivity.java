package com.example.lul.emonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;


public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView eqDetailView  = findViewById(R.id.eq_detail_text_view);

        Bundle extras = getIntent().getExtras();

        Earthquake earthquake = extras.getParcelable(MainActivity.SELECT_EARTHQUAKE);

        if ( earthquake != null ){
            eqDetailView.setText(earthquake.getMagnitude() + ":" + earthquake.getPlace());

        }

    }
}
