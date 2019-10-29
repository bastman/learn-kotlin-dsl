package org.example

fun main() {
    println("hello")

}



class ClientBuilder {
    var firstName:String? = null
    var lastName:String?=null
    var twitter:Twitter?=null
    var company:Company?=null
    fun build():Client=Client(firstName=firstName,lastName = lastName,twitter = twitter, company = company)
}
data class Client(val firstName:String?, val lastName:String?, val twitter:Twitter?, val company:Company?)

class TwitterBuilder {
    var handle:String?=null
    fun build():Twitter = Twitter(handle=handle)
}
data class Twitter(val handle:String?)

class CompanyBuilder {
    var name:String?=""
    var city:String?=null
    fun build():Company = Company(name=name, city=city)
}
data class Company(val name:String?, val city:String?)
