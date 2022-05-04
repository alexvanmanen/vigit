package nl.vanmanenit.vigit;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGit {

    private static final String testGitRepo = "testGitRepo";

    @BeforeAll
    public static void beforeAllCreateTestGitRepo() throws IOException, InterruptedException {
        new ProcessManager().execute("mkdir", testGitRepo);
        new ProcessManager().execute(new File(testGitRepo), "git", "init");
    }


    @Test
    @DisplayName("Test with correct value for Git repository, expected to succeed")
    public void testIsGitDirectoryTrue() {
        Git git = new Git();
        assertTrue(git.isGitDirectory(testGitRepo));
    }

    @Test
    @DisplayName("Test with null value for Git repository, expected to fail")
    public void testIsGitDirectoryNullFails() {
        Git git = new Git();
        assertFalse(git.isGitDirectory(null));
    }

    @Test
    @DisplayName("Test with empty string for Git repository, expected to fail")
    public void testIsGitDirectoryEmptyFails() {
        Git git = new Git();
        assertFalse(git.isGitDirectory(""));
    }

    @Test
    @DisplayName("Test with blank value for Git repository, expected to fail")
    public void testIsGitDirectoryContainsOnlyBlanksFails() {
        Git git = new Git();
        assertFalse(git.isGitDirectory("  "));
    }

    @Test
    @DisplayName("Test with none existing directory for Git repository, expected to fail")
    public void testIfGitDirectoryDoesNotExistFails() {
        Git git = new Git();
        assertFalse(git.isGitDirectory("not existing directory"));
    }

    @Test
    @DisplayName("Test with existing directory but without Git repository, expected to fail")
    public void testIfFailsWhenExistingDirectoryDoesNotHaveGitRepository() {
        Git git = new Git();
        assertFalse(git.isGitDirectory("src/test"));
    }

    @AfterAll
    public static void AfterAllRemoveGitRepo() throws IOException, InterruptedException {
        new ProcessManager().execute("rm", "-rf", testGitRepo);
    }
}
