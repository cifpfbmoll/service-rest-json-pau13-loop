package edu.pingpong.rest.json.repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import edu.pingpong.rest.json.domain.Comments;

@ApplicationScoped
public class RepoComments {
    
    // How to read this line ?
    private Set<Comments> comments = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public RepoComments() {}

    @PostConstruct
    public void init() {
        // The first thing to do is always clear the Set in case the test cases could have changed
        comments.clear();
        comments.add(new Comments("Pau", "Lorem Ipsum"));
        comments.add(new Comments("Olivia", "Roses are red and violets are blue"));
        comments.add(new Comments("Tyler", "I'm so glad that I'm not in a relationship with you"));
    }

    public Set<Comments> list() {
        return this.comments;
    }

    public void add(Comments comment) {
        comments.add(comment);
    }

    // Maybe the comment we are trying to delete is not included in the list, for this rule is a good practice to use "removeIf" instead of a if-else chain
    public void remove(String name) {
        comments.removeIf(realComment -> realComment.getName().contentEquals(name));
    }

    // equalsIgnoreCase() --> method compares two strings, ignoring lower case and upper case differences. This method returns true if the strings are equal, and false if not.
    public Optional<Comments> get(String name) {
        return this.comments.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();
    }
}
