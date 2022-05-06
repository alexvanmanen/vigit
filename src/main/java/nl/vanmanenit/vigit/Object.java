package nl.vanmanenit.vigit;

import java.nio.file.Path;

public class Object {

    private final Path path;
    public Object(Path path) {
        this.path = path;
    }
    public Path getPath() {
        return path;
    }
}
