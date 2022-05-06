package nl.vanmanenit.vigit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ObjectManager {

    private final String gitRepoPath;
    public ObjectManager(String gitRepoPath) {
        this.gitRepoPath = gitRepoPath;
    }
    public List<Object> getObjects() throws IOException {
        List<Object> objects = new ArrayList<>();
        try (
                var paths = Files.walk(Paths.get(gitRepoPath + "/.git/objects"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> objects.add(new Object(path)));
        }

        return objects;
    }
}
