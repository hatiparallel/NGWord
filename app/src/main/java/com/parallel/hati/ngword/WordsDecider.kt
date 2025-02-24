package com.parallel.hati.ngword

import android.util.Log
import java.util.*

class WordsDecider {
    companion object {
        val defaultWords: List<String> = listOf("男", "女", "目", "髪", "口", "手", "足", "頭", "顔", "指", "机", "椅子", "トイレ", "空", "晴れ", "曇り", "雨", "雪", "車", "自転車", "電車", "鍵", "電話", "0", "15", "18", "20", "100", "世界", "日本", "アメリカ", "学校", "夢", "水", "朝", "昼", "夜", "眼鏡", "財布", "パソコン", "スマホ", "テレビ", "ゲーム", "映画", "音楽", "歌", "犬", "猫", "鳥", "草", "花", "木", "虫", "魚", "ズボン", "帽子", "カバン", "充電", "無視", "電池", "ゴミ箱", "ブレーキ", "ハンドル", "本", "メモ", "マンガ", "タオル", "カラオケ", "布団", "洗濯", "ペットボトル", "コップ", "家", "ビル", "山", "川", "海", "イヤホン", "筋肉", "身長", "体重", "うわさ", "時間", "時計", "靴", "化粧", "クリーム", "新幹線", "バス", "橋", "大人", "子ども", "空気", "写真", "魔法", "喧嘩", "今日", "昨日", "明日", "今週", "来週", "先週", "今月", "来月", "先月", "今年", "去年", "来年", "ブロック", "階段", "エレベーター" , "エスカレーター", "チェック", "部屋", "ドア", "ルール", "赤", "青", "黄色", "白", "黒", "緑", "カメラ", "自転車", "傘", "誕生日", "イベント", "カフェ", "弁当", "説明", "コンビニ", "話題", "練習", "ゴールデンウィーク", "特別", "連絡", "相談", "報告", "声", "全部", "たくさん", "少し", "そろそろ", "ゆっくり", "緊張", "終わり", "興味", "日曜", "月曜", "金曜", "土曜", "一緒", "準備", "今回", "前回", "次回", "注意", "正月", "クリスマス", "簡単", "たいへん", "カレンダー", "以上", "以下", "未満", "参加", "好き", "嫌い", "都会", "田舎", "オリンピック", "協力", "チーム", "サービス", "毎日", "パーティー", "漢字", "英語", "スペース", "ありがとう", "人数", "予約", "病院", "お金", "有名", "名前", "うそ", "ずっと", "ティッシュ", "プレゼント")

        public fun shuffleNumber(people_count: Int, words_count: Int): IntArray {
            val arr = Array<Int>(people_count*words_count, { it }).toMutableList()
            val result: MutableList<Int> = mutableListOf()
            val evacuate: MutableList<Int> = mutableListOf()
            val rand = Random()

            for (p in 0..people_count - 1) {
                while (arr.size > 0 && arr[0] < (p + 1)*words_count && arr[0] >= p*words_count) {
                    evacuate.add(arr.removeAt(0))
                }
                if (p == people_count - 1) {
                    val len = evacuate.size
                    for (e in 0..len-1) {
                        var changed = rand.nextInt(p*words_count)
                        while (result[changed] >= p*words_count) {
                            changed = (changed + 1) % (p*words_count)
                        }
                        arr.add(result[changed])
                        result[changed] = evacuate.removeAt(0)
                    }
                }
                for (w in 0..words_count - 1) {
                    result.add(arr.removeAt(rand.nextInt(arr.size)))
                }
                while (evacuate.size > 0) {
                    arr.add(evacuate.removeAt(0))
                }
            }

            return result.toIntArray()
        }

        public fun chooseDefaultWords(count: Int): Array<String> {
            val words = defaultWords.toMutableList()
            val rand = Random()

            return Array<String>(count, { words.removeAt(rand.nextInt(words.size)) })
        }

        public fun getWord(wordlist: Array<String>, words_count: Int, person: Int, id: Int): String {
            return wordlist[person*words_count + id]
        }

        public fun setWord(wordlist: Array<String>, words_count: Int, person: Int, id: Int, word: String) {
            wordlist[person*words_count + id] = word
        }

        public fun getNoneWord(position: Int): String {
            return "ワード${position + 1}"
        }
    }
}