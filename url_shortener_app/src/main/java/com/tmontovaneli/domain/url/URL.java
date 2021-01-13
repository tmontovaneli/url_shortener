package com.tmontovaneli.domain.url;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class URL {

    public abstract String url();

    public abstract String key();

    public static URL create(String url, String key) {
        return builder()
                .url(url)
                .key(key)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_URL.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder url(String url);

        public abstract Builder key(String key);

        public abstract URL build();
    }
}
