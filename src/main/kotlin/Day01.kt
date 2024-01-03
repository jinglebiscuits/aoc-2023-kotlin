import java.io.File
import java.lang.IllegalStateException

class Day01 {


    val numberStrings = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val numberMap: Map<String, String> = mapOf(
        Pair("zero", "0"),
        Pair("one", "1"),
        Pair("two", "2"),
        Pair("three", "3"),
        Pair("four", "4"),
        Pair("five", "5"),
        Pair("six", "6"),
        Pair("seven", "7"),
        Pair("eight", "8"),
        Pair("nine", "9"))
    init {
        var sum = 0
        File(javaClass.getResource("inputs/day1input.txt").toURI()).readLines().map {
            sum += getNumberFromLine(it)
        }
        println(sum)
    }

    fun getNumberFromLine(line: String) : Int {
        var numberAsString = ""
        numberAsString += getFirstDigit(line)
        numberAsString += getLastDigit(line.reversed())
        return numberAsString.toInt()
    }

    fun getFirstDigit(line: String) : Char {
        var testString = ""
        line.forEach {
            testString += it
            val digit = getDigit(testString)
            if (digit != null) {
                return digit
            }
        }
        throw IllegalStateException("should have found something by now")
    }

    fun getLastDigit(line: String) : Char {
        var testString = ""
        line.forEach {
            testString = it + testString
            val digit = getDigit(testString)
            if (digit != null) {
                return digit
            }
        }
        throw IllegalStateException("should have found something by now")
    }

    fun getDigit(input: String) : Char? {
        val pair = input.findAnyOf(numberStrings, ignoreCase = true)
        return if (pair != null) {
            toDigitString(pair.second)
        } else {
            null
        }
    }

    fun toDigitString(input: String) : Char {
        val out: Char?
        if (input.length > 1) {
            out = numberMap.get(input)?.toCharArray()?.first()
            return out ?: "0".toCharArray().first()
        } else {
            return input.toCharArray().first()
        }
    }
}