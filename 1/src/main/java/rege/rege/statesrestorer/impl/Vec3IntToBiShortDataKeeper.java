package rege.rege.statesrestorer.impl;

import java.util.HashMap;
import java.util.Map;

import rege.rege.statesrestorer.base.DataKeeper;
import rege.rege.statesrestorer.util.BiShortData;
import rege.rege.statesrestorer.util.Vec3Int;

public class Vec3IntToBiShortDataKeeper extends HashMap<Vec3Int, BiShortData>
implements DataKeeper<Vec3Int, BiShortData> {
    public BiShortData claim(Vec3Int source) {
        return this.put(source, new BiShortData(null, null));
    }

    public BiShortData unclaim(Vec3Int source) {
        return this.remove(source);
    }

    public BiShortData access(Vec3Int source) {
        return this.get(source);
    }

    public Map<Vec3Int, BiShortData> restore(Map<Vec3Int, BiShortData> map) {
        final HashMap<Vec3Int, BiShortData> RES =
        new HashMap<Vec3Int, BiShortData>(map);
        for (Entry<Vec3Int, BiShortData> i : this.entrySet()) {
            Vec3Int vec = i.getKey();
            BiShortData data = i.getValue();
            if (RES.containsKey(vec) && !data.isEmpty()) {
                BiShortData restored = RES.get(vec);
                if (data.main != null) {
                    restored.main = data.main;
                }
                if (data.addi != null) {
                    restored.addi = data.addi;
                }
            } else {
                RES.put(vec, new BiShortData(data.main, data.addi));
            }
        }
        return RES;
    }
}
