package com.wahson.aspects;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by WahsonLeung on 2022/4/19
 */
public class InvocationContext {
    private static final ThreadLocal<InvocationContext> LOCAL = ThreadLocal.withInitial(InvocationContext::new);

    private final Map<String, Object> attributes = new HashMap<>();

    public static InvocationContext getContext() {
        return LOCAL.get();
    }

    public InvocationContext setAttribute(String key, Object value) {
        this.attributes.put(key, value);
        return this;
    }

    public InvocationContext setAttributes(Map<String, Object> attrs) {
        this.attributes.putAll(attrs);
        return this;
    }

    public Optional<Object> getAttribute(String key) {
        return Optional.ofNullable(this.attributes.get(key));
    }

    public void remove() {
        LOCAL.remove();
    }
}
