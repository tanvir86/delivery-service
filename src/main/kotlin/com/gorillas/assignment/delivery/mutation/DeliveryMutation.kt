package com.gorillas.assignment.delivery.mutation

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Mutation
import com.gorillas.assignment.delivery.model.Delivery
import com.gorillas.assignment.delivery.model.Warehouse
import com.gorillas.assignment.delivery.repository.DeliveryRepository
import com.gorillas.assignment.delivery.service.DeliveryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Mutation request for Delivery Repository
 */
@Component
class DeliveryMutation : Mutation {

    @Autowired
    lateinit var deliveryService: DeliveryService;

    @GraphQLDescription("Update delivery status as received")
    fun setDeliveryReceived(
        @GraphQLDescription("The ID of delivery") id: Int,
        @GraphQLDescription("warehouse name where product was delivered") deliveredWarehouse: Warehouse
    )
            : Delivery? = deliveryService.setDeliveryReceived(id, deliveredWarehouse)
}