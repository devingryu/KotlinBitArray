import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or

/**
 * An array of bits. Saves boolean value in a minimal space of [ByteArray].
 *
 * @constructor Creates a new array of the specified @param[size], with an initial value of @param[init]
 */
class BitArray(val size: Int, private val init: Boolean) {
    /**
     * Creates a new array of the specified [size], with an initial value of `false`.
     */
    constructor(size: Int) : this(size, false)

    private val dat: ByteArray = ByteArray((size - 1).floorDiv(8) + 1) { if (init) -1 else 0 }

    operator fun get(index: Int): Boolean {
        if (index >= size) throw IndexOutOfBoundsException()
        return (dat[index.floorDiv(8)] and (1 shl (index % 8)).toByte()) != 0x00.toByte()
    }

    operator fun set(index: Int, value: Boolean) {
        if (index >= size) throw IndexOutOfBoundsException()
        if (value)  // set
            dat[index.floorDiv(8)] = dat[index.floorDiv(8)] or (1 shl (index % 8)).toByte()
        else        // clear
            dat[index.floorDiv(8)] = dat[index.floorDiv(8)] and (1 shl (index % 8)).toByte().inv()
    }

    override fun toString(): String {
        var result = ""
        if (size != 0) {
            for (byte in 0 until dat.size - 1)
                for (bit in 0..7)
                    result += if ((dat[byte] and (1 shl bit).toByte()) != 0x00.toByte()) '1' else '0'
            for (bit in 0 until size - (dat.size - 1) * 8)
                result += if ((dat.last() and (1 shl bit).toByte()) != 0x00.toByte()) '1' else '0'
        }
        return result
    }
}