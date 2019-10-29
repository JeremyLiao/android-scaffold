package com.jeremyliao.android.scaffold.litho;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.SolidColor;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;

/**
 * Created by liaohailiang on 2019-10-29.
 */
@LayoutSpec
public class MyComponentSpec {
    @OnCreateLayout
    static Component onCreateLayout(
            ComponentContext c,
            @Prop int color,
            @Prop String title) {

        return Row.create(c)
                .alignItems(YogaAlign.CENTER)
                .child(
                        SolidColor.create(c)
                                .color(color)
                                .widthDip(40)
                                .heightDip(40))
                .child(
                        Text.create(c)
                                .text(title)
                                .textSizeDip(30)
                                .flexGrow(1f))
                .build();
    }
}