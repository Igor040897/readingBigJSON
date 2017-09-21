package com.igor040897.test;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igor040897.test.bd.realmObjects.RealmString;

import io.realm.RealmList;

/**
 * Created by fanre on 6/27/2017.
 */
public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ItemViewHolder> {
    private final RealmList<RealmString> items = new RealmList<>();
    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final RealmString city = items.get(position);
        holder.city.setText(city.getStringValue());
        holder.container.setActivated(selectedItems.get(position, false));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(final RealmList<RealmString>items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void add(final RealmString item) {
        items.add(item);
        notifyDataSetChanged();
    }

    RealmString find(final int pos) {
        return items.get(pos);
    }

    RealmString remove(final int pos) {
        final RealmString item = items.remove(pos);
        notifyItemRemoved(pos);
        return item;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView city;
        private final View container;

        ItemViewHolder(final View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.item_container);
            city = (TextView) itemView.findViewById(R.id.city);
        }
    }
}
