package com.gorillas.assignment.delivery.query

import com.gorillas.assignment.delivery.DATA_JSON_PATH
import com.gorillas.assignment.delivery.GRAPHQL_ENDPOINT
import com.gorillas.assignment.delivery.GRAPHQL_MEDIA_TYPE
import com.gorillas.assignment.delivery.verifyOnlyDataExists
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DeliveryQueryTest(@Autowired private val testClient: WebTestClient) {

    @Test
    fun `verify deliveryById query`() {
        val query = "deliveryById"

        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("query { $query(id: 102) { deliveryId, product, supplier, quantity, expectedDate, expectedWarehouse } }")
            .exchange()
            .verifyOnlyDataExists(query)
            .jsonPath("$DATA_JSON_PATH.$query.deliveryId").isEqualTo("102")
            .jsonPath("$DATA_JSON_PATH.$query.product").isEqualTo("Saiyans")
            .jsonPath("$DATA_JSON_PATH.$query.expectedWarehouse").isEqualTo("Namek")
    }

    @Test
    fun `verify availableDeliveries query`() {
        val query = "availableDeliveries"

        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("query { $query { deliveryId, product, supplier, quantity, expectedDate, expectedWarehouse } }")
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.OK)
            .verifyOnlyDataExists(query)
            .jsonPath("$DATA_JSON_PATH.$query").isArray
            .jsonPath("$DATA_JSON_PATH.$query[1].deliveryId").isEqualTo("102")
            .jsonPath("$DATA_JSON_PATH.$query[1].product").isEqualTo("Saiyans")
            .jsonPath("$DATA_JSON_PATH.$query[1].expectedWarehouse").isEqualTo("Namek")
    }

    @Test
    fun `verify getDeliveriesByStatus query for not received deliveries`() {
        val query = "getDeliveriesByStatus"

        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("query { $query(received: false) { deliveryId, product, supplier, quantity, deliveredDate, deliveredWarehouse  } }")
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.OK)
            .verifyOnlyDataExists(query)
            .jsonPath("$DATA_JSON_PATH.$query").isArray
            .jsonPath("$DATA_JSON_PATH.$query[0].deliveredWarehouse").isEmpty
            .jsonPath("$DATA_JSON_PATH.$query[0].deliveredDate").isEmpty
    }

    @Test
    fun `verify getDeliveriesByStatus query for received deliveries `() {
        val query = "getDeliveriesByStatus"

        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("query { $query(received: true) { deliveryId, product, supplier, quantity, deliveredDate, deliveredWarehouse  } }")
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.OK)
            .verifyOnlyDataExists(query)
            .jsonPath("$DATA_JSON_PATH.$query").isArray
            .jsonPath("$DATA_JSON_PATH.$query[0].deliveredWarehouse").isNotEmpty
            .jsonPath("$DATA_JSON_PATH.$query[0].deliveredDate").isNotEmpty
    }
}