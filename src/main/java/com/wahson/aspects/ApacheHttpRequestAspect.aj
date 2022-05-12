package com.wahson.aspects;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by WahsonLeung on 2022/4/26
 */
public aspect ApacheHttpRequestAspect {

    pointcut httpRequest(HttpUriRequest request):
        call(* execute(HttpUriRequest)) && args(request);

    before(HttpUriRequest request): httpRequest(request) {
        System.out.println("before http request");
        String key = "X-Target-Env";
        InvocationContext.getContext().getAttribute(key).ifPresent(value ->
            request.setHeader(key, (String) value)
        );
    }
}
