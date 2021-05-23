package edu.pingpong.rest.json.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class RepoCommentsTest {
    
    @Inject
    RepoComments repo; 

    // Execute this method before each test case
    @BeforeEach
    public void setup() {
        repo.init();
    }

    // Execute this method after each test case
    @AfterEach 
    public void teardown() {
        repo.init();
    }

    @Test
    public void initTest() {
        assertEquals(3, repo.list().size());
        Assertions.assertThat(repo.list().size()).isEqualTo(3);
    }

    @Test
    public void containsTest() {
    assertTrue(repo.list().stream().anyMatch(c -> c.getName().equals("Pau")));
    }

    @Test
    public void removeTest() {
        repo.remove("Pau");
        assertEquals(2, repo.list().size());
        assertFalse(repo.list().stream().anyMatch(c -> c.getName().equals("Pau")));
    }

    @Test
    public void getFruitTest() {
        Assertions.assertThat(repo.get("Pau")).get().hasFieldOrPropertyWithValue("name", "Pau");
        Assertions.assertThat(repo.get("Zodous")).isEmpty();
    }
}
