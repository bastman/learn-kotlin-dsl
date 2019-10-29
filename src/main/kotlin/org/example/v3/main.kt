package org.example.v3

fun main() {
    println("hello")

    val client: Client = createClient {
        firstName = "John"
        lastName = "Doe"
        twitter {
            handle = "The twitter handle"
        }
        company(name = "the comp name (default)") {
            name = "comp name"
            city = "comp city"
        }
    }
    println("hello: $client")
}

fun createClient(init: ClientBuilderDsl.() -> Unit): Client = ClientBuilderDsl().apply(init).build()

fun ClientBuilderDsl.twitter(init: TwitterBuilderDsl.() -> Unit) {
    twitter = TwitterBuilderDsl().apply(init).build()
}

fun ClientBuilderDsl.company(name: String, init: CompanyBuilderDsl.() -> Unit) {
    company = CompanyBuilderDsl()
            .apply { this.name = name }
            .apply(init)
            .build()
}


@DslMarker
annotation class ClientDsl

@ClientDsl
class CompanyBuilderDsl {
    lateinit var name: String
    var city: String? = null
    fun build(): Company = Company(name = name, city = city)
}

@ClientDsl
class TwitterBuilderDsl {
    var handle: String? = null
    fun build(): Twitter = Twitter(handle = handle)
}

@ClientDsl
class ClientBuilderDsl {
    var firstName: String? = null
    var lastName: String? = null
    var twitter: Twitter? = null
    var company: Company? = null
    fun build(): Client = Client(firstName = firstName, lastName = lastName, twitter = twitter, company = company)
}

data class Client(val firstName: String?, val lastName: String?, val twitter: Twitter?, val company: Company?)
data class Twitter(val handle: String?)
data class Company(val name: String, val city: String?)
