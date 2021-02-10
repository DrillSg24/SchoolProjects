package g55130.mentoring.repository;

import java.io.IOException;
import java.util.List;

public interface Dao<T> {
    void insert(T item) throws IOException;

    void delete(T item) throws IOException;

    void update(T item) throws IOException;

    T get(T item) throws IOException;

    List<T> getAll() throws IOException;
}
