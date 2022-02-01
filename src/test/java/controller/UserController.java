package controller;

import base.AbstractController;
import io.restassured.builder.RequestSpecBuilder;
import model.User;

import static io.restassured.RestAssured.given;

public class UserController extends AbstractController {

    private RequestSpecBuilder specBuilder = new RequestSpecBuilder()
            .addHeader("Content-type", "application/json; charset=UTF-8")
            .setBasePath("users");

    private User user;

    public UserController(User user) {
        this.user = user;
    }

    public User getElement() {
        user.setId(1);
        return given(specBuilder.build()).get(String.valueOf(user.getId())).as(User.class);
    }
}
