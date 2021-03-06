package com.mamalimomen.base.controller.utilities;

public enum PersistenceUnit {
    UNIT_ONE("persistence-unit-one"),
    UNIT_TWO("persistence-unit-two");

    private final String unit;

    PersistenceUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return this.unit;
    }
}
