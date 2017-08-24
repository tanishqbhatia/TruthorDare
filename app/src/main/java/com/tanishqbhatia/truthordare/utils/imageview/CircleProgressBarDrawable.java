package com.tanishqbhatia.truthordare.utils.imageview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;

/**
 * Created by Tanishq Bhatia on 22-08-2017 at 11:05.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class CircleProgressBarDrawable extends ProgressBarDrawable {

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mLevel = 0;
    private int maxLevel = 10000;


    @Override
    protected boolean onLevelChange(int level) {
        mLevel = level;
        invalidateSelf();
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        if (getHideWhenZero() && mLevel == 0 || mLevel > 4500) {
            return;
        }
        drawBar(canvas, maxLevel, ColorCons.GREY_300);
        drawBar(canvas, mLevel, ColorCons.GREY_400);
    }

    private void drawBar(Canvas canvas, int level, int color) {
        Rect bounds = getBounds();
        RectF rectF = new RectF((float) (bounds.right * .4), (float) (bounds.bottom * .4),
                (float) (bounds.right * .6), (float) (bounds.bottom * .6));
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        if (level != 0)
            canvas.drawArc(rectF, 0, (float) (level * 360 / maxLevel), false, mPaint);
    }
}