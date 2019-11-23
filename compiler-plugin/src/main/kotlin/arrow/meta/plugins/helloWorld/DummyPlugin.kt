package arrow.meta.plugins.helloWorld

import arrow.meta.Meta
import arrow.meta.Plugin
import arrow.meta.invoke
import arrow.meta.quotes.Transform
import arrow.meta.quotes.namedFunction

val Meta.helloWorld: Plugin
    get() =
        "Hello World" {
            meta(
                    namedFunction({ name == "helloWorld" }) { c ->
                        Transform.replace(
                                replacing = c,
                                newDeclaration =
                                """ |fun helloWorld(): Unit = 
                                    |  println("Hello ΛRROW Meta!")
                                    |""".function
                                        .map {
                                            """
                                                val x = 10
                                            """.property.value!!
                                        }
                                        .`as`(
                                            """
                                                val x = 10
                                            """.property.value!!
                                        )
                                        .synthetic
                        )
                    }
            )
        }
