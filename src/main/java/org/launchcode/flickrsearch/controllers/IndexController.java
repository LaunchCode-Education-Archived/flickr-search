package org.launchcode.flickrsearch.controllers;

import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import org.launchcode.flickrsearch.api.FlickrService;
import org.scribe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LaunchCode
 */
@Controller
public class IndexController {

    private static final String userTokenKey = "flickr.authtoken";
    private static final String requestTokenKey = "flickr.reqtoken";

    @Autowired
    private FlickrService flickrService;

    @RequestMapping(value = "")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "auth")
    public String auth(Model model, HttpServletRequest req){
        Token token = flickrService.generateRequestToken();
        setRequestToken(token, req);
        model.addAttribute("authUrl", flickrService.getAuthUrl(token));
        return "auth";
    }

    @RequestMapping(value = "flickr_callback")
    public String auth_callback(@RequestParam(name="oauth_verifier") String tokenKey,
                                HttpServletRequest req, Model model) {
        try {
            Auth flickrUser = flickrService.authorize(getRequestToken(req), tokenKey);
            setUserToken(flickrUser.getToken(), req);
            model.addAttribute("flickrUser", flickrUser);
        } catch (FlickrException e) {
            model.addAttribute("flickrUser", null);
        }
        return "auth";
    }

    private String getUserToken(HttpServletRequest req) {
        return req.getSession().getAttribute(userTokenKey).toString();
    }

    private void setUserToken(String token, HttpServletRequest req) {
        req.getSession().setAttribute(userTokenKey, token);
    }

    private Token getRequestToken(HttpServletRequest req) {
        return (Token) req.getSession().getAttribute(requestTokenKey);
    }

    private void setRequestToken(Token token, HttpServletRequest req) {
        req.getSession().setAttribute(requestTokenKey, token);
    }

}
