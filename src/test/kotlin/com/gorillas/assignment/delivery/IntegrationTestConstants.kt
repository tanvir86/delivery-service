package com.gorillas.assignment.delivery

import org.springframework.http.MediaType

const val DATA_JSON_PATH = "$.data"
const val ERRORS_JSON_PATH = "$.errors"
const val EXTENSIONS_JSON_PATH = "$.extensions"
const val GRAPHQL_ENDPOINT = "/graphql"
val GRAPHQL_MEDIA_TYPE = MediaType("application", "graphql")
const val SUBSCRIPTION_ENDPOINT = "/subscriptions"