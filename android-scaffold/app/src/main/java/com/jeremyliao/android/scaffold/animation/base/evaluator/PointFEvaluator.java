package com.jeremyliao.android.scaffold.animation.base.evaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by liaohailiang on 2020-07-17.
 */
public class PointFEvaluator implements TypeEvaluator<PointF> {

    PointF newPoint = new PointF();

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float x = startValue.x + (fraction * (endValue.x - startValue.x));
        float y = startValue.y + (fraction * (endValue.y - startValue.y));
        newPoint.set(x, y);
        return newPoint;
    }
}
