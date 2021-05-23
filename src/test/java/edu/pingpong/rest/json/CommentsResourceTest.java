package edu.pingpong.rest.json;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

@QuarkusTest
public class CommentsResourceTest {

    @Test
    public void testHelloEndpoint() {
        // si el content-type de la peticion es TEXT
        // responde el endpoint hello
        given().contentType(ContentType.TEXT).when().get("/comments").then().statusCode(200)
                .body(is("Welcome to the comments Api"));
    }

    @Test
    public void testListEndpoint() {
        // Si el content-type de la peticion es JSON
        // responde el endpoint list
        // list() endpoint devuelve lista de maps [{}, {}]
        List<Map<String, Object>> products = given().contentType(ContentType.JSON).when().get("/comments")
                .as(new TypeRef<List<Map<String, Object>>>() {
                });

        Assertions.assertThat(products).hasSize(3);
        Assertions.assertThat(products.get(0)).containsValue("Pau");
        Assertions.assertThat(products.get(1)).containsEntry("description", "Roses are red and violets are blue");
    }

    @Test
    @TestTransaction
    public void testList() {
        given().contentType(ContentType.JSON).when().get("/comments/").then().statusCode(200).body("$.size()", is(3),
                "name", containsInAnyOrder("Pau", "Tyler", "Olivia"), "description",
                containsInAnyOrder("I'm so glad that I'm not in a relationship with you",
                        "Roses are red and violets are blue", "Lorem Ipsum"));
    }

    @Test
    public void testAdd() {
        given().body("{\"name\": \"Forrest\", \"description\": \"Run Forrest run !\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON).when().post("/comments").then().statusCode(200)
                .body("$.size()", is(4), "name", containsInAnyOrder("Pau", "Tyler", "Olivia", "Forrest"), "description",
                        containsInAnyOrder("Roses are red and violets are blue", "Lorem Ipsum",
                                "I'm so glad that I'm not in a relationship with you", "Run Forrest run !"));

        given().body("{\"name\": \"Adam\", \"description\": \"Bye Bye McAdam\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON).when().post("/comments").then().statusCode(200)
                .body("$.size()", is(5), "name", containsInAnyOrder("Pau", "Tyler", "Olivia", "Forrest", "Adam"),
                        "description",
                        containsInAnyOrder("Roses are red and violets are blue", "Lorem Ipsum",
                                "I'm so glad that I'm not in a relationship with you", "Run Forrest run !",
                                "Bye Bye McAdam"));
    }

    @Test
    public void getTest() {
        given()
            .pathParam("name", "Olivia")
        .when()
            .get("/comments/{name}")
        .then()
            .contentType(ContentType.JSON)
            .body("name", equalTo("Olivia"));

        // no fruit
        given()
            .pathParam("name", "Janis")
        .when()
            .get("/comments/{name}")
        .then()
            .statusCode(404);
    }
}
