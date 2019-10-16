package com.github.tamurashingo.salonservice.domain.shared;

public interface Entity<T> {

    boolean sameIdentityAs(T other);
}
