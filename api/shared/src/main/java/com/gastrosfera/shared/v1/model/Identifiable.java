package com.gastrosfera.shared.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Identifiable<T> {
    @JsonIgnore
    T getIdentifier();
}
