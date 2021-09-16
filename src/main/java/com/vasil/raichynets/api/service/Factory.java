package com.vasil.raichynets.api.service;

/**
 * @author Vasil Raichynets
 */
public interface Factory<T, R extends Factory<T, R>> {
    T create();
}
