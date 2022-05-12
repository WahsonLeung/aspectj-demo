package com.wahson.aspects;

import okhttp3.Call;
import okhttp3.Request;

import java.util.Optional;

/**
 * Created by WahsonLeung on 2022/4/26
 */
public aspect OkHttpRequestAspect {

    pointcut okHttpRequest(Request request):
        call(Call newCall(Request)) && args(request);

    before(Request request): okHttpRequest(request) {
        System.out.println("before okhttp request");
    }

    Call around(Request request):
        okHttpRequest(request) {
        System.out.println("before request");
        String key = "X-Target-Env";

//        Optional<Object> attribute = InvocationContext.getContext().getAttribute(key);
//
//        if (attribute.isPresent()) {
//            String value = (String) attribute.get();
//            request = request.newBuilder().addHeader(key, value).build();
//        }

        request = request.newBuilder().addHeader(key, "env-003").build();

        System.out.println(request);
        return proceed(request);
    }
}
