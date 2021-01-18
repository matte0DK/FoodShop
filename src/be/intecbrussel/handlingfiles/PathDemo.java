package be.intecbrussel.handlingfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
    public static void main(String[] args) throws IOException {

        Path pathToMyTextFile = Paths.get("resources/MyTextFile.txt");
        pathToMyTextFile.getFileName();
        pathToMyTextFile.toAbsolutePath();
        pathToMyTextFile.isAbsolute();
        pathToMyTextFile.getFileSystem();

        pathToMyTextFile.getParent().resolve("subfolder").resolve("subfile.txt");

        if (Files.notExists(Paths.get("resources/subfolder/jeanpierre"))) {
            Files.createDirectories(Paths.get("resources/subfolder/jeanpierre"));
        }

        Files.deleteIfExists(Paths.get("resources/subfolder/jeanpierre.txt"));

        if (Files.notExists(Paths.get("resources/subfolder/jeanpierre/jeanpatapouf.txt"))) {
            Files.createFile(Paths.get("resources/subfolder/jeanpierre/jeanpatapouf.txt"));
        }

        Path pathToWalk = Paths.get("resources");

        Files.walk(pathToWalk).forEach(path -> System.out.println(path.toString()));

        Files.lines(Paths.get("resources/MyTextFile.txt")).forEach(System.out::println);
    }
}
