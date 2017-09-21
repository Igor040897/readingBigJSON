package com.igor040897.test.bd;

import android.app.Activity;
import android.util.JsonReader;

import com.igor040897.test.bd.realmObjects.Country;
import com.igor040897.test.bd.realmObjects.RealmString;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by fanre on 9/16/2017.
 */

public class JSONReader {

    public JSONReader(final Activity act) {
        if (Realm.getDefaultInstance().isEmpty()) {
            String json = readFromAsset(act);
            JsonReader reader = new JsonReader(new StringReader(json));

            try {
                readJSONCountries(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readJSONCountries(final JsonReader reader) throws IOException {

        String text = null;
        ArrayList<Country> countries = new ArrayList<>();

        reader.beginObject();
        while (reader.hasNext()) {
            String country = reader.nextName();
            RealmList<RealmString> cities = readStringsArray(reader);
            countries.add(new Country(country, cities));
        }
        reader.endObject();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(countries);
        realm.commitTransaction();

    }

    private RealmList<RealmString> readStringsArray(final JsonReader reader) throws IOException {
        RealmList<RealmString> cities = new RealmList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            cities.add(new RealmString(reader.nextString()));
        }
        reader.endArray();
        return cities;
    }

    private String readFromAsset(final Activity act) {
        String text = "";
        try {
            InputStream is = act.getAssets().open("countriesToCities.json");

            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
