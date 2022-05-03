package com.gorillas.assignment.delivery.service

import com.gorillas.assignment.delivery.model.Delivery
import com.gorillas.assignment.delivery.model.Warehouse
import com.gorillas.assignment.delivery.repository.DeliveryRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


internal class DeliveryServiceTest {

    private val deliveryRepository : DeliveryRepository = mockk(relaxed = true)
    private val deliveryService = DeliveryService(deliveryRepository)

    @Test
    fun `setDeliveryReceived should check if delivery exist`() {

        deliveryService.setDeliveryReceived(103,Warehouse.Namek)

        verify(exactly = 1) { deliveryRepository.findDelivery(103) }
    }

    @Test
    fun `setDeliveryReceived should throw exception if delivery not exist`() {
        every {deliveryRepository.findDelivery(any()) } returns null

        assertThrows<Exception> { deliveryService.setDeliveryReceived(103, Warehouse.Namek) }
        verify(exactly = 0) { deliveryRepository.save(any()) }
    }

    @Test
    fun `setDeliveryReceived should update delivery deliveredWarehouse and deliveredDate`() {
        val delivery : Delivery = mockk(relaxed = true)
        every {deliveryRepository.findDelivery(103) } returns delivery

        deliveryService.setDeliveryReceived(103,Warehouse.Namek)

        verify(exactly = 1) { delivery.deliveredWarehouse =  Warehouse.Namek }
        verify(exactly = 1) { delivery.deliveredDate =  any() }
        verify(exactly = 1) { deliveryRepository.save(delivery) }

    }
}