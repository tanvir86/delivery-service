package com.gorillas.assignment.delivery.model

import com.expediagroup.graphql.generator.annotations.GraphQLDescription

@GraphQLDescription("Available Warehouses to Deliver Product")
enum class Warehouse {
    TheMoon,
    Namek,
    Headquarters,
    BerlinZoo,
    Oranges
}