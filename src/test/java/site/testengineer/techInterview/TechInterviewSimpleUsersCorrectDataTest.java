package site.testengineer.techInterview;

import controller.UserController;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.Description;
import model.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class TechInterviewSimpleUsersCorrectDataTest {

    private static RequestSpecification spec;

    @BeforeEach
    public void initSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080")
                .setBasePath("/users")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    @Description("This is automation script for check status code")
    public void testTechInterviewCheckPositiveStatusCode() {
        User user = new User();
        UserController userController = new UserController(user);
        userController.getElement();
    }

    @Test
    @Description("This is automation script for check timing")
    public void testTechInterviewCheckRequestTime() {
        given().spec(spec)
                .when()
                .get("/1")
                .then()
                .assertThat()
                .time(lessThan(1000L), TimeUnit.MILLISECONDS);
    }

    @Test
    @Description("This is automation script for check content type")
    public void testTechInterviewCheckContentType() {
        given().spec(spec)
                .when()
                .get("/1")
                .then()
                .assertThat()
                .contentType(ContentType.JSON);
    }

    @Test
    @Description("This is automation script for check body with array")
    public void testTechInterviewCheckBody() {
        Map<String, Object> expected = new HashMap<String, Object>();
        expected.put("id", 1);
        expected.put("title", "This is test title");
        expected.put("description", "This is Test exercises");
        expected.put("completed", false);
        given().spec(spec)
                .when()
                .get("/1")
                .then()
                .assertThat()
                .body("username", equalTo("Test"))
                .body("exercisesList", Matchers.hasItem(expected));
    }

    @Test
    @Description("This is automation script for check body with model")
    public void testTechInterviewCheckBodyModel() {
        User user = given().spec(spec)
                .when()
                .get("/1")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().as(User.class);

        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getUsername()).isEqualTo("Test");
        assertThat(user.getExercisesList().stream().findFirst().get().getTitle()).isEqualTo("This is test title");
        assertThat(user.getExercisesList().stream().findFirst().get().getDescription()).isEqualTo("This is Test exercises");
        assertThat(user.getExercisesList().stream().findFirst().get().isCompleted()).isEqualTo(false);
    }
}
