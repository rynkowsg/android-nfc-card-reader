import org.junit.Test

class ExampleUnitTest {
    @Test
    fun `chunk card number`() {
        val res = "123456789012345".chunked(4).joinToString(" ")
        println(res)
    }

    @Test
    fun `pad left`() {
        val res2 = "1".padStart(2, '0')
        println(res2)
    }
}
