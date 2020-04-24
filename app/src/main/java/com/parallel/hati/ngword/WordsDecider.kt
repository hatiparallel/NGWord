package com.parallel.hati.ngword

import java.util.*

class WordsDecider {
    companion object {
        val defaultWords: List<String> = listOf("男", "女", "目", "机", "椅子", "トイレ")

        public fun shuffleNumber(resultSize: Int, arrSize: Int): IntArray {
            val arr = Array<Int>(arrSize, { it }).toMutableList()
            val result: MutableList<Int> = mutableListOf()
            val rand = Random()

            for (i in 0..resultSize - 1) {
                var removed = false
                if (arr[0] == i) {
                    if (arr.size == 1) {
                        val changed = rand.nextInt(arrSize - 1)
                        result.add(result[changed])
                        result[changed] = i
                        break
                    }
                    arr.removeAt(0)
                    removed = true
                }
                result.add(arr.removeAt(rand.nextInt(arr.size)))
                if (removed) {
                    arr.add(i)
                }
            }

            return result.toIntArray()
        }

        public fun chooseDefaultWords(number: Int): Array<String> {
            val words = defaultWords.toMutableList()
            val rand = Random()

            return Array<String>(number, { words.removeAt(rand.nextInt(words.size)) })
        }
    }
}