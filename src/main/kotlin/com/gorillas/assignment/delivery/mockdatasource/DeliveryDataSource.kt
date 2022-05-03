package com.gorillas.assignment.delivery.mockdatasource

import com.gorillas.assignment.delivery.model.Delivery
import com.gorillas.assignment.delivery.model.Warehouse
import java.time.OffsetDateTime

object DeliveryDataSource {
    val deliveries = mutableMapOf(
        101 to Delivery(
            deliveryId = "101",
            product = "Bananas",
            supplier = "JungleInc",
            quantity = 1000U,
            expectedDate = OffsetDateTime.parse("2027-01-08T07:17:48.237Z"),
            expectedWarehouse = Warehouse.TheMoon
        ),
        102 to Delivery(
            deliveryId = "102",
            product = "Saiyans",
            supplier = "Bardock",
            quantity = 9001U,
            expectedDate = OffsetDateTime.parse("2019-10-10T09:08:11.098Z"),
            expectedWarehouse = Warehouse.Namek
        ),
        103 to Delivery(
            deliveryId = "103",
            product = "Skull, Crystal",
            supplier = "Akator",
            quantity = 1U,
            expectedDate = OffsetDateTime.parse("2008-05-22T00:00:00.001Z"),
            expectedWarehouse = Warehouse.Headquarters,
            deliveredWarehouse = Warehouse.Namek,
            deliveredDate = OffsetDateTime.now()
        ),
        104 to Delivery(
            deliveryId = "104",
            product = "Bananas",
            supplier = "JungleInc",
            quantity = 1U,
            expectedDate = OffsetDateTime.parse("2020-10-08T07:18:42.937Z"),
            expectedWarehouse = Warehouse.BerlinZoo
        ),
        105 to Delivery(
            deliveryId = "105",
            product = "Apples",
            supplier = "ApplesToOrangesInc",
            quantity = 50U,
            expectedDate = OffsetDateTime.parse("2020-05-01T09:00:42.000Z"),
            expectedWarehouse = Warehouse.Oranges
        ),
        106 to Delivery(
            deliveryId = "106",
            product = "Salad",
            supplier = "HealthyFoodInc",
            quantity = 600U,
            expectedDate = OffsetDateTime.parse("2021-11-01T11:20:42.000Z"),
            expectedWarehouse = Warehouse.Headquarters
        ),
        107 to Delivery(
            deliveryId = "107",
            product = "Salad",
            supplier = "HealthyFoodInc",
            quantity = 400U,
            expectedDate = OffsetDateTime.parse("2021-11-01T11:20:42.000Z"),
            expectedWarehouse = Warehouse.Headquarters
        ),
        108 to Delivery(
            deliveryId = "108",
            product = "Salad",
            supplier = "HealthyFoodInc",
            quantity = 800U,
            expectedDate = OffsetDateTime.parse("2021-11-01T11:20:42.000Z"),
            expectedWarehouse = Warehouse.Headquarters
        )
    )
}