import com.devingryu.datatype.BitArray
import java.lang.IndexOutOfBoundsException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BitArrayTest {
    @Test
    fun testBitArray() {
        val testBitArray = BitArray(26)
        with(testBitArray) {
            set(0, true)
            set(2, true)
            set(3, true)
            set(8, true)
            set(13, true)
            set(24, true)
            set(25, true)
        }
        assertEquals("10110000100001000000000011", testBitArray.toString())
        assertEquals(true, testBitArray[8])
        assertEquals(false, testBitArray[22])
        assertEquals(26, testBitArray.size)
        assertFailsWith(IndexOutOfBoundsException::class) { testBitArray[26] }
    }
}