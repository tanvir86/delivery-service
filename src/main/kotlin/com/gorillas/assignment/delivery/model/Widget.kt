package com.gorillas.assignment.delivery.model

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.generator.annotations.GraphQLIgnore

@GraphQLDescription("A useful widget")
data class Widget (
    @GraphQLDescription("The widget's value that can be null")
    var value: Int? = null,

    @Deprecated(message = "This field is deprecated", replaceWith = ReplaceWith("value"))
    @GraphQLDescription("The widget's deprecated value that shouldn't be used")
    val deprecatedValue: Int? = value,

    val listOfValues: List<Int>? = null,

    @GraphQLIgnore
    val ignoredField: String? = "ignored",

    private val hiddenField: String? = "hidden"
) {
    @GraphQLDescription("returns original value multiplied by target OR null if original value was null")
    fun multiplyValueBy(multiplier: Int) = value?.times(multiplier)
}