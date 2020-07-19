package com.jeremyliao.android.scaffold.animation.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.jeremyliao.android.scaffold.R;
import com.jeremyliao.android.scaffold.animation.base.evaluator.TranslationEvaluator;
import com.jeremyliao.android.scaffold.databinding.ActivityAnimationBaseBinding;
import com.jeremyliao.android.scaffold.databinding.ActivityPaintBaseBinding;

public class AnimationBaseActivity extends AppCompatActivity {

    ActivityAnimationBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animation_base);
        binding.setLifecycleOwner(this);
        binding.setHandler(this);
    }

    public void animation1() {
        binding.imgAnim1.animate()
                .translationX(500)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        binding.imgAnim1.setTranslationX(0);
                    }
                })
                .start();
    }

    public void animation2() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(binding.imgAnim2,
                "translationX", 500);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                binding.imgAnim2.setTranslationX(0);
            }
        });
        animator.start();
    }

    /**
     * more:https://hencoder.com/ui-1-6/
     */
    public void animation3() {
        binding.imgAnim3.animate()
                .translationX(500)
                .setInterpolator(new OvershootInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        binding.imgAnim3.setTranslationX(0);
                    }
                })
                .start();
    }

    public void animation4() {
        ObjectAnimator animator = ObjectAnimator.ofObject(binding.imgAnim4, "translationX",
                new TranslationEvaluator(), 500.0f);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                binding.imgAnim4.setTranslationX(0);
            }
        });
        animator.start();
    }

    public void animation5() {
        binding.imgAnim5.animate()
                .scaleX(0)
                .scaleY(0)
                .alpha(0)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        binding.imgAnim5.setScaleX(1.0f);
                        binding.imgAnim5.setScaleY(1.0f);
                        binding.imgAnim5.setAlpha(1.0f);
                    }
                })
                .start();
    }

    public void animation6() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(binding.imgAnim6, holder1, holder2, holder3);
        animator.start();
    }

    public void animation7() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(binding.imgAnim7, "translationX", 0, 500);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(binding.imgAnim7, "translationX", 500, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animator1, animator2);
//        animatorSet.play(animator1).before(animator2);
        animatorSet.start();
    }

    public void animation8() {
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 500);
        Keyframe keyframe3 = Keyframe.ofFloat(1, 400);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("translationX",
                keyframe1, keyframe2, keyframe3);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(binding.imgAnim8, holder);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                binding.imgAnim8.setTranslationX(0);
            }
        });
        animator.start();
    }

    public void animation9() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                binding.imgAnim9.setTranslationX((float) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                binding.imgAnim9.setTranslationX(0);
            }
        });
        animator.start();
    }
}
