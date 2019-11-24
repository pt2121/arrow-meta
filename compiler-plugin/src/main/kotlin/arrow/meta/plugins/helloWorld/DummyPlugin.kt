package arrow.meta.plugins.helloWorld

import arrow.meta.Meta
import arrow.meta.Plugin
import arrow.meta.invoke
import arrow.meta.quotes.Transform
import arrow.meta.quotes.namedFunction
import arrow.meta.quotes.scope

val Meta.helloWorld: Plugin
    get() =
        "Hello World" {
            meta(
                    namedFunction({ name == "helloWorld" }) { c ->
                        Transform.replace(
                                replacing = c,
                                newDeclaration =

//                                """println("Hello ΛRROW Meta!")""".expression
//                                        .map { exp ->
//                                            """ fun ${this.name}(): Unit = ${exp.text}""".function.value
//                                        }.synthetic

                                """private""".modifierList
                                        .fold(
                                                """ |fun helloWorld(): Unit =
                                                |  println("Hello ΛRROW Meta!")
                                                |""".function.value
                                        ) { acc, mod ->
                                            """ ${mod.text} fun ${acc.name}(): Unit =
                                                ${acc.bodyExpression?.text.orEmpty()}
                                            """.trimIndent().function.value
                                        }.scope()
                                        .synthetic
                        )
                    }
            )
        }
