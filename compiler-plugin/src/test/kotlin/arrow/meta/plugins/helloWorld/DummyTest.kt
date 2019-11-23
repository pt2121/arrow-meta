package arrow.meta.plugins.helloWorld

import arrow.meta.plugin.testing.CompilerTest
import arrow.meta.plugin.testing.assertThis
import org.junit.Test

class DummyTest {

    companion object {
        const val TESTS = """
      | import arrow.higherkind
      | //metadebug
      |
      | fun helloWorld(): Unit = TODO()
      | 
      | fun main(args: Array<String>) {
      |   helloWorld()
      | }
      """
    }

    @Test
    fun `simple case`() {
        assertThis(CompilerTest(
                config = { metaDependencies },
                code = { TESTS.source },
                assert = {
                    allOf(quoteOutputMatches(
                            """
          | import arrow.higherkind
          | 
          | //meta: <date>
          | 
          | @arrow.synthetic fun helloWorld(): Unit =
          |   println("Hello ΛRROW Meta!")
          | 
          | fun main(args: Array<String>) {
          |     helloWorld()
          | }
          | 
          """.source))
                }
        ))
    }

//    @Test
//    fun `simple 2`() {
//        assertThis(CompilerTest(
//                config = { metaDependencies },
//                code = { TESTS.source },
//                assert = {
//                    allOf(quoteOutputMatches(
//                            """
//          | import arrow.higherkind
//          |
//          | //meta: <date>
//          |
//          | @arrow.synthetic val x = 10
//          |
//          | fun main(args: Array<String>) {
//          |     println(x)
//          | }
//          |
//          """.source))
//                }
//        ))
//    }
}
