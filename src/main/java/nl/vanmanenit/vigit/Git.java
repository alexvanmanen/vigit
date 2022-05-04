package nl.vanmanenit.vigit;

import java.nio.file.Files;
import java.nio.file.Path;

public class Git {


    public boolean isGitDirectory(String directory) {
        if(directory == null || directory.isBlank()) {
            return false;
        }
        return Files.isDirectory(Path.of(directory + "/.git"));
    }
}
