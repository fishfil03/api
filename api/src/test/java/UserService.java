import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserService {
    static {
        RestAssured.baseURI = "https://reqres.in/api/users";
    }

    public static String getUserEmail(int id) {
        var response = getUser(id);
        return response.jsonPath().get("data.email");
    }

    public static ResponseBody getUser(int id) {
        return getResponseBody(String.valueOf(id));
    }

    public static ResponseBody getResponseBody(String relativeUrl) {
        return given()
                .when()
                .get(relativeUrl)
                .thenReturn()
                .getBody();
    }

    public static void delete(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }

        given()
                .when()
                .delete(String.valueOf(id))
                .then()
                .extract().response();
    }

    public static void add(String name, String job) {
        if (name == null || job == null || name.isEmpty() || job.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Map<String, Object> body = createBody(name, job);

        given().
                body(body).
                when().
                post();
    }

    public static void update(int id, String name, String job) {
        if (id <= 0 || name == null || job == null || name.isEmpty() || job.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Map<String, Object> body = createBody(name, job);

        given().
                body(body).
                when().
                put(String.valueOf(id));
    }

    private static Map<String, Object> createBody(String name, String job) {
        Map<String, Object> body = new HashMap<>();

        body.put("name", name);
        body.put("job", job);

        return body;
    }
}
