import g55130.mentoring.config.ConfigManager;
import g55130.mentoring.dto.StudentDto;
import g55130.mentoring.repository.StudentRepository;

import java.io.File;
import java.io.IOException;

public class Mentoring {
    public Mentoring() {
    }

    public static void main(String[] args) throws IOException {
        Mentoring mentoring = new Mentoring();
        mentoring.checkPath();
        getAuthor();
        getDataPath();
        StudentRepository repo = new StudentRepository();

    }

    private static void getAuthor() {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException ex) {
            System.out.println("Chargement de la configuration impossible"
                    + ex.getMessage());
        }
        String author = ConfigManager
                .getInstance().getProperties("app.author");
        System.out.println("Auteur : " + author);
    }

    private static void getDataPath() {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException ex) {
            System.out.println("Chargement de la configuration impossible"
                    + ex.getMessage());
        }
        String dataPath = ConfigManager.getInstance()
                .getProperties("file.url");
        System.out.println("Le fichier subscribed se trouve ici : " + dataPath);
    }

    public void checkPath() {
        System.out.println("Chemin Courant \t"
                + new File(".").getAbsolutePath());
        System.out.println("Chemin classe \t"
                + this.getClass().getClassLoader().getResource(".").getPath());
        System.out.println("Chemin jar \t"
                + new File(getClass().getClassLoader().getResource(".").getFile()));
    }
}
