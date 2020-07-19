package com.jeremyliao.android.scaffold.paint.paintbase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.jeremyliao.android.scaffold.R;

/**
 * Created by liaohailiang on 2019-05-24.
 * https://hencoder.com/ui-1-1/
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PaintBaseView extends View {

    Paint paint = new Paint();
    Paint paint1 = new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();
    Paint paint4 = new Paint();
    Paint paint5 = new Paint();
    Paint paint6 = new Paint();
    Path path = new Path();
    String text = "hello world!";
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
    Rect bounds = new Rect();
    Camera camera = new Camera();
    int offsetX = 100;
    int offsetY = 100;

    {
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
    }

    {
        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint1.setShader(shader);
    }

    {
        Shader shader = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint2.setShader(shader);
    }

    {
        Shader shader = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"));
        paint3.setShader(shader);
    }

    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint4.setShader(shader);
    }

    {
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_nav_picture);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER);
        paint5.setShader(shader);
    }

    {
        ColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x003000);
        paint6.setColorFilter(lightingColorFilter);
    }

    public PaintBaseView(Context context) {
        super(context);
    }

    public PaintBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
//        drawLines(canvas);
//        drawArcs(canvas);
//        drawPath(canvas);
//        drawText(canvas);
//        drawRect(canvas);
//        drawCircleWithPaint1(canvas);
//        drawCircleWithPaint2(canvas);
//        drawCircleWithPaint3(canvas);
//        drawCircleWithPaint4(canvas);
//        drawCircleWithPaint5(canvas);
//        drawBitmapWithPaint6(canvas);
//        drawBitmapWithBlur(canvas);
//        drawTextOnPath(canvas);
//        drawTextBounds(canvas);
//        drawTextAdvance(canvas);
//        drawWithClipRect(canvas);
//        drawWithTranslate(canvas);
        drawWithCamera(canvas);
    }

    private void drawLines(Canvas canvas) {
        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
        canvas.drawLines(points, paint);
    }

    private void drawArcs(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL); // 填充模式
        canvas.drawArc(200, 100, 800, 500, -110, 100, true, paint); // 绘制扇形
        canvas.drawArc(200, 100, 800, 500, 20, 140, false, paint); // 绘制弧形
        paint.setStyle(Paint.Style.STROKE); // 画线模式
        canvas.drawArc(200, 100, 800, 500, 180, 60, false, paint); // 绘制不封口的弧形
    }

    private void drawPath(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    private void drawText(Canvas canvas) {
        paint.setTextSize(18);
        canvas.drawText(text, 100, 25, paint);
        paint.setTextSize(36);
        canvas.drawText(text, 100, 70, paint);
        paint.setTextSize(60);
        canvas.drawText(text, 100, 145, paint);
        paint.setTextSize(84);
        canvas.drawText(text, 100, 240, paint);
    }

    private void drawRect(Canvas canvas) {
        paint.setARGB(100, 255, 0, 0);
        canvas.drawRect(0, 0, 200, 200, paint);
    }

    private void drawCircleWithPaint1(Canvas canvas) {
        canvas.drawCircle(300, 300, 200, paint1);
    }

    private void drawCircleWithPaint2(Canvas canvas) {
        canvas.drawCircle(300, 300, 200, paint2);
    }

    private void drawCircleWithPaint3(Canvas canvas) {
        canvas.drawCircle(300, 300, 200, paint3);
    }

    private void drawCircleWithPaint4(Canvas canvas) {
        canvas.drawCircle(100, 100, 100, paint4);
    }

    private void drawCircleWithPaint5(Canvas canvas) {
        canvas.drawCircle(100, 100, 100, paint5);
    }

    private void drawBitmapWithPaint6(Canvas canvas) {
        canvas.drawBitmap(bitmap, 100, 100, paint6);
    }

    private void drawTextWithShadow(Canvas canvas) {
        paint.setShadowLayer(10, 0, 0, Color.RED);
        canvas.drawText(text, 80, 300, paint);
    }

    private void drawBitmapWithBlur(Canvas canvas) {
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }

    private void drawTextOnPath(Canvas canvas) {
        canvas.drawPath(path, paint);
        paint.setTextSize(50);
        canvas.drawTextOnPath(text, path, 0, 0, paint);
    }

    private void drawTextBounds(Canvas canvas) {
        paint.setTextSize(60);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, offsetX, offsetY, paint);

        paint.getTextBounds(text, 0, text.length(), bounds);
        bounds.left += offsetX;
        bounds.top += offsetY;
        bounds.right += offsetX;
        bounds.bottom += offsetY;
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(bounds, paint);
    }

    private void drawTextAdvance(Canvas canvas) {
        paint.setTextSize(60);
        int length = text.length();
        float advance = paint.getRunAdvance(text, 0, length, 0, length, false, length);
        canvas.drawText(text, offsetX, offsetY, paint);
        canvas.drawLine(offsetX + advance, offsetY - 50, offsetX + advance, offsetY + 10, paint);
    }

    private void drawWithClipRect(Canvas canvas) {
        canvas.save();
        canvas.clipRect(100, 100, 200, 200);
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
        canvas.restore();
    }

    private void drawWithTranslate(Canvas canvas) {
        canvas.save();
        canvas.translate(200, 0);
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
        canvas.restore();
    }

    private void drawWithCamera(Canvas canvas) {
        canvas.save();

        camera.save(); // 保存 Camera 的状态
        camera.rotateX(30); // 旋转 Camera 的三维空间
        canvas.translate(100, 100); // 旋转之后把投影移动回来
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-100, -100); // 旋转之前把绘制内容移动到轴心（原点）
        camera.restore(); // 恢复 Camera 的状态

        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
        canvas.restore();
    }
}
