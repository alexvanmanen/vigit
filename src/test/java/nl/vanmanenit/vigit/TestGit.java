package nl.vanmanenit.vigit;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGit {


    @Test
    public void testIsGitDirectoryTrue() {
        Git git = new Git();
        assertTrue(git.isGitDirectory("./example_git_repo"));
    }

}
