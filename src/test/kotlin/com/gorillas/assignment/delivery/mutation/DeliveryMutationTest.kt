package com.gorillas.assignment.delivery.mutation

import com.gorillas.assignment.delivery.DATA_JSON_PATH
import com.gorillas.assignment.delivery.GRAPHQL_ENDPOINT
import com.gorillas.assignment.delivery.GRAPHQL_MEDIA_TYPE
import com.gorillas.assignment.delivery.verifyOnlyDataExists
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
internal class DeliveryMutationTest(@Autowired private val testClient: WebTestClient) {

    @Test
    fun `verify setDeliveryReceived mutation`() {
        val query = "setDeliveryReceived"

        testClient.post()
            .uri(GRAPHQL_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(GRAPHQL_MEDIA_TYPE)
            .bodyValue("mutation { $query(id: 102, deliveredWarehouse: BerlinZoo) { deliveryId, deliveredDate, deliveredWarehouse, expectedWarehouse } }")
            .exchange()
            .verifyOnlyDataExists(query)
            .jsonPath("$DATA_JSON_PATH.$query.deliveryId").isEqualTo("102")
            .jsonPath("$DATA_JSON_PATH.$query.deliveredWarehouse").isEqualTo("BerlinZoo")
            .jsonPath("$DATA_JSON_PATH.$query.deliveredDate").isNotEmpty
    }
}