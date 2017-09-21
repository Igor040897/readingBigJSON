package com.igor040897.test.bd.realmObjects;

import io.realm.RealmObject;

/**
 * Created by fanre on 9/16/2017.
 */

public class RealmString extends RealmObject {

    private String stringValue;

    public RealmString(){}

    public RealmString(String stringValue){
        this.stringValue =  stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

}
