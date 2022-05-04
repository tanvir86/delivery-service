package com.gorillas.assignment.delivery.model

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import java.time.OffsetDateTime

@GraphQLDescription("Object containing Delivery Data")
data class Delivery (
    @GraphQLDescription("Unique ID of a delivery")
    val deliveryId: String,

    @GraphQLDescription("Product name in a delivery")
    val product: String,

    @GraphQLDescription("Supplier name of a delivery")
    val supplier: String,

    @GraphQLDescription("Number of unit product in a delivery")
    val quantity: UInt,

    @GraphQLDescription("Expected delivery date")
    val expectedDate: OffsetDateTime,

    @GraphQLDescription("Expected warehouse name to be delivered")
    val expectedWarehouse: Warehouse,

    @GraphQLDescription("Datetime of the delivery")
    var deliveredDate: OffsetDateTime? = null,

    @GraphQLDescription("warehouse name where product was delivered")
    var deliveredWarehouse: Warehouse? = null
        ) {
}