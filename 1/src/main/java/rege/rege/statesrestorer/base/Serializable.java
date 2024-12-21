package rege.rege.statesrestorer.base;

/**
 * @author REGE
 * @since 0.0.1-a.1
 */
public interface Serializable {
    byte[] serialize(Object... args);

    /**
     * Read content from the byte array, and modify {@code this}.
     * @param arr The origin byte array.
     * @param args The custom varargs.
     * @return A non-negative {@code int} for the pointer for succeeded
     * reading, or {@code -1} for failed reading, or {@code -2} if the object
     * is designed read-only.
     */
    int tryRead(byte[] arr, Object... args);
}
