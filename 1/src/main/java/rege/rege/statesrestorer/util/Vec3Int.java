package rege.rege.statesrestorer.util;

import rege.rege.statesrestorer.base.Serializable;

/**
 * @author REGE
 * @since 0.0.1-a.1
 */
public class Vec3Int implements Serializable {
    public static Vec3Int fromByteArr(byte... arr) {
        if (arr.length < 12) {
            return null;
        }
        return new Vec3Int((((arr[0] < 0) ? 256 + arr[0] : arr[0]) << 24) |
                          (((arr[1] < 0) ? 256 + arr[1] : arr[1]) << 16) |
                          (((arr[2] < 0) ? 256 + arr[2] : arr[2]) << 8) |
                          ((arr[3] < 0) ? 256 + arr[3] : arr[3]),
                          (((arr[4] < 0) ? 256 + arr[4] : arr[4]) << 24) |
                          (((arr[5] < 0) ? 256 + arr[5] : arr[5]) << 16) |
                          (((arr[6] < 0) ? 256 + arr[6] : arr[6]) << 8) |
                          ((arr[7] < 0) ? 256 + arr[7] : arr[7]),
                          (((arr[8] < 0) ? 256 + arr[8] : arr[8]) << 24) |
                          (((arr[9] < 0) ? 256 + arr[9] : arr[9]) << 16) |
                          (((arr[10] < 0) ? 256 + arr[10] : arr[10]) << 8) |
                          ((arr[11] < 0) ? 256 + arr[11] : arr[11]));
    }

    public final int x;
    public final int y;
    public final int z;

    public Vec3Int(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public byte[] serialize(Object... args) {
        return new byte[]{
            (byte)(this.x >>> 24), (byte)(this.x >>> 16), (byte)(this.x >>> 8),
            (byte)this.x, (byte)(this.y >>> 24), (byte)(this.y >>> 16),
            (byte)(this.y >>> 8), (byte)this.y, (byte)(this.z >>> 24),
            (byte)(this.z >>> 16), (byte)(this.z >>> 8), (byte)this.z
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vec3Int)) {
            return false;
        }
        Vec3Int that = (Vec3Int)o;
        return this.x == that.x && this.y == that.y && this.z == that.z;
    }

    @Override
    public int hashCode() {
        int result = this.x;
        result = 31 * result + this.y;
        result = 31 * result + this.z;
        return result;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ')';
    }

    public int tryRead(byte[] arr, Object... args) {
        return -2;
    }
}
