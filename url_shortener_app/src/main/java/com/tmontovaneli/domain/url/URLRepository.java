package com.tmontovaneli.domain.url;


import com.tmontovaneli.infrastructure.annotation.InMemory;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
@InMemory
class URLRepository implements IURLRepository {

    //URL => KEY
    private static Map<String, String> _CACHE_URL_KEY = new HashMap<>();

    //KEY => URL
    private static Map<String, String> _CACHE_KEY_URL = new HashMap<>();

    @Override
    public void save(URL url) {
        _CACHE_KEY_URL.put(url.key(), url.url());
        _CACHE_URL_KEY.put(url.url(), url.key());
    }

    @Override
    public Optional<URL> findByKey(String key) {
        System.out.println(_CACHE_KEY_URL);
        if (_CACHE_KEY_URL.containsKey(key)) {
            String url = _CACHE_KEY_URL.get(key);
            return Optional.of(URL.create(url, key));
        }
        return Optional.empty();
    }

    @Override
    public Optional<URL> findByURL(String url) {
        if (_CACHE_URL_KEY.containsKey(url)) {
            String key = _CACHE_URL_KEY.get(url);
            return Optional.of(URL.create(url, key));
        }
        return Optional.empty();
    }
}