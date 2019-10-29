package org.example.v1

fun main() {
    println("hello")

    val client: Client = createClient {
        this.firstName = "John"
        this.lastName = "Doe"
        this.twitter = twitter {
            handle = "The twitter handle"
        }
        this.company = company {
            this.name = "comp name"
            this.city = "comp city"
        }
    }
    println("hello: $client")
}

fun createClient(c: ClientBuilder.()->Unit): Client {
    val builder = ClientBuilder()
    c(builder)
    return builder.build()
}

fun ClientBuilder.twitter(c: TwitterBuilder.()->Unit): Twitter {
    return TwitterBuilder().apply(c).build()
}

fun ClientBuilder.company(c: CompanyBuilder.()->Unit): Company {
    return CompanyBuilder().apply(c).build()
}


@DslMarker
annotation class ClientDsl

@ClientDsl
class CompanyBuilderDsl: CompanyBuilder()
@ClientDsl
class TwitterBuilderDsl: TwitterBuilder()
@ClientDsl
class ClientBuilderDsl: ClientBuilder()

open class ClientBuilder {
    var firstName:String? = null
    var lastName:String?=null
    var twitter: Twitter?=null
    var company: Company?=null
    fun build(): Client = Client(firstName = firstName, lastName = lastName, twitter = twitter, company = company)
}
data class Client(val firstName:String?, val lastName:String?, val twitter: Twitter?, val company: Company?)

open class TwitterBuilder {
    var handle:String?=null
    fun build(): Twitter = Twitter(handle = handle)
}
data class Twitter(val handle:String?)

open class CompanyBuilder {
    var name:String?=""
    var city:String?=null
    fun build(): Company = Company(name = name, city = city)
}
data class Company(val name:String?, val city:String?)
