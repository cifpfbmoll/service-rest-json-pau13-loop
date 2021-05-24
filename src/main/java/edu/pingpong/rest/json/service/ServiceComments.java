package edu.pingpong.rest.json.service;

import java.util.Optional;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import edu.pingpong.rest.json.domain.Comments;
import edu.pingpong.rest.json.repository.RepoComments;

/**
 * When an instance is created lazy ? 
 * 
 * A single bean instance is used for the application and shared among all injection points.
 * 
 * QUARKUS TEHORY
 * This is a scope annotation. It tells the container which context to associate the bean instance with. In 
 *  this particular case, a single bean instance is created for the application and used by all other beans 
 *  that inject Translator.
 */
@ApplicationScoped
public class ServiceComments {
    
    /**
     * This is a field injection point. It tells the container that ServiceComments depends on the Comments 
     * bean. If there is no matching bean the build fails.
     */
    @Inject 
    RepoComments repo;

    public ServiceComments() {}

    public Set<Comments> list() {
        return repo.list();
    }

    public void add(Comments comment) {
        repo.add(comment);
    }

    public void remove(String name) {
        repo.remove(name);
    }

    // Add the needed logic in case the var is blank 
    public Optional<Comments> getComment(String name) {
        return name.isBlank()? Optional.ofNullable(null) : repo.get(name);
    }
}
