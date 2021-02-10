package g55130.mentoring.repository;

import g55130.mentoring.config.ConfigManager;
import g55130.mentoring.dto.StudentDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDao implements Dao<StudentDto> {
    private final String path = getDataPath();

    public StudentDao() throws IOException {
    }

    private String getDataPath() throws IOException {
        try {
            ConfigManager.getInstance().load();
            return ConfigManager.getInstance().getProperties("file.url");

        } catch (IOException loadException) {
            throw new IOException("Cannot load config file : " + loadException.getMessage());
        }
    }

    @Override
    public void insert(StudentDto item) throws IOException {
        Files.write(Path.of(path), ("\n" + item.toString()).getBytes(),
                StandardOpenOption.APPEND);
    }

    @Override
    public void delete(StudentDto item) throws IOException {
        var out = Files.lines(Path.of(path))
                .filter(line -> !line.startsWith(item.getId() + ""))
                .collect(Collectors.toList());
        String asLine = String.join(System.lineSeparator(), out);
        Files.writeString(Path.of(path), asLine);
    }

    @Override
    public void update(StudentDto item) throws IOException {
        this.delete(item);
        this.insert(item);
    }

    @Override
    public StudentDto get(StudentDto item) throws IOException {
        var line
                = Files.lines(Path.of(path))
                .filter(s -> s.startsWith(item.getId() + "")).findFirst();
        if (line.isPresent()) {
            String foundLine = line.get();
            var dataList = Stream.of(foundLine.split(","))
                    .collect(Collectors.toList());
            return new StudentDto(Integer.parseInt(dataList.get(0)),
                    dataList.get(1), dataList.get(2));
        }
        return null;
    }

/*
    @Override
    public List<StudentDto> getAll(StudentDto item) throws IOException {
        var lines
                = Files.lines(Path.of(path))
                .filter(s -> s.contains(item.toString()))
                .collect(Collectors.toList());
        List<StudentDto> students = new ArrayList<>();
        for (String line :
                lines) {
            var dataList = Stream.of(line.split(","))
                    .collect(Collectors.toList());
            students.add(new StudentDto(Integer.parseInt(dataList.get(0)),
                    dataList.get(1), dataList.get(2)));
        }
        return students;
    }
*/

    @Override
    public List<StudentDto> getAll() throws IOException {
        var lines = Files.lines(Path.of(path))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
        List<StudentDto> students = new ArrayList<>();
        for (String line : lines) {
            var dataList = Stream
                    .of(line.split(","))
                    .collect(Collectors.toList());
            students.add(new StudentDto(
                    Integer.parseInt(dataList.get(0)),
                    dataList.get(1),
                    dataList.get(2)));
        }
        return students;
    }
}
