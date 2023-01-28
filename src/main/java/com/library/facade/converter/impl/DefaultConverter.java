package com.library.facade.converter.impl;

import com.library.core.exception.ConverterException;
import com.library.facade.converter.Converter;
import com.library.facade.populator.Populator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class DefaultConverter<SOURCE, TARGET> implements Converter<SOURCE, TARGET> {

    private Class<TARGET> targetClass;
    private List<Populator<SOURCE, TARGET>> populatorsList;

    @Override
    public TARGET convert(SOURCE source) {
        final TARGET target = getTargetDto();
        if (populatorsList.isEmpty()) {
            return target;
        }
        populatorsList.forEach(populator -> populator.populate(source, target));
        return target;
    }

    @Override
    public Collection<TARGET> convertAll(Collection<? extends SOURCE> sourceCollection) {
        if (sourceCollection.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceCollection.stream().map(this::convert).collect(Collectors.toList());
    }

    private TARGET getTargetDto() {
        try {
            return getTargetClass().getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new ConverterException("get instance exception", getTargetClass());
        }
    }
}
