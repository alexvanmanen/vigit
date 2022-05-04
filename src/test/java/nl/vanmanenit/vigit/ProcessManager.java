package nl.vanmanenit.vigit;

import java.io.File;
import java.io.IOException;

public class ProcessManager {
    public void execute(File directory, String... commands) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        processBuilder.directory(directory);
        Process gitInitProcess = processBuilder.start();
        gitInitProcess.waitFor();
    }

    public void execute(String... commands) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        Process gitInitProcess = processBuilder.start();
        gitInitProcess.waitFor();
    }
}
