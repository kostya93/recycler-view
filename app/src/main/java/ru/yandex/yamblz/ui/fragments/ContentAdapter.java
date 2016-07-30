package ru.yandex.yamblz.ui.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.other.ItemTouchHelperAdapter;

class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentHolder> implements ItemTouchHelperAdapter{

    private final Random rnd = new Random();
    private final List<Integer> colors = new ArrayList<>();

    @Override
    public ContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false), this);
    }

    @Override
    public void onBindViewHolder(ContentHolder holder, int position) {
        holder.bind(createColorForPosition(position));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    private Integer createColorForPosition(int position) {
        if (position >= colors.size()) {
            colors.add(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        }
        return colors.get(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        int direction = fromPosition < toPosition ? 1 : -1;
        for (int i = fromPosition; i != toPosition; i += direction) {
            Collections.swap(colors, i, i + direction);
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        colors.remove(position);
        notifyItemRemoved(position);
    }

    private Integer changeColor(int pos) {
        colors.set(pos, Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        return colors.get(pos);
    }

    private Integer getColorByPos(int pos) {
        return colors.get(pos);
    }

    static class ContentHolder extends RecyclerView.ViewHolder {

        ContentHolder(View itemView, ContentAdapter contentAdapter) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                int from = contentAdapter.getColorByPos(getAdapterPosition());
                int to = contentAdapter.changeColor(getAdapterPosition());
                ObjectAnimator.ofObject(itemView, "backgroundColor", new ArgbEvaluator(), from, to)
                        .setDuration(1000)
                        .start();
                ((TextView) itemView).setText("#".concat(Integer.toHexString(to).substring(2)));
            });
        }

        void bind(Integer color) {
            itemView.setBackgroundColor(color);
            ((TextView) itemView).setText("#".concat(Integer.toHexString(color).substring(2)));
        }


    }
}
