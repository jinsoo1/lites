package com.theshine.android.lites.util.ext

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object StringExt {
    private val shortDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private val longDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA)


    fun showDateAsShort(origin: String?): String {
        return shortDateFormat.format(origin?.let { longDateFormat.parse(it) } ?: return "")
    }


    /**
     * 생년월일을 기준으로 현재 나이 계산
     * @param unix unixtimestamp
     */

    fun getAmericanAge(birthDate: String?): String {
        val now: LocalDate = LocalDate.now()
        val parsedBirthDate: LocalDate =
            LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        var americanAge: Int = now.minusYears(parsedBirthDate.getYear().toLong()).getYear() // (1)

        // (2)
        // 생일이 지났는지 여부를 판단하기 위해 (1)을 입력받은 생년월일의 연도에 더한다.
        // 연도가 같아짐으로 생년월일만 판단할 수 있다!
        if (parsedBirthDate.plusYears(americanAge.toLong()).isAfter(now)) {
            americanAge -= 1
        }
        return americanAge.toString()
    }
}