package com.example.moviesguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HeadChooseActivity extends BaseActivity {

    ImageView[] iv = new ImageView[8];

//    int[] ids = { R.id.image_01, R.id.image_02, R.id.image_03, R.id.image_04,
//            R.id.image_05, R.id.image_06, R.id.image_07, R.id.image_08,
//            R.id.image_09 };
//    int[] imgId = { R.drawable.img01, R.drawable.img02, R.drawable.img03,
//            R.drawable.img04, R.drawable.img05, R.drawable.img06,
//            R.drawable.img07, R.drawable.img08, R.drawable.img09 };

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

//        for (int i = 0; i < iv.length; i++) {
//            final int finalI = i;
//            //给每一个ImageView找到id
//            iv[i] = (ImageView) findViewById(ids[i]);
//            //设置点击事件监听
//            iv[i].setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //返回数据
//                    Intent intent = getIntent();
//                    //内部类使用外部局部变量，需要final
//                    intent.putExtra("image", imgId[finalI]);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            });
//        }
    }

}