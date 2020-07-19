package com.jeremyliao.android.scaffold.animation.base.evaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by liaohailiang on 2020-07-17.
 * 这个其实就是一个FloatEvaluator
 * 测试一下自定义Evaluator
 */
public class TranslationEvaluator implements TypeEvaluator<Float> {

    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        return startValue + (fraction * (endValue - startValue));
    }
}
