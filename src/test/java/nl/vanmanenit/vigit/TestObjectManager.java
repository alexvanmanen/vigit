package nl.vanmanenit.vigit;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;

public class TestObjectManager {

    private static final String testGitRepo = "testGitRepoObjectManager";

    @BeforeAll
    public static void beforeAllCreateTestGitRepo() throws IOException, InterruptedException {
        new ProcessManager().execute("mkdir", testGitRepo);
        new ProcessManager().execute(new File(testGitRepo), "git", "init");
    }

    @Test
    @DisplayName("No objects exists after git init")
    public void noObjectsExist() {
        ObjectManager objectManager = new ObjectManager();
        assertTrue(objectManager.getObjects().isEmpty());
    }

//    @Test
//    @DisplayName("No objects exists after git init and new File")
//    public void noObjectsExistAfterNewFile() {
//        new ProcessManager().execute(new File(testGitRepo), "git", "init");
//        ObjectManager objectManager = new ObjectManager();
//        assertTrue(objectManager.getObjects().isEmpty());
//    }


    @AfterAll
    public static void AfterAllRemoveGitRepo() throws IOException, InterruptedException {
        new ProcessManager().execute("rm", "-rf", testGitRepo);
    }
}
