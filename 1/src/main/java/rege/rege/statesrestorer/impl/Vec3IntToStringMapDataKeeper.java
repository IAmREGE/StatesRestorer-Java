package rege.rege.statesrestorer.impl;

import java.util.HashMap;
import java.util.Map;

import rege.rege.statesrestorer.base.DataKeeper;
import rege.rege.statesrestorer.util.StringMapData;
import rege.rege.statesrestorer.util.Vec3Int;

/**
 * @author REGE
 * @since 0.0.1-a.1
 */
public class Vec3IntToStringMapDataKeeper
extends HashMap<Vec3Int, StringMapData>
implements DataKeeper<Vec3Int, StringMapData> {
    public StringMapData claim(Vec3Int source) {
        StringMapData data = new StringMapData();
        this.put(source, data);
        return data;
    }

    public StringMapData unclaim(Vec3Int source) {
        return this.remove(source);
    }

    public StringMapData access(Vec3Int source) {
        return this.get(source);
    }

    public Map<Vec3Int, StringMapData> restore(Map<Vec3Int, StringMapData> map) {
        final HashMap<Vec3Int, StringMapData> RES =
        new HashMap<Vec3Int, StringMapData>(map);
        for (Entry<Vec3Int, StringMapData> i : this.entrySet()) {
            Vec3Int vec = i.getKey();
            StringMapData data = i.getValue();
            if (!data.isEmpty()) {
                if (RES.containsKey(vec)) {
                    StringMapData restored = RES.get(vec);
                    restored.putAll(data);
                } else {
                    RES.put(vec, new StringMapData(data));
                }
            }
        }
        return RES;
    }
}
