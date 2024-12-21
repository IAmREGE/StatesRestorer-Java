package rege.rege.statesrestorer.util;

import rege.rege.statesrestorer.base.LeveledData;

/**
 * @author REGE
 * @since 0.0.1-a.1
 */
public class BiShortData implements LeveledData<Boolean, Short> {
    public static BiShortData fromByteArr(byte... arr) {
        if (arr.length == 0) {
            return null;
        }
        switch (arr[0] & 3) {
            case 0: return new BiShortData(null, null);
            case 1: return (arr.length > 2) ? new BiShortData(null, (short)(
                (((arr[1] < 0) ? 256 + arr[1] : arr[1]) << 8) |
                ((arr[2] < 0) ? 256 + arr[2] : arr[2])
            )) : null;
            case 2: return (arr.length > 2) ? new BiShortData((short)(
                (((arr[1] < 0) ? 256 + arr[1] : arr[1]) << 8) |
                ((arr[2] < 0) ? 256 + arr[2] : arr[2])
            ), null) : null;
        }
        return (arr.length > 4) ? new BiShortData((short)(
            (((arr[1] < 0) ? 256 + arr[1] : arr[1]) << 8) |
            ((arr[2] < 0) ? 256 + arr[2] : arr[2])
        ), (short)((((arr[3] < 0) ? 256 + arr[3] : arr[3]) << 8) |
                   ((arr[4] < 0) ? 256 + arr[4] : arr[4]))) : null;
    }

    public Short main;
    public Short addi;

    public BiShortData(Short main, Short addi) {
        this.withMain(main);
        this.withAddi(addi);
    }

    public Short getData(Boolean isAddi) {
        return isAddi.booleanValue() ? this.addi : this.main;
    }

    public boolean isEmpty() {
        return this.main == null && this.addi == null;
    }

    public byte[] serialize(Object... args) {
        if (this.main == null) {
            if (this.addi == null) {
                return new byte[]{0};
            }
            final short A = this.addi.shortValue();
            return new byte[]{1, (byte)(A >>> 8), (byte)A};
        }
        final short B = this.main.shortValue();
        if (this.addi == null) {
            return new byte[]{2, (byte)(B >>> 8), (byte)B};
        }
        final short A = this.addi.shortValue();
        return new byte[]{3, (byte)(B >>> 8), (byte)B,(byte)(A >>> 8),(byte)A};
    }

    @Override
    public boolean equals(Object o) {
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

    @Override
    public String toString() {
        return (this.main == null) ?
               ((this.addi != null) ? ":" + this.addi : ":") :
               ((this.addi == null) ? this.main.toString() :
                this.main + ":" + this.addi);
    }

    public BiShortData withMain(Short main) {
        this.main = main;
        return this;
    }

    public BiShortData withAddi(Short addi) {
        this.addi = addi;
        return this;
    }

    public int tryRead(byte[] arr, Object... args) {
        if (arr.length == 0) {
            return -1;
        }
        switch (arr[0] & 3) {
            case 0: {
                this.withMain(null).withAddi(null);
                return 1;
            }
            case 1: {
                if (arr.length < 3) {
                    return -1;
                }
                this.withMain(null).withAddi((short)(
                    (((arr[1] < 0) ? 256 + arr[1] : arr[1]) << 8) |
                    ((arr[2] < 0) ? 256 + arr[2] : arr[2])
                ));
                return 3;
            }
            case 2: {
                if (arr.length < 3) {
                    return -1;
                }
                this.withMain((short)(
                    (((arr[1] < 0) ? 256 + arr[1] : arr[1]) << 8) |
                    ((arr[2] < 0) ? 256 + arr[2] : arr[2])
                )).withAddi(null);
                return 3;
            }
        }
        if (arr.length < 5) {
            return -1;
        }
        this.withMain((short)(
            (((arr[1] < 0) ? 256 + arr[1] : arr[1]) << 8) |
            ((arr[2] < 0) ? 256 + arr[2] : arr[2])
        )).withAddi((short)((((arr[3] < 0) ? 256 + arr[3] : arr[3]) << 8) |
                    ((arr[4] < 0) ? 256 + arr[4] : arr[4])));
        return 5;
    }
}
