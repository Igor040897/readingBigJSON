package com.igor040897.test;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
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

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final RealmString city = items.get(position);
        holder.city.setText(city.getStringValue());

        holder.city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(holder.city.getContext(), InfoCityActivity.class);
                intent.putExtra("City", city.getStringValue());
                holder.city.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(final RealmList<RealmString>items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView city;

        ItemViewHolder(final View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city);
        }
    }
}
