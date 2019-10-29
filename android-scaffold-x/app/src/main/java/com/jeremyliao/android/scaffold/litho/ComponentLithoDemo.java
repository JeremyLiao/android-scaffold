package com.jeremyliao.android.scaffold.litho;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;

public class ComponentLithoDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ComponentContext context = new ComponentContext(this);

        final Component component = MyComponent
                .create(context)
                .color(Color.GRAY)
                .title("hello world")
                .build();

        setContentView(LithoView.create(context, component));
    }

}
