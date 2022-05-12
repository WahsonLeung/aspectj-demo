package com.wahson.aspects;

import okhttp3.Call;
import okhttp3.Request;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Optional;

/**
 * Created by WahsonLeung on 2022/4/28
 */
@Aspect
public class OkHttpAspect {
    @Pointcut("execution(* okhttp3.OkHttpClient.newCall(..))")
    public void okHttpRequest() {
    }

    @Around("okHttpRequest()")
    public Call doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("doAround");
        Request request = (Request) joinPoint.getArgs()[0];

        String key = "X-Target-Env";

        Optional<Object> attribute = InvocationContext.getContext().getAttribute(key);

        if (attribute.isPresent()) {
            String value = (String) attribute.get();
            request = request.newBuilder().addHeader(key, value).build();
        }

        return (Call) joinPoint.proceed(new Object[]{request});
    }
}
