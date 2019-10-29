package com.jeremyliao.android.scaffold.litho;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.Row;
import com.facebook.litho.widget.SolidColor;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;

public class SimpleLithoDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ComponentContext context = new ComponentContext(this);

        final Component component = Column
                .create(context)
                .alignItems(YogaAlign.CENTER)
                .child(
                        SolidColor
                                .create(context)
                                .color(Color.YELLOW)
                                .widthDip(40)
                                .heightDip(40)
                )
                .child(
                        Text.create(context)
                                .text("hello world")
                                .textSizeDip(30)
                )
                .child(
                        Text.create(context)
                                .text("Litho tutorial")
                                .textSizeSp(20)
                )
                .build();

        setContentView(LithoView.create(context, component));
    }

}
