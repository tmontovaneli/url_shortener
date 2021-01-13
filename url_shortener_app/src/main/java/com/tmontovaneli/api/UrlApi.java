package com.tmontovaneli.api;

import com.tmontovaneli.domain.url.URLService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UrlApi {

    @Inject
    private URLService urlService;

    public String shorten(String url) {
        return urlService.shorten(url);
    }

    public String originalUrl(String shortenedUrl){
        return urlService.getOriginalUrl(shortenedUrl);
    }
}
