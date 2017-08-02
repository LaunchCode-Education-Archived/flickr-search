package org.launchcode.flickrsearch.api;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.test.TestInterface;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by LaunchCode
 */
@Service
public class FlickrService {

    private Flickr flickr;

    public FlickrService(@Value("${flickr.key}") String apiKey, @Value("${flickr.secret}") String apiSecret) throws ParserConfigurationException {
        flickr = new Flickr(apiKey, apiSecret, new REST());
        TestInterface testInterface = flickr.getTestInterface();
    }

    public Token generateRequestToken() {
        return flickr.getAuthInterface().getRequestToken("http://localhost:8080/flickr_callback");
    }

    public String getAuthUrl(Token token) {
        AuthInterface authInterface = flickr.getAuthInterface();
        return authInterface.getAuthorizationUrl(token, Permission.READ);
    }

    public Auth authorize(Token token, String tokenKey) throws FlickrException {
        Token requestToken = flickr.getAuthInterface().getAccessToken(token, new Verifier(tokenKey));
        return flickr.getAuthInterface().checkToken(requestToken);
    }

}
