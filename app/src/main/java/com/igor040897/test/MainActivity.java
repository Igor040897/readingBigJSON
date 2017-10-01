package com.igor040897.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.igor040897.test.bd.JSONReader;
import com.igor040897.test.bd.realmObjects.Country;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Spinner spCountries;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spCountries = findViewById(R.id.spCountries);
        recyclerView = findViewById(R.id.cities);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new JSONReader(this);

        ArrayList<String> nameCountries = findAllNameCountries();

        final ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, nameCountries);
        spCountries.setAdapter(priorityAdapter);

        spCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, View view, int i, long l) {
                recyclerView.setAdapter(getAdapterCountry());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private RecyclerView.Adapter getAdapterCountry() {
        final Realm realm = Realm.getDefaultInstance();
        String string = spCountries.getSelectedItem().toString();
        Country country = realm.where(Country.class).equalTo("country", string).findFirst();
        CitiesAdapter adapter = new CitiesAdapter();
        adapter.addAll(country.getCities());
        return adapter;
    }

    private ArrayList<String> findAllNameCountries() {
        final Realm realm = Realm.getDefaultInstance();
        RealmResults<Country> countries = realm.where(Country.class).findAll();

        ArrayList<String> nameCountries = new ArrayList<>();
        for (Country country : countries) {
            nameCountries.add(country.getCountry());
        }
        return nameCountries;
    }
}
