package com.igor040897.test;

import android.os.AsyncTask;

import org.geonames.WebService;
import org.geonames.WikipediaArticle;

import java.util.List;

/**
 * Created by Igor040897 on 10/1/2017.
 */

public class GeoNamesTask extends AsyncTask<String, Void, WikipediaArticle> {

    @Override
    protected WikipediaArticle doInBackground(final String... strings) {
        return queryGeoNames_countryCode(strings[0]);
    }

    private WikipediaArticle queryGeoNames_countryCode(final String query) {
        WebService.setUserName("ihor.kolisnyk");

        try {
            List<WikipediaArticle> wikiResults = WebService.wikipediaSearch(query, "en");
            return wikiResults.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
