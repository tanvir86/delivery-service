package com.gorillas.assignment.delivery

import org.springframework.test.web.reactive.server.WebTestClient

fun WebTestClient.ResponseSpec.verifyOnlyDataExists(expectedQuery: String): WebTestClient.BodyContentSpec {
    return this.expectBody()
        .jsonPath("$DATA_JSON_PATH.$expectedQuery").exists()
        .jsonPath(ERRORS_JSON_PATH).doesNotExist()
        .jsonPath(EXTENSIONS_JSON_PATH).doesNotExist()
}