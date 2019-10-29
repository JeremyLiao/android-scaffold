package com.jeremyliao.android.scaffold.litho;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;

public class ListLithoDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ComponentContext context = new ComponentContext(this);

        final Component component =
                RecyclerCollectionComponent.create(context)
                        .disablePTR(true)
                        .section(ListSection.create(new SectionContext(context)).build())
                        .build();

        setContentView(LithoView.create(context, component));
    }

}
