package com.library.facade.converter;

import java.util.Collection;

public interface Converter<SOURCE, TARGET> {

    TARGET convert(SOURCE source);

    Collection<TARGET> convertAll(Collection<? extends SOURCE> sourceCollection);

}
