package ru.yandex.yamblz.ui.other;

/**
 * Created by kostya on 30.07.16.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
