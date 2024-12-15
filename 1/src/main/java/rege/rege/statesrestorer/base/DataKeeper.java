package rege.rege.statesrestorer.base;

public interface
DataKeeper<S extends Serializable, T extends LeveledData<?, ?>> {
    T claim(S source);

    T unclaim(S source);

    T access(S source);
}
