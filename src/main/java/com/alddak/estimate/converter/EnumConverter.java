package com.alddak.estimate.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EnumConverter<E extends Enum<E>> implements AttributeConverter<E,String> {
    private final Class<E> enumClass;

    public EnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, dbData);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
