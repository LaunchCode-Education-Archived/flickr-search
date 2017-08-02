package org.launchcode.flickrsearch.controllers;

import org.launchcode.flickrsearch.models.FlickrConnector;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LaunchCode
 */
@RestController
@RequestMapping(value = "search")
public class SearchController {

    @RequestMapping(value = "tags/{tags}")
    public String getFlickrData(@PathVariable String[] tags){
        return FlickrConnector.fetchImageJson(tags);
    }

}
