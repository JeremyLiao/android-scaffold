package com.jeremyliao.android.scaffold.litho;

import android.graphics.Color;

import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.common.SingleComponentSection;

/**
 * Created by liaohailiang on 2019-10-29.
 */
@GroupSectionSpec
public class ListSectionSpec {

    @OnCreateChildren
    static Children onCreateChildren(final SectionContext c) {
        Children.Builder builder = Children.create();

        for (int i = 0; i < 32; i++) {
            builder.child(
                    SingleComponentSection.create(c)
                            .key(String.valueOf(i))
                            .component(ListItem.create(c)
                                    .color(i % 2 == 0 ? Color.WHITE : Color.LTGRAY)
                                    .title(i + ". Hello, world!")
                                    .subtitle("Litho tutorial")
                                    .build()));
        }
        return builder.build();
    }
}