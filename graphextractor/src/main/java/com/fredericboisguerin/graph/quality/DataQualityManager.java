package com.fredericboisguerin.graph.quality;

@FunctionalInterface
public interface DataQualityManager<T> {

    T cleanData(T data);
}
