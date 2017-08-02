package org.launchcode.flickrsearch.models;

import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by LaunchCode
 */
public class FlickrConnector {

    public static String fetchImageJson(String[] tags) {

        BufferedReader in = null;

        try {
            URL flickr = new URL(buildRequestURL(tags));
            URLConnection yc = flickr.openConnection();
            in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = in.readLine()) != null) {
                response.append(line);
                response.append("\n");
            }

            in.close();
            JSONObject jsonObj = XML.toJSONObject(response.toString());
            return jsonObj.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String buildRequestURL(String[] tags) {
        StringBuffer params = new StringBuffer();

        for (int i=0; i<tags.length; i++) {
            params.append(tags[i]);
            if (i < tags.length-1)
                params.append(",");
        }

        return "https://api.flickr.com/services/feeds/photos_public.gne?tagmode=all&tags=" + params.toString();
    }
}
