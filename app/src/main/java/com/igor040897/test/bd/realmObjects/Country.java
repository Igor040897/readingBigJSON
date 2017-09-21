package com.igor040897.test.bd.realmObjects;

import io.realm.RealmList;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by fanre on 9/16/2017.
 */

public class Country extends RealmObject{
    @PrimaryKey
    @Required
    private String country;

    private RealmList<RealmString> cities;

    public Country(){}

    public Country(String countre, RealmList<RealmString> cities) {
        this.country = countre;
        this.cities = cities;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public RealmList<RealmString> getCities() {
        return cities;
    }

    public void setCities(RealmList<RealmString> cities) {
        this.cities = cities;
    }
}
