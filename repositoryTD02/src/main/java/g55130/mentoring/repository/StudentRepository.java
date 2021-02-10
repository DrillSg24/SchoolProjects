package g55130.mentoring.repository;

import g55130.mentoring.dto.StudentDto;

import java.io.IOException;
import java.util.List;

public class StudentRepository implements Repository<StudentDto> {
    private final StudentDao dao = new StudentDao();

    public StudentRepository() throws IOException {
    }


    @Override
    public void add(StudentDto item) throws IOException {
        if (contains(item)){
            dao.update(item);}
        else
            dao.insert(item);
    }

    @Override
    public void remove(StudentDto item) throws IOException {
        if (!contains(item)) throw new IllegalArgumentException("Item is not " +
                "present");
        dao.delete(item);
    }

    @Override
    public StudentDto get(StudentDto item) throws IOException {
        if (!contains(item)) throw new IllegalArgumentException("Item is not " +
                "present");
        return dao.get(item);
    }

    @Override
    public List<StudentDto> getAll() throws IOException {
        return dao.getAll();
    }

    @Override
    public boolean contains(StudentDto item) throws IOException {
        return dao.get(item) != null;
    }
}
