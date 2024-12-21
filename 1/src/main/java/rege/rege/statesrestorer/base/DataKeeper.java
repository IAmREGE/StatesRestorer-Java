package rege.rege.statesrestorer.base;

import java.util.Map;

/**
 * @param <S> The source type of the keeper.
 * @param <T> The target type of the keeper.
 * @author REGE
 * @since 0.0.1-a.1
 */
public interface
DataKeeper<S extends Serializable, T extends LeveledData<?, ?>> {
    T claim(S source);

    T unclaim(S source);

    T access(S source);

    Map<S, T> restore(Map<S, T> map);
}
