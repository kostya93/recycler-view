package ru.yandex.yamblz.ui.other;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kostya on 30.07.16.
 */

public class ListItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;

    public ListItemDecoration() {
        super();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            int adapterPos = parent.getChildAdapterPosition(child);
            if (adapterPos % 2 == 0) {
                c.drawRect(layoutManager.getDecoratedLeft(child) + 10,
                           layoutManager.getDecoratedTop(child) + 10,
                           layoutManager.getDecoratedRight(child) - 10,
                           layoutManager.getDecoratedBottom(child) - 10,
                           paint);
            }
        }
    }
}
