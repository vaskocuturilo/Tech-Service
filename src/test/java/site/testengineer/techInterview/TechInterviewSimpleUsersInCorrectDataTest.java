package site.testengineer.techInterview;

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

public class TechInterviewSimpleUsersInCorrectDataTest {

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
        given().spec(spec)
                .when()
                .get("/2")
                .then()
                .assertThat()
                .statusCode(500);
    }

    @Test
    @Description("This is automation script for check timing")
    public void testTechInterviewCheckRequestTime() {
        given().spec(spec)
                .when()
                .get("/1")
                .then()
                .assertThat()
                .time(lessThanOrEqualTo(5000L), TimeUnit.MILLISECONDS);
    }

    @Test
    @Description("This is automation script for check content type")
    public void testTechInterviewCheckContentType() {
        given().spec(spec)
                .when()
                .get("/1")
                .then()
                .assertThat()
                .contentType(not(ContentType.HTML));
    }

    @Test
    @Description("This is automation script for check body with array")
    public void testTechInterviewCheckBody() {
        Map<String, Object> expected = new HashMap<String, Object>();
        expected.put("id", 2);
        expected.put("title1", "This is test title1");
        expected.put("description1", "This is Test exercises1");
        expected.put("completed", true);
        given().spec(spec)
                .when()
                .get("/1")
                .then()
                .assertThat()
                .body("username", not(equalTo("Test1")))
                .body("exercisesList", not(Matchers.hasItem(expected)));
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
        assertThat(user.getUsername()).isNotEqualTo("Test");
        assertThat(user.getExercisesList().stream().findFirst().get().getTitle()).isNotEqualTo("This");
        assertThat(user.getExercisesList().stream().findFirst().get().getDescription()).isNotEqualTo("This");
        assertThat(user.getExercisesList().stream().findFirst().get().isCompleted()).isNotEqualTo(false);
    }
}
