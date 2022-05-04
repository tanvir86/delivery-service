package com.gorillas.assignment.delivery.hooks

import com.expediagroup.graphql.generator.directives.KotlinDirectiveWiringFactory
import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import graphql.language.StringValue
import graphql.schema.*
import org.springframework.beans.factory.BeanFactoryAware
import reactor.core.publisher.Mono
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubclassOf

class CustomSchemaGeneratorHooks(override val wiringFactory: KotlinDirectiveWiringFactory) : SchemaGeneratorHooks {

    /**
     * Register additional GraphQL scalar types.
     */
    override fun willGenerateGraphQLType(type: KType): GraphQLType? = when (type.classifier) {
        OffsetDateTime::class -> graphqlDateTimeType
        UInt::class -> graphqlUnsignedIntType
        else -> null
    }

    /**
     * Register Reactor Mono monad type.
     */
    override fun willResolveMonad(type: KType): KType = when (type.classifier) {
        Mono::class -> type.arguments.first().type ?: type
        Set::class -> List::class.createType(type.arguments)
        else -> type
    }

    /**
     * Exclude the Spring bean factory interface
     */
    override fun isValidSuperclass(kClass: KClass<*>): Boolean {
        return when {
            kClass.isSubclassOf(BeanFactoryAware::class) -> false
            else -> super.isValidSuperclass(kClass)
        }
    }

}


internal val graphqlDateTimeType: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("DateTime")
    .description("""An RFC-3339 compliant DateTime Scalar""")
    .coercing(DateTimeCoercing)
    .build()

private object DateTimeCoercing : Coercing<OffsetDateTime, String> {
    override fun parseValue(input: Any): OffsetDateTime = runCatching {
        when (input) {
            is OffsetDateTime -> input
            is ZonedDateTime -> input.toOffsetDateTime()
            is String -> parseOffsetDateTime(input)
            else -> throw CoercingParseLiteralException("Expected valid RFC3339 DateTime but was $input")
        }
    }.getOrElse {
        throw CoercingParseLiteralException("Expected valid RFC3339 DateTime but was $input")
    }
    override fun serialize(dataFetcherResult: Any): String = kotlin.runCatching {
        dataFetcherResult.toString()
    }.getOrElse {
        throw CoercingSerializeException("Data fetcher result $dataFetcherResult cannot be serialized to a String")
    }
    override fun parseLiteral(input: Any): OffsetDateTime = runCatching {
        parseOffsetDateTime((input as? StringValue)?.value)
    }.getOrElse {
        throw CoercingParseLiteralException("Expected valid Period literal but was $input")
    }

    private fun parseOffsetDateTime(str: String?) : OffsetDateTime  {
        try{
            return OffsetDateTime.parse(str, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        } catch (err: DateTimeParseException){
            throw CoercingParseLiteralException("Expected valid RFC3339 DateTime but was $str")
        }
    }
}


internal val graphqlUnsignedIntType: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("UInt")
    .description("""An Unsigned Integer equivalent to Kotlin UInt""")
    .coercing(UIntCoercing)
    .build()

private object UIntCoercing : Coercing<UInt, Int> {
    override fun parseValue(input: Any): UInt = runCatching {
        when (input) {
            is UInt -> input
            else -> throw CoercingParseLiteralException("Expected valid Unsigned Integer but was $input")
        }
    }.getOrElse {
        throw CoercingParseLiteralException("Expected valid Unsigned Integer but was $input")
    }
    override fun serialize(dataFetcherResult: Any): Int = kotlin.runCatching {
        (dataFetcherResult as? UInt)?.toInt() ?: throw CoercingSerializeException("Data fetcher result $dataFetcherResult cannot be serialized to a UInt")
    }.getOrElse {
        throw CoercingSerializeException("Data fetcher result $dataFetcherResult cannot be serialized to a UInt")
    }
    override fun parseLiteral(input: Any): UInt = runCatching {
        (input as? UInt)?:  throw CoercingParseLiteralException("Expected valid Unsigned Integer but was $input")
    }.getOrElse {
        throw CoercingParseLiteralException("Expected valid Unsigned Integer but was $input")
    }

}