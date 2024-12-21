package rege.rege.statesrestorer.base;

/**
 * @param <P> Type of data's entry.
 * @param <V> Type of data's value.
 * @author REGE
 * @since 0.0.1-a.1
 */
public interface LeveledData<P, V> extends Serializable {
    V getData(P entry);

    boolean isEmpty();
}
