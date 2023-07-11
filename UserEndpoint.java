package com.softserve.taf.services.placeholder.endpoints;

import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.user.UserDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class UserEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USERS_END = "/users";
    private static final String USERS_RESOURCE_END = "/users/{userID}";

    public UserEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Create a user
     *
     * @param userDto {@link @UserDto}
     * @return
     */
    public UserDto create(UserDto userDto) {
        return create(userDto, HttpStatus.CREATED)
                .extract().as(UserDto.class);
    }

    /**
     * Create a user with specific status code
     *
     * @param userDto {@link UserDto}
     * @param status  {@link HttpStatus}
     * @return {@link ValidatableResponse}
     */
    public ValidatableResponse create(UserDto userDto, HttpStatus status) {
        LOGGER.info("Create new User");
        return post(
                this.specification,
                USERS_END,
                userDto)
                .statusCode(status.getCode());
    }

    /**
     * Update user
     *
     * @param id      integer
     * @param userDto {@link UserDto}
     * @return updated {@link UserDto}
     */
    public UserDto update(int id, UserDto userDto) {
        return update(userDto, id, HttpStatus.OK)
                .extract().as(UserDto.class);
    }

    /**
     * Update user with specific status code
     *
     * @param userDto {@link UserDto}
     * @param id      integer
     * @param status  {@link HttpStatus}
     * @return {@link ValidatableResponse}
     */
    public ValidatableResponse update(UserDto userDto, int id, HttpStatus status) {
        LOGGER.info("Update User by id [{}]", id);
        return put(
                this.specification,
                USERS_RESOURCE_END,
                userDto,
                id)
                .statusCode(status.getCode());
    }

    /**
     * Get user by id
     *
     * @param id String
     * @return {@link UserDto}
     */
    public UserDto getById(String id) {
        return getById(id, HttpStatus.OK)
                .extract().as(UserDto.class);
    }

    /**
     * Get user by id with specific status code
     *
     * @param id     String
     * @param status {@link HttpStatus}
     * @return {@link ValidatableResponse}
     */
    public ValidatableResponse getById(String id, HttpStatus status) {
        LOGGER.info("Get User by id [{}]", id);
        return get(
                this.specification,
                USERS_RESOURCE_END,
                id)
                .statusCode(status.getCode());
    }

    /**
     * Get all users
     *
     * @return List of {@link UserDto}
     */
    public List<UserDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(UserDto[].class));
    }

    /**
     * Get aa users with specific status code
     *
     * @param status {@link HttpStatus}
     * @return {@link ValidatableResponse}
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Users");
        ValidatableResponse response = get(this.specification, USERS_END);
        response.statusCode(status.getCode());
        return response;
    }
}
