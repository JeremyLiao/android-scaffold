package com.jeremyliao.android.scaffold.recyclerview.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liaohailiang on 2020-02-06.
 */
public class DemoDecoration extends RecyclerView.ItemDecoration {

    private Paint paint = new Paint();
    private Paint paint1 = new Paint();

    public DemoDecoration(Context context) {
        paint.setColor(Color.rgb(100, 20, 50));
        paint1.setColor(Color.rgb(120, 80, 120));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position % 3 == 0) {
            outRect.top = 50;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            int position = parent.getChildAdapterPosition(child);
            if (position % 3 == 0) {
                int left = child.getLeft();
                int top = child.getTop() - 50;

                c.save();
                c.translate(left, top);
                c.drawRect(0, 0, child.getWidth(), child.getHeight(), paint);
                c.restore();
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        c.drawRect(0, 0, 200, 200, paint1);
    }
}
