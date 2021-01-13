package com.tmontovaneli.domain.url;

import java.util.Optional;

public interface IURLRepository {

    void save(URL url);

    Optional<URL> findByKey(String key);

    Optional<URL> findByURL(String url);

}