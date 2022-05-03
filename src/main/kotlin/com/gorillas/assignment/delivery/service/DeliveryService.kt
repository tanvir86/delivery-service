package com.gorillas.assignment.delivery.service

import com.gorillas.assignment.delivery.model.Delivery
import com.gorillas.assignment.delivery.model.Warehouse
import com.gorillas.assignment.delivery.repository.DeliveryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class DeliveryService(private val deliveryRepository: DeliveryRepository) {

    @kotlin.jvm.Throws()
    fun setDeliveryReceived(id: Int, deliveredWarehouse: Warehouse): Delivery? {
        var delivery = deliveryRepository.findDelivery(id)

        delivery?.let {
            it.deliveredWarehouse = deliveredWarehouse
            it.deliveredDate = OffsetDateTime.now()
            return deliveryRepository.save(delivery)
        }
            ?: throw Exception("Invalid Delivery ID")
    }
}