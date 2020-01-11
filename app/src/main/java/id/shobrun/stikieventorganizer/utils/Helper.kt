package id.shobrun.stikieventorganizer.utils

import android.annotation.SuppressLint
import java.security.MessageDigest
import java.sql.Timestamp
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

import kotlin.system.measureTimeMillis

object Helper{
    fun getTimeStamp() = Timestamp(System.currentTimeMillis()).time

    /**
     * Hashing
     */
    fun md5(input: String) = hashString("MD5",input)
    fun sha256(input: String) = hashString("SHA-256",input)
    private fun hashString(type:String, input:String): String{
        val bytes = MessageDigest.getInstance(type)
            .digest(input.toByteArray())
        return printHexBinary(bytes)
    }
    private fun printHexBinary(data: ByteArray): String {
        val HEX_CHARS = "0123456789ABCDEF".toCharArray()
        val r = StringBuilder(data.size * 2)
        data.forEach { b ->
            val i = b.toInt()
            r.append(HEX_CHARS[i shr 4 and 0xF])
            r.append(HEX_CHARS[i and 0xF])
        }
        return r.toString()
    }

    private fun simpleHash(input: String): String{
        val alphabet = "ABCDEF0123456789".toCharArray()
        val alphabetLength = alphabet.size
        var hash = ""
        var index = 0
        do {
            val temp = input[index++].toInt() % alphabetLength
            hash = "${alphabet[temp]}$hash"
        }while (index<input.length)
        return hash
    }
    fun getUniqueID(userId:String):String{
        val id = "$userId${getTimeStamp()}"
        return simpleHash(simpleHash(id))
    }
}