package com.tmontovaneli.infrastructure;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.tmontovaneli.api.UrlApi;
import io.quarkus.vertx.web.Route;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@ApplicationScoped
public class Routes {

    @Inject
    private UrlApi urlApi;

    @Route(path = "/url/shorten", methods = HttpMethod.GET)
    public void shorten(RoutingContext rc) {
        rc.response().end(urlApi.shorten(rc.request().getParam("url")));
    }

    @Route(path = "/url/original", methods = HttpMethod.GET)
    public void originalUrl(RoutingContext rc) {
        rc.response().end(urlApi.originalUrl(rc.request().getParam("url")));
    }
}
