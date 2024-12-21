package rege.rege.statesrestorer.util;

import java.util.HashMap;
import java.util.Map;

import rege.rege.statesrestorer.base.LeveledData;

public class StringMapData extends HashMap<String, String>
implements LeveledData<String, String> {
    public static StringMapData fromByteArr(byte... arr) {
        if (arr.length < 4) {
            return null;
        }
        final int LEN = (((arr[0] < 0) ? 256 + arr[0] : arr[0]) << 24) |
                        (((arr[1] < 0) ? 256 + arr[1] : arr[1]) << 16) |
                        (((arr[2] < 0) ? 256 + arr[2] : arr[2]) << 8) |
                        ((arr[3] < 0) ? 256 + arr[3] : arr[3]);
        final StringMapData RES = new StringMapData(LEN);
        try {
            int index = 4;
            for (int i = 0; i < LEN; i++) {
                int len =
                (((arr[index] < 0) ? 256 + arr[index] : arr[index]) << 24) |
                (((arr[index + 1] < 0) ? 256 + arr[index + 1] : arr[index + 1])
                 << 16) | (((arr[index + 2] < 0) ? 256 + arr[index + 2] :
                            arr[index + 2]) << 8) |
                ((arr[index + 3] < 0) ? 256 + arr[index + 3] : arr[index + 3]);
                index += 4;
                String key;
                if (len > 0) {
                    StringBuilder sb = new StringBuilder(len);
                    for (int j = 0; j < len; j++) {
                        sb.append((char)((arr[index] << 8) | arr[index + 1]));
                        index += 2;
                    }
                    key = sb.toString();
                } else {
                    key = "";
                }
                len =
                (((arr[index] < 0) ? 256 + arr[index] : arr[index]) << 24) |
                (((arr[index + 1] < 0) ? 256 + arr[index + 1] : arr[index + 1])
                 << 16) | (((arr[index + 2] < 0) ? 256 + arr[index + 2] :
                            arr[index + 2]) << 8) |
                ((arr[index + 3] < 0) ? 256 + arr[index + 3] : arr[index + 3]);
                index += 4;
                String val;
                if (len > 0) {
                    StringBuilder sb = new StringBuilder(len);
                    for (int j = 0; j < len; j++) {
                        sb.append((char)((arr[index] << 8) | arr[index + 1]));
                        index += 2;
                    }
                    val = sb.toString();
                } else {
                    val = "";
                }
                RES.put(key, val);
            }
            return RES;
        } catch (ArrayIndexOutOfBoundsException ignored) {}
        return null;
    }

    public StringMapData() {
        super();
    }

    public StringMapData(int levels) {
        super(levels);
    }

    public StringMapData(Map<String, String> construct) {
        super(construct);
    }

    public String getData(String entry) {
        return this.get(entry);
    }

    public byte[] serialize(Object... args) {
        final int LEN = this.size();
        int reslen = 4 + (LEN << 3);
        int index = 0;
        for (Entry<String, String> i : this.entrySet()) {
            reslen += i.getKey().length() << 1;
            reslen += i.getValue().length() << 1;
            index++;
        }
        final byte[] RES = new byte[reslen];
        RES[0] = (byte)(LEN >>> 24);
        RES[1] = (byte)(LEN >>> 16);
        RES[2] = (byte)(LEN >>> 8);
        RES[3] = (byte)LEN;
        index = 4;
        for (Entry<String, String> i : this.entrySet()) {
            String s = i.getKey();
            int len = s.length();
            RES[index] = (byte)(len >>> 24);
            RES[index + 1] = (byte)(len >>> 16);
            RES[index + 2] = (byte)(len >>> 8);
            RES[index + 3] = (byte)len;
            index += 4;
            for (int j = 0; j < len; j++) {
                char c = s.charAt(j);
                RES[index] = (byte)((int)c >>> 8);
                RES[index + 1] = (byte)(int)c;
                index += 2;
            }
            s = i.getValue();
            len = s.length();
            RES[index] = (byte)(len >>> 24);
            RES[index + 1] = (byte)(len >>> 16);
            RES[index + 2] = (byte)(len >>> 8);
            RES[index + 3] = (byte)len;
            index += 4;
            for (int j = 0; j < len; j++) {
                char c = s.charAt(j);
                RES[index] = (byte)((int)c >>> 8);
                RES[index + 1] = (byte)(int)c;
                index += 2;
            }
        }
        return RES;
    }
}
