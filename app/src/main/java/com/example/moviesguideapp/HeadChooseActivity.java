package com.example.moviesguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HeadChooseActivity extends BaseActivity {

    ImageView[] iv = new ImageView[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_chooser_dialog);

        for (int i = 0; i < iv.length; i++) {
            final int finalI = i+1;
            String name = "image_0"+finalI;
            try {
                Class cls = R.id.class;
                int id = cls.getField(name).getInt(null);
                iv[i] = (ImageView) findViewById(id);
                iv[i].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        Class cls2 = R.drawable.class;
                        try {
                            int imgid2 = cls2.getField("img0"+finalI).getInt(null);
                            intent.putExtra("image", imgid2);
                            setResult(RESULT_OK, intent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}