package com.tmontovaneli.domain.url;

import com.tmontovaneli.domain.url.generator.IGenerateIdentity;
import com.tmontovaneli.infrastructure.annotation.Dynamo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.function.Supplier;

@ApplicationScoped
public class URLService {

    public static int _THRESHOLD = 5;

    String _DOMAIN = "http://bit.ly/";

    @Inject
    @Dynamo
    private IURLRepository urlRepository;

    @Inject
    private IGenerateIdentity generateIdentity;

    public URLService(IURLRepository urlRepository, IGenerateIdentity generateIdentity) {
        this.urlRepository = urlRepository;
        this.generateIdentity = generateIdentity;
    }

    public String shorten(String url) {
        Supplier<URL> supplier = () -> {
            String id = generateIdentity.generateId(url);
            Optional<URL> byKey = urlRepository.findByKey(id);

            int count = 1;
            while (byKey.isPresent()) {
                byKey = urlRepository.findByKey(id);
                count++;

                if (count > _THRESHOLD) {
                    throw new IllegalArgumentException("");
                }
            }

            URL u = URL.create(url, id);
            urlRepository.save(u);
            return u;
        };

        return String.format("%s%s", _DOMAIN, urlRepository.findByURL(url).orElseGet(supplier).key());
    }

    public String getOriginalUrl(String shortenedURL) {
        String key = shortenedURL.substring(_DOMAIN.length());
        System.out.println(key);
        Optional<URL> byKey = urlRepository.findByKey(key);
        if (!byKey.isPresent())
            throw new RuntimeException("Not found");
        return byKey.get().url();
    }

}
