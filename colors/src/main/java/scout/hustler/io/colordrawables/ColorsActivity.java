package scout.hustler.io.colordrawables;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
//        final TextView selectedTextView = (TextView) selectedView;


        final boolean[] isShadowApplied = new boolean[1];
        final TextView head_tv, demo_tv;
        RecyclerView colors_rv;
        Button close, choose, shadow;
        ColorsAdapter colorsAdapter;
        final int[] choosen_color = new int[1];


        head_tv = findViewById(R.id.color_text);
        demo_tv = findViewById(R.id._demo_color_text);
        colors_rv = findViewById(R.id.colors_rv);
        close = findViewById(R.id.bt_color_close);
        choose = findViewById(R.id.bt_color_choose);
        shadow = findViewById(R.id.bt_color_shadow);
        TextUtils.setFont(this, head_tv, Constants.FONT_CIRCULAR);
        TextUtils.setFont(this, close, Constants.FONT_CIRCULAR);
        TextUtils.setFont(this, choose, Constants.FONT_CIRCULAR);
        colors_rv.setLayoutManager(new GridLayoutManager(this, 6));

        colorsAdapter = new ColorsAdapter(this, new ColorsAdapter.OnColorClickListener() {
            @Override
            public void onColorClick(int color) {
                choosen_color[0] = color;
                demo_tv.setTextColor(color);

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();//finishing activity
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (0 > choosen_color.length) {
                    Toast.makeText(ColorsActivity.this, "SELECT ANY COLR TO APPLY", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("Color", choosen_color[0]);
                    setResult(Constants.COLORACTIVITY, intent);
                    finish();//finishing activity
                }

            }
        });
        colors_rv.setAdapter(colorsAdapter);
    }
}

