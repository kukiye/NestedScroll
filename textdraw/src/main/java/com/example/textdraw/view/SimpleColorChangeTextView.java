package com.example.textdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class SimpleColorChangeTextView extends AppCompatTextView {

    private String mText = "享学课堂";//成员变量

    private float mPercent = 0.0f;

    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float mPercent) {
        this.mPercent = mPercent;
        invalidate();//重绘
    }

    public SimpleColorChangeTextView(Context context) {
        super(context);
    }

    public SimpleColorChangeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleColorChangeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //TODO 1、drawText————以baseline作为Y轴基准线来绘制文字
        //反面教程 在onDraw方法里创建对象会频繁调用，引起内存抖动
        Paint paint = new Paint();
        paint.setTextSize(80);
        float baseline = 100;
        canvas.drawText(mText, 0, baseline, paint);

        //todo 2、drawLine————绘制直线
        drawCenterLineX(canvas);
        drawCenterLineY(canvas);

        //todo 3、drawText————设置X轴起始位置，并且setTextAlign————设置文字依靠X轴的对齐方式
        float x = getWidth() / 2;
        //1、默认LEFT
        canvas.drawText(mText, x, baseline, paint);
        //2、设置文字居中对齐
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(mText, x, baseline + paint.getFontSpacing(), paint);
        //3、设置文字RIGHT居右对齐
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(mText, x, baseline + paint.getFontSpacing() * 2, paint);


        //文字高度的计算

        //        public float ascent;
        //        public float bottom;
        //        public float descent;
        //        public float leading;
        //        public float top; 从baseline到文字最顶端的尺寸


        //第二种方式 x种居中
        //底层 黑色
        drawCenterTextBlack(canvas);
        //上面一层 红色
        drawCenterTextRed(canvas);
    }

    /**
     * 绘制黑色文字
     * mPercent 从0——>1 ，Rect矩形区域的left在往右移动，矩形区域在减小，黑色文字逐渐消失
     *
     * @param canvas
     */
    private void drawCenterTextBlack(final Canvas canvas) {
        //保存画布现在的样子
        canvas.save();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        //抗锯齿
        paint.setAntiAlias(true);
        paint.setTextSize(80);
        //TODO 4、把文字绘制到view的中心
        //获取文本的宽度
        float width = paint.measureText(mText);
        paint.setTextAlign(Paint.Align.LEFT);
        //设置X轴的起始位置，让文字居于X轴的正中间
        float left = getWidth() / 2 - width / 2;

        //获取文字的上下边界（fontMetrics.ascent，fontMetrics.descent）
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        //设置Y轴基线位置，让文字居于正Y轴中间
        float baseline = getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2;

        //TODO 5、clipRect ———— 裁剪矩形区域，矩形区域变成了一个图层覆盖在屏幕上

        //mPercent改变的时候，矩形区域的左边（left）一直在往右移动，直到完全移到文字宽度的位置
        //而文字的位置不变
        float left_x = left + width * mPercent;
        Rect rect = new Rect((int) left_x, 0, getWidth(), getHeight());
        canvas.clipRect(rect);
        canvas.drawText(mText, left, baseline, paint);

        //恢复到canvas.save()之前的样子
        //每次恢复的时候，矩形区域左边的文字会恢复到原来的样子即空白，矩形区域上的文字依然显示
        //所以效果是黑色文字在逐渐消失
        canvas.restore();
    }

    /**
     * 绘制红色文字
     * mPercent 从0——>1 ，矩形区域的右边一直增大，矩形区域在增大，红色文字逐渐显示
     *
     * @param canvas
     */
    private void drawCenterTextRed(final Canvas canvas) {

        //
        canvas.save();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(80);
        float width = paint.measureText(mText);
        paint.setTextAlign(Paint.Align.LEFT);

        float left = getWidth() / 2 - width / 2;
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baseline = getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2;

        float right = left + width * mPercent;
        //矩形区域的最右边一直在增大
        Rect rect = new Rect((int) left, 0, (int) right, getHeight());
        canvas.clipRect(rect);
        canvas.drawText(mText, left, baseline, paint);


        canvas.restore();

    }

    /**
     * 绘制X轴的中心线
     *
     * @param canvas
     */
    private void drawCenterLineX(final Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), paint);
    }

    /**
     * 绘制Y轴的中心线
     *
     * @param canvas
     */
    private void drawCenterLineY(final Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, paint);
    }


}
