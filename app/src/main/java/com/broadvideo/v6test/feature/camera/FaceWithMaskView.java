package com.broadvideo.v6test.feature.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangjingyi on 28/03/2017.
 */

public class FaceWithMaskView extends View {
    private List<String> ids;
    private List<String> yaws;
    private List<String> pitchs;
    private List<String> rolls;
    private List<String> blurs;
    private List<String> smiles;
    private List<FaceRect> rect;
    private Paint paint = new Paint();
    private Paint idPaint = new Paint();
    private Paint posePaint = new Paint();
    private Paint backPaint = new Paint();

    private void initData() {
        ids = new ArrayList<String>();
        yaws = new ArrayList<>();
        pitchs = new ArrayList<>();
        rolls = new ArrayList<>();
        blurs = new ArrayList<>();
        smiles = new ArrayList<>();
        rect = new ArrayList<FaceRect>();
        paint.setARGB(122, 13, 101, 231);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10.0f);

        backPaint.setARGB(122, 13, 101, 231);
        backPaint.setStyle(Paint.Style.FILL);
        backPaint.setStrokeWidth(10.0f);

        idPaint.setARGB(255, 255, 0, 0);
        idPaint.setTextSize(40);

        posePaint.setARGB(255, 80, 80, 80);
        posePaint.setTextSize(25);
    }

    public FaceWithMaskView(Context context) {
        super(context);
        initData();
    }

    public FaceWithMaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public FaceWithMaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    public void addId(String label) {
        ids.add(label);
    }

    public void addYaw(String label) {
        yaws.add(label);
    }
    public void addPitch(String label) {
        pitchs.add(label);
    }
    public void addRoll(String label) {
        rolls.add(label);
    }
    public void addBlur(String label)  {
        blurs.add(label);
    }
    public void addSmile(String lable) {
        smiles.add(lable);
    }

    public void addRect(FaceRect rect) {
//        FaceRect buffer = new FaceRect();
//        RectF rectF = new RectF();
//        buffer.rect = rectF;
//        buffer.rect.left = (int) rect.rect.left;
//        buffer.rect.top = (int) rect.rect.top;
//        buffer.rect.right = (int) rect.rect.right;
//        buffer.rect.bottom = (int) rect.rect.bottom;
//        buffer.hasMask = rect.hasMask;
        this.rect.add(rect);
    }

    public void clear() {
        rect.clear();
//        ids.clear();
//        yaws.clear();
//        rolls.clear();
//        blurs.clear();
//        pitchs.clear();
//        smiles.clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < rect.size(); i++) {
            FaceRect r = rect.get(i);
            canvas.drawRect(r.rect, paint);
            canvas.drawRect(r.rect.right+5, r.rect.top - 5, r.rect.right, r.rect.top + 200, backPaint);
        }
        this.clear();
    }
}
