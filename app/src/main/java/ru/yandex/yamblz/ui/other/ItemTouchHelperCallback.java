package ru.yandex.yamblz.ui.other;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by kostya on 30.07.16.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapter mAdapter;
    private LastMovedDecoration lastMovedDecoration;
    private boolean isMoving = false;

    public ItemTouchHelperCallback(ItemTouchHelperAdapter mAdapter, LastMovedDecoration lastMovedDecoration) {
        this.mAdapter = mAdapter;
        this.lastMovedDecoration = lastMovedDecoration;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP   | ItemTouchHelper.DOWN |
                              ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int lastMovedFirst = viewHolder.getAdapterPosition();
        int lastMovedSecond = target.getAdapterPosition();

        lastMovedDecoration.setLastMovedFirst(lastMovedFirst);
        lastMovedDecoration.setLastMovedSecond(lastMovedSecond);

        mAdapter.onItemMove(lastMovedFirst, lastMovedSecond);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }


    @Override
    public boolean isItemViewSwipeEnabled() {
        Log.d("isItemViewSwipeEnabled", "isItemViewSwipeEnabled");
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        Log.d("isLongPressDragEnabled", "isLongPressDragEnabled");
        return true;
    }

}
