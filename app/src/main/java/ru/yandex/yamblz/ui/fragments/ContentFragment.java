package ru.yandex.yamblz.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.other.ItemTouchHelperCallback;
import ru.yandex.yamblz.ui.other.ListItemDecoration;

public class ContentFragment extends BaseFragment {

    private ItemTouchHelper mItemTouchHelper;
    private GridLayoutManager gridLayoutManager;
    private ListItemDecoration listItemDecoration;
    private boolean isBordered = false;
    private int spanCount = 1;

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.more_columns:
                spanCount = Math.min(30, spanCount + 1);
                gridLayoutManager.setSpanCount(spanCount);
                rv.getAdapter().notifyDataSetChanged();
                Toast.makeText(getContext(), "spanCount = " + spanCount, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.less_columns:
                spanCount = Math.max(1, spanCount - 1);
                gridLayoutManager.setSpanCount(spanCount);
                rv.getAdapter().notifyDataSetChanged();
                Toast.makeText(getContext(), "spanCount = " + spanCount, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.border:
                if (isBordered) {
                    rv.removeItemDecoration(listItemDecoration);
                } else {
                    rv.addItemDecoration(listItemDecoration);
                }
                rv.getAdapter().notifyDataSetChanged();
                isBordered = !isBordered;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listItemDecoration = new ListItemDecoration();
        gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        rv.setLayoutManager(gridLayoutManager);
        ContentAdapter adapter = new ContentAdapter();
        rv.setAdapter(adapter);
        //rv.setHasFixedSize(false);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rv);
    }
}
