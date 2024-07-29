package com.ecommerce.productservice.utils;

import java.util.Collection;
import java.util.stream.Stream;

public final class StreamUtils {
    public static <T> Stream<T> emptyIfNull(Collection<T> collection) {
        return collection == null ? Stream.empty() : collection.stream();
    }
}
