package rege.rege.statesrestorer.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

import rege.rege.statesrestorer.impl.Vec3IntToBiShortDataKeeper;
import rege.rege.statesrestorer.impl.Vec3IntToStringMapDataKeeper;
import rege.rege.statesrestorer.util.BiShortData;
import rege.rege.statesrestorer.util.StringMapData;
import rege.rege.statesrestorer.util.Vec3Int;

/**
 * @author REGE
 * @since 0.0.1-a.1
 */
public class Test1 {
    public static final Logger LOGGER =
    Logger.getLogger(Test1.class.getName());

    /**
     * <p>Run the test code.</p>
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        {
            Vec3IntToBiShortDataKeeper keeper =
            new Vec3IntToBiShortDataKeeper();
            keeper.claim(new Vec3Int(0, 0, 0)).withMain((short)1);
            keeper.claim(new Vec3Int(0, 0, 1)).withAddi((short)2);
            keeper.claim(new Vec3Int(0, 1, 0)).withMain((short)3)
            .withAddi((short)4);
            keeper.claim(new Vec3Int(0, 1, 1));
            LOGGER.info(keeper.toString());
            HashMap<Vec3Int, BiShortData> map =
            new HashMap<Vec3Int, BiShortData>();
            map.put(new Vec3Int(0, 0, 0), new BiShortData((short)5, (short)3));
            LOGGER.info(keeper.restore(map).toString());
            LOGGER.info(BiShortData.fromByteArr(
                new BiShortData(null, null).serialize()).toString()
            );
        }
        for (short i = 0; i < 140; i++) {
            BiShortData data = new BiShortData(i, null);
            if (!data.equals(BiShortData.fromByteArr(data.serialize()))) {
                LOGGER.severe("Deserialize unmatched: " + data);
            }
            for (short j = 0; j < (i >> ((i & 3) + 4)); j++) {
                data = new BiShortData(i, j);
                if (!data.equals(BiShortData.fromByteArr(data.serialize()))) {
                    LOGGER.severe("Deserialize unmatched: " + data);
                }
            }
        }
        for (short i = 0; i < 16; i++) {
            BiShortData data = new BiShortData(null, i);
            if (!data.equals(BiShortData.fromByteArr(data.serialize()))) {
                LOGGER.severe("Deserialize unmatched: " + data);
            }
        }
        {
            Vec3IntToStringMapDataKeeper keeper =
            new Vec3IntToStringMapDataKeeper();
            keeper.claim(new Vec3Int(0, 0, 0)).with("", "air");
            keeper.claim(new Vec3Int(0, 0, 1)).with("axis", "x");
            keeper.claim(new Vec3Int(0, 1, 0)).with("color", "red")
            .with("level", "0");
            keeper.claim(new Vec3Int(0, 1, 1));
            keeper.claim(new Vec3Int(1, 0, 0)).with("", "water")
            .with("level", "7");
            LOGGER.info(keeper.toString());
            HashMap<Vec3Int, StringMapData> map =
            new HashMap<Vec3Int, StringMapData>();
            map.put(new Vec3Int(0, 0, 0),
                    new StringMapData().with("", "portal").with("axis", "z"));
            map.put(new Vec3Int(0, 0, 1), new StringMapData().with("", "log"));
            LOGGER.info(keeper.restore(map).toString());
        }
    }
}
