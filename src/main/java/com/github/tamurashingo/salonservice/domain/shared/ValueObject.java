package com.github.tamurashingo.salonservice.domain.shared;

public interface ValueObject<T> extends java.io.Serializable {

    boolean sameValueAs(T other);
}
