package com.gorillas.assignment.delivery

import com.expediagroup.graphql.generator.directives.KotlinDirectiveWiringFactory
import com.gorillas.assignment.delivery.hooks.CustomSchemaGeneratorHooks
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DeliveryApplication {

	@Bean
	fun wiringFactory() = KotlinDirectiveWiringFactory()

	@Bean
	fun hooks(wiringFactory: KotlinDirectiveWiringFactory) = CustomSchemaGeneratorHooks(wiringFactory)
}

fun main(args: Array<String>) {
	runApplication<DeliveryApplication>(*args)
}
