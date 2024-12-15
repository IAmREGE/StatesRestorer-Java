package rege.rege.statesrestorer.base;

public interface LeveledData<P, V> extends Serializable {
    V getData(P entry);

    boolean isEmpty();
}
