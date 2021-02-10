package g55130.mentoring.repository;

import java.io.IOException;
import java.util.List;

public interface Repository<T> {
    void add(T item) throws IOException;

    void remove(T item) throws IOException;

    T get(T item) throws IOException;

    List<T> getAll() throws IOException;

    boolean contains(T item) throws IOException;
}
