package g55130.mentoring;

import g55130.mentoring.dto.StudentDto;
import g55130.mentoring.repository.StudentRepository;

import java.io.IOException;

/**
 * @author Ihab Tazi 55130 - ESI.
 */

public class Validation {

    public static void main(String[] args) throws IOException {
        StudentRepository repo = new StudentRepository();
        System.out.println(repo.getAll());
        StudentDto item = new StudentDto(55130, "Ihab", "Tazi");
        repo.add(item);
        System.out.println(repo.getAll());
        StudentDto itemUpdated
                = new StudentDto(55130, "IhabUpdated",
                "TaziUpdated");
        repo.add(itemUpdated);
        System.out.println(repo.getAll());
        repo.remove(itemUpdated);
        System.out.println(repo.getAll());
    }
}
