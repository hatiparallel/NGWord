package com.parallel.hati.ngword

import java.util.*

class WordsDecider {
    companion object {
        val defaultWords: Array<String> = arrayOf("男", "女", "目", "机", "椅子", "トイレ")

        public fun shuffleNumber(resultSize: Int, arrSize: Int): Array<Int> {
            val arr = Array<Int>(arrSize, { it }).toMutableList()
            val result: MutableList<Int> = mutableListOf()
            val rand = Random()

            for (i in 0..resultSize - 1) {
                result.add(arr.removeAt(rand.nextInt(arrSize - 1 - i)))
            }

            return result.toTypedArray()
        }

        public fun chooseDefaultWords(count: Int): Array<String> {
            val shuffled = shuffleNumber(count, defaultWords.size)
            return Array<String>(count, { defaultWords[shuffled[it]] })
        }
    }
}