package com.example.lab9;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout rl = new RelativeLayout(this);

        int height = (int) getResources().getDimension(R.dimen.size);
        int width = (int) getResources().getDimension(R.dimen.size);
        int padding = (int) getResources().getDimension(R.dimen.padding);
        int alpha=127;

        ShapeDrawable redShape = new ShapeDrawable(new OvalShape());
        redShape.getPaint().setColor(Color.RED);
        redShape.setIntrinsicHeight(height);
        redShape.setIntrinsicWidth(width);
        redShape.setAlpha(alpha);

        ImageView iv = new ImageView(rl.getContext());
        iv.setPadding(padding,padding,padding,padding);
        iv.setImageDrawable(redShape);

        RelativeLayout.LayoutParams redViewLayoutParams = new
                RelativeLayout.LayoutParams(height,width);
        redViewLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        redViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        iv.setLayoutParams(redViewLayoutParams);
        rl.addView(iv);

        ShapeDrawable blueShape = new ShapeDrawable(new OvalShape());
        blueShape.getPaint().setColor(Color.BLUE);
        blueShape.setIntrinsicHeight(height);
        blueShape.setIntrinsicWidth(width);
        blueShape.setAlpha(alpha);

        ImageView iv2 = new ImageView(rl.getContext());
        iv2.setPadding(padding,padding,padding,padding);
        iv2.setImageDrawable(blueShape);

        RelativeLayout.LayoutParams blueViewLayoutParams = new
                RelativeLayout.LayoutParams(height,width);
        blueViewLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        blueViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        iv2.setLayoutParams(blueViewLayoutParams);
        rl.addView(iv2);


        setContentView(rl);
    }
}
