package com.example.osk.category;

import java.util.Set;
import java.util.stream.Collectors;

public enum CategoryType {
    AM("AM"),
    A1("A1"),
    A2("A2"),
    A("A"),
    B2("B2"),
    B("B"),
    BE("B+E"),
    C("C"),
    C1("C1"),
    C1E("C1+E"),
    CE("C+E"),
    D("D"),
    D1("D1"),
    D1E("D1+E"),
    DE("D+E"),
    T("T"),
    TRAMWAJ("Tramwaj");

    private String value;

    private CategoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CategoryType getCategoryTypeFromString(String categoryTypeString) {
        for (CategoryType type : CategoryType.values()) {
            if (type.getValue().equalsIgnoreCase(categoryTypeString)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown category type: " + categoryTypeString);
    }

    public static Set<CategoryType> getCategoriesFromString(Set<String> categoryTypeString) {
        return categoryTypeString.stream().map(CategoryType::getCategoryTypeFromString).collect(Collectors.toSet());
    }
}
