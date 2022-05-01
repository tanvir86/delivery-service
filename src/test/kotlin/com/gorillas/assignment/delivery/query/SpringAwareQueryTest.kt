package com.gorillas.assignment.delivery.query

import com.gorillas.assignment.delivery.DATA_JSON_PATH
import com.gorillas.assignment.delivery.GRAPHQL_ENDPOINT
import com.gorillas.assignment.delivery.GRAPHQL_MEDIA_TYPE
import com.gorillas.assignment.delivery.verifyOnlyDataExists
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringAwareQueryTest(@Autowired private val testClient: WebTestClient) {
    @Test
    fun `verify widgetById query`() {
        val query = "widgetById"

        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("query { $query(id: 2) { value, multiplyValueBy(multiplier: 5), deprecatedValue } }")
            .exchange()
            .verifyOnlyDataExists(query)
            .jsonPath("$DATA_JSON_PATH.$query.value").isEqualTo("2")
            .jsonPath("$DATA_JSON_PATH.$query.multiplyValueBy").isEqualTo("10")
            .jsonPath("$DATA_JSON_PATH.$query.deprecatedValue").isEqualTo("2")
    }
}