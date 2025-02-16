package com.storelabs.mygage.estimate.converter;

import com.storelabs.mygage.estimate.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class CategoryListConverter implements AttributeConverter<List<Category>, String> {
    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            return null;
        }
        return categories.stream()
                .map(Enum::name)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<Category> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return List.of();
        }
        return Arrays.stream(dbData.split(DELIMITER))
                .map(Category::valueOf)
                .collect(Collectors.toList());
    }
}