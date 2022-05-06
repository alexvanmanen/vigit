package nl.vanmanenit.vigit;


import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;

public class TestObjectManager {

    private static final String testGitRepo = "testGitRepoObjectManager";

    @BeforeEach
    public void beforeEachCreateTestGitRepo() throws IOException, InterruptedException {
        new ProcessManager().execute("mkdir", testGitRepo);
        new ProcessManager().execute(new File(testGitRepo), "git", "init");
    }

    @Test
    @DisplayName("No objects exists after git init")
    public void TestNoObjectsExistAfterInitializingGitRepository() throws IOException {
        ObjectManager objectManager = new ObjectManager(testGitRepo);
        assertTrue(objectManager.getObjects().isEmpty());
    }

    @Test
    @DisplayName("No objects exists after git init and new File added to workingDirecotry")
    public void testNoObjectsExistAfterNewFileIsCreated() throws IOException {
        boolean success = new File(testGitRepo + "/file.txt").createNewFile();
        assertTrue(success);
        assertTrue(new ObjectManager(testGitRepo).getObjects().isEmpty());
    }

    @Test
    @DisplayName("One object exists after git init and new File added wit Git Add")
    public void testObjectsExistAfterNewFileIsCreated() throws IOException, InterruptedException {
        boolean success = new File(testGitRepo + "/file2.txt").createNewFile();
        assertTrue(success);
        new ProcessManager().execute(new File(testGitRepo), "git", "add", "file2.txt");
        assertEquals(1, new ObjectManager(testGitRepo).getObjects().size());
        assertNotNull(new ObjectManager(testGitRepo).getObjects().get(0).getPath());
    }

    @Test
    @DisplayName("Only one object exists after git init and two files added with the same content using Git Add")
    public void testOnlyOneObjectExistAfterTwoExactSameFilesAreCreated() throws IOException, InterruptedException {
        boolean isFileAddedWithSuccess = new File(testGitRepo + "/file.txt").createNewFile();
        assertTrue(isFileAddedWithSuccess);
        boolean isFile2AddedWithSuccess = new File(testGitRepo + "/file2.txt").createNewFile();
        assertTrue(isFile2AddedWithSuccess);
        new ProcessManager().execute(new File(testGitRepo), "git", "add", ".");
        assertEquals(1, new ObjectManager(testGitRepo).getObjects().size());
        assertNotNull(new ObjectManager(testGitRepo).getObjects().get(0).getPath());
    }

    @Test
    @DisplayName("Two object exists after git init and two files added with the different content using Git Add")
    public void testTwoObjectsExistAfterNewFileIsCreated() throws IOException, InterruptedException {
        createFileWithContent(testGitRepo + "/file1.txt", "Some text");
        createFileWithContent(testGitRepo + "/file2.txt", "Extra text");

        new ProcessManager().execute(new File(testGitRepo), "git", "add", ".");
        List<Object> objects = new ObjectManager(testGitRepo).getObjects();
        assertEquals(2, objects.size());
        assertNotNull(objects.get(0).getPath());
        assertNotNull(objects.get(1).getPath());
    }

    private void createFileWithContent(String fileName, String content) throws IOException {
        Path file = Paths.get(fileName);
        Files.write(file, List.of(content), StandardCharsets.UTF_8);
    }

    @AfterEach
    public void AfterEachRemoveGitRepo() throws IOException, InterruptedException {
        new ProcessManager().execute("rm", "-rf", testGitRepo);
    }
}
