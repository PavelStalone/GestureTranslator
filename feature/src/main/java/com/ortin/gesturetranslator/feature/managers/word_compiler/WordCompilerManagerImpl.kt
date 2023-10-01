package com.ortin.gesturetranslator.feature.managers.word_compiler

class WordCompilerManagerImpl : WordCompilerManager {
    private val MIN_FRAME_DETECT = 10
    private val MIN_SPACE_TIME = 3000L
    private var text = ""

    private var lastWord: String? = null
    private var currentLastWord: String? = null
    private var countFrameHold = 0

    private var timeSpace: Long = 0
    private val frameSortedMap = HashMap<String, Int>()
    private var lastTime: Long = 0
    override fun addLetter(letter: String?) {
        timeSpace = if (lastTime != 0L) System.currentTimeMillis() - lastTime else 0L
        if (timeSpace >= MIN_SPACE_TIME) text += " "

        if (lastWord != null) {
            if (lastWord == letter) {
                countFrameHold++
                if (countFrameHold >= MIN_FRAME_DETECT) {
                    if (currentLastWord != null && currentLastWord != lastWord) text += lastWord else if (currentLastWord == null) text += lastWord
                    currentLastWord = lastWord
                    frameSortedMap.clear()
                    lastWord = null
                    countFrameHold = 0
                }
            } else {
                if (frameSortedMap.containsKey(lastWord)) frameSortedMap[lastWord!!] =
                    frameSortedMap[lastWord]!! + countFrameHold else frameSortedMap[lastWord!!] =
                    countFrameHold

                countFrameHold = 1
                lastWord = letter

                val checkWord = searchCurrentWord()

                if (checkWord != null) {
                    if (currentLastWord != null && currentLastWord != checkWord) text += checkWord else if (currentLastWord == null) text += checkWord
                    currentLastWord = checkWord
                    frameSortedMap.clear()
                    lastWord = null
                }
            }
        } else {
            lastWord = letter
            countFrameHold = 1
        }

        lastTime = System.currentTimeMillis()
    }

    override fun getWord(): String {
        return text
    }

    private fun searchCurrentWord(): String? {
        for (key in frameSortedMap.keys) {
            if (frameSortedMap[key]!! >= MIN_FRAME_DETECT) {
                return key
            }
        }
        return null
    }

    override fun clearState() {
        text = ""
        lastWord = null
        currentLastWord = null
        countFrameHold = 0
        timeSpace = 0L
        frameSortedMap.clear()
        lastTime = 0L
    }
}