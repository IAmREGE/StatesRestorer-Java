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

    public boolean isEmpty() {
        return this.main == null && this.addi == null;
    }

    public byte[] serialize(Object... args) {
        return new byte[0];
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BiShortData)) {
            return false;
        }
        BiShortData that = (BiShortData)o;
        return (this.main == null ? that.main == null :
                this.main.equals(that.main)) &&
               (this.addi == null ? that.addi == null :
                this.addi.equals(that.addi));
    }

    @Override
    public int hashCode() {
        int result = this.main != null ? this.main.hashCode() : 0;
        result = 31 * result + (this.addi != null ? this.addi.hashCode() : 0);
        return result;
    }
}
