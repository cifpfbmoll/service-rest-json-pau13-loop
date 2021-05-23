package edu.pingpong.rest.json.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Annotation that can be used to define ordering (possibly partial) to use when
 * serializing object properties. Properties included in annotation declaration
 * will be serialized first (in defined order), followed by any properties not
 * included in the definition.
 */
@JsonPropertyOrder({ "name", "comment" })
public class Comments {

    // ATENCIÓN
    // los propiedades han de ser publicas para que jackson
    // pueda acceder a ellar por reflection
    @NotBlank
    public String name;

    @NotEmpty
    public String description;

    public Comments() {
    }

    public Comments(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
