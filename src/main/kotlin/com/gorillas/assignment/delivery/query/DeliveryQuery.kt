package com.gorillas.assignment.delivery.query

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.server.operations.Query
import com.gorillas.assignment.delivery.model.Delivery
import com.gorillas.assignment.delivery.repository.DeliveryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeliveryQuery: Query {
    @GraphQLDescription("retrieves Delivery from the repository by ID")
    fun deliveryById(
        @GraphQLIgnore @Autowired repository: DeliveryRepository,
        @GraphQLDescription("The ID of delivery") id: Int
    ): Delivery? = repository.findDelivery(id)

    @GraphQLDescription("retrieves all deliveries from repository")
    fun availableDeliveries(@GraphQLIgnore @Autowired repository: DeliveryRepository): List<Delivery?> = repository.retrieveAllDeliveries()

    @GraphQLDescription("retrieves all deliveries not yet received or have already been received from repository")
    fun getDeliveriesByStatus(
        @GraphQLIgnore @Autowired repository: DeliveryRepository,
        @GraphQLDescription("Delivery received status ") received: Boolean
    ): List<Delivery?> = repository.getDeliveriesByStatus(received)

}