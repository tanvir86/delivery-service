package com.gorillas.assignment.delivery.repository

import com.gorillas.assignment.delivery.mockdatasource.DeliveryDataSource
import com.gorillas.assignment.delivery.model.Delivery
import com.gorillas.assignment.delivery.model.Warehouse
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class DeliveryRepository {

    fun findDelivery(id: Int): Delivery? = DeliveryDataSource.deliveries[id]

    fun retrieveAllDeliveries(): List<Delivery> = DeliveryDataSource.deliveries.values.toList()

    fun getDeliveriesByStatus(received: Boolean): List<Delivery> = DeliveryDataSource.deliveries.values.toList()
        .filter { (received && it.deliveredDate != null) || (!received && it.deliveredDate == null) }

    fun save(delivery: Delivery): Delivery? {
        DeliveryDataSource.deliveries[delivery.deliveryId.toInt()] = delivery
        return delivery
    }

}