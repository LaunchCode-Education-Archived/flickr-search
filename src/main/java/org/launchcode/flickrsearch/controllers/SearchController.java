package org.launchcode.flickrsearch.controllers;

import org.launchcode.flickrsearch.models.FlickrConnector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by LaunchCode
 */
@Controller
public class SearchController {

    @RequestMapping(value = "")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "get_flickr_data")
    @ResponseBody
    public String getFlickrData(String[] tags){
        return FlickrConnector.fetchImageJson(tags);
    }
}
