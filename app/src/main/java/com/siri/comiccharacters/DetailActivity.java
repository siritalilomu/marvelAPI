package com.siri.comiccharacters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView name;
    TextView realname;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = (TextView) findViewById(R.id.name_text_view);
        name.setText(getIntent().getStringExtra("NAME"));


        realname = (TextView) findViewById(R.id.realname_text_view);
        realname.setText(getIntent().getStringExtra("REALNAME"));


        mImageView = (ImageView) findViewById(R.id.imageView);
        Picasso.get().load(getIntent().getStringExtra("URL")).resize(400, 420).centerCrop().into(mImageView);
    }
}
