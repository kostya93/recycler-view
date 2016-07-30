package ru.yandex.yamblz.ui.other;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kostya on 30.07.16.
 */

public class LastMovedDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;

    private int lastMovedFirst = -1;
    private int lastMovedSecond = -1;

    public void setLastMovedFirst(int lastMovedFirst) {
        this.lastMovedFirst = lastMovedFirst;
    }
    public void setLastMovedSecond(int lastMovedSecond) {
        this.lastMovedSecond = lastMovedSecond;
    }

    public LastMovedDecoration() {
        super();
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (lastMovedFirst != -1 && lastMovedSecond != -1) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                final View child = parent.getChildAt(i);
                int adapterPos = parent.getChildAdapterPosition(child);
                if (adapterPos == lastMovedFirst || adapterPos == lastMovedSecond) {
                    c.drawRect(layoutManager.getDecoratedLeft(child) + 10,
                            layoutManager.getDecoratedTop(child) + 10,
                            layoutManager.getDecoratedRight(child) - 10,
                            layoutManager.getDecoratedBottom(child) - 10,
                            paint);
                }
            }
        }
    }
}
