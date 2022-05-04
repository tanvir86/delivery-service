package com.gorillas.assignment.delivery.repository

import com.gorillas.assignment.delivery.model.Delivery
import com.gorillas.assignment.delivery.model.Warehouse
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class DeliveryRepository {
    private val deliveries = mapOf(
        101 to Delivery(deliveryId = "101", product = "Bananas", supplier = "JungleInc", quantity = 1000U, expectedDate = OffsetDateTime.now(), expectedWarehouse = Warehouse.TheMoon  ),
        102 to Delivery(deliveryId = "102", product = "Saiyans", supplier = "Bardock", quantity = 9001U, expectedDate = OffsetDateTime.now(), expectedWarehouse = Warehouse.Namek  ),
        103 to Delivery(deliveryId = "103", product = "Skull, Crystal", supplier = "Akator", quantity = 1U, expectedDate = OffsetDateTime.parse("2008-05-22T00:00:00.001Z"), expectedWarehouse = Warehouse.Headquarters, deliveredDate = OffsetDateTime.now(), deliveredWarehouse = Warehouse.Headquarters  )
    )

    fun findDelivery(id: Int): Delivery? = deliveries[id]

    fun retrieveAllDeliveries(): List<Delivery> = deliveries.values.toList()

    fun getDeliveriesByStatus(received: Boolean): List<Delivery> = deliveries.values.toList()
        .filter { (received && it.deliveredDate != null) || (!received && it.deliveredDate == null)   }

}