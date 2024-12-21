package rege.rege.statesrestorer.base;

import java.util.Map;

public interface
DataKeeper<S extends Serializable, T extends LeveledData<?, ?>> {
    T claim(S source);

    T unclaim(S source);

    T access(S source);

    Map<S, T> restore(Map<S, T> map);
}
