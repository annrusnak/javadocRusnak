package com.softserve.taf.services.placeholder.endpoints;

import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.comment.CommentDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class CommentEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMENTS_END = "/comments";
    private static final String COMMENTS_RESOURCE_END = "/comments/{commentID}";

    public CommentEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Create a comment
     *
     * @param commentDto {@link CommentDto}
     * @return new {@link CommentDto}
     */
    public CommentDto create(CommentDto commentDto) {
        return create(commentDto, HttpStatus.CREATED)
                .extract().as(CommentDto.class);
    }

    /**
     * Create a comment with specific status code
     *
     * @param commentDto {@link CommentDto}
     * @param status     {@link HttpStatus}
     * @return {@link ValidatableResponse}
     */
    public ValidatableResponse create(CommentDto commentDto, HttpStatus status) {
        LOGGER.info("Create new Comment");
        return post(
                this.specification,
                COMMENTS_END,
                commentDto)
                .statusCode(status.getCode());
    }

    /**
     * Update an exicting comment
     *
     * @param id         integer
     * @param commentDto {@link CommentDto}
     * @return updated {@link CommentDto}
     */
    public CommentDto update(int id, CommentDto commentDto) {
        return update(commentDto, id, HttpStatus.OK)
                .extract().as(CommentDto.class);
    }

    /**
     * Update an exicting comment with specific status code
     *
     * @param commentDto {@link CommentDto}
     * @param id         integer
     * @param status     {@link HttpStatus}
     * @return {@link ValidatableResponse}
     */
    public ValidatableResponse update(CommentDto commentDto, int id, HttpStatus status) {
        LOGGER.info("Update Comment by id [{}]", id);
        return put(
                this.specification,
                COMMENTS_RESOURCE_END,
                commentDto,
                id)
                .statusCode(status.getCode());
    }

    /**
     * Get comment by id
     *
     * @param id integer
     * @return {@link CommentDto}
     */
    public CommentDto getById(int id) {
        return getById(id, HttpStatus.OK)
                .extract().as(CommentDto.class);
    }

    /**
     * Get comment by id with specific status code
     *
     * @param id     integer
     * @param status {@link HttpStatus}
     * @return {@link ValidatableResponse}
     */
    public ValidatableResponse getById(int id, HttpStatus status) {
        LOGGER.info("Get Comment by id [{}]", id);
        return get(
                this.specification,
                COMMENTS_RESOURCE_END,
                String.valueOf(id))
                .statusCode(status.getCode());
    }

    /**
     * Get all comments
     *
     * @return List of {@link CommentDto}
     */
    public List<CommentDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(CommentDto[].class));
    }

    /**
     * Get all comments with specific status code
     *
     * @param status {@link HttpStatus}
     * @return {@link ValidatableResponse}
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Comments");
        ValidatableResponse response = get(this.specification, COMMENTS_END);
        response.statusCode(status.getCode());
        return response;
    }
}
