package rege.rege.statesrestorer.util;

import rege.rege.statesrestorer.base.LeveledData;

public class BiShortData implements LeveledData<Boolean, Short> {
    public Short main;
    public Short addi;

    public BiShortData(Short main, Short addi) {
        this.main = main;
        this.addi = addi;
    }

    public Short getData(Boolean isAddi) {
        return isAddi.booleanValue() ? this.addi : this.main;
    }

    public byte[] serialize(Object... args) {
        return new byte[0];
    }
}
