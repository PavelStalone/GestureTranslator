package com.ortin.gesturetranslator.feature.managers.word_compiler;

import java.util.HashMap;

public class WordCompilerManagerImpl implements WordCompilerManager {

    private final int MIN_FRAME_DETECT = 10;
    private final long MIN_SPACE_TIME = 3000L;

    private String text = "";
    private String lastWord;
    private String currentLastWord;
    private int countFrameHold;
    private long timeSpace;
    private final HashMap<String, Integer> frameSortedMap = new HashMap<>();

    private long lastTime;

    @Override
    public void addLetter(String letter) {
        timeSpace = lastTime != 0L ? System.currentTimeMillis() - lastTime : 0L;

        if (timeSpace >= MIN_SPACE_TIME) text += " ";

        if (lastWord != null) {
            if (lastWord.equals(letter)) {
                countFrameHold++;
                if (countFrameHold >= MIN_FRAME_DETECT){
                    if (currentLastWord != null && !currentLastWord.equals(lastWord))
                        text += lastWord;
                    else if (currentLastWord == null) text += lastWord;
                    currentLastWord = lastWord;
                    frameSortedMap.clear();
                    lastWord = null;
                    countFrameHold = 0;
                }
            } else {
                if (frameSortedMap.containsKey(lastWord))
                    frameSortedMap.put(lastWord, frameSortedMap.get(lastWord) + countFrameHold);
                else frameSortedMap.put(lastWord, countFrameHold);

                countFrameHold = 1;
                lastWord = letter;

                String checkWord = searchCurrentWord();
                if (checkWord != null) {
                    if (currentLastWord != null && !currentLastWord.equals(checkWord))
                        text += checkWord;
                    else if (currentLastWord == null) text += checkWord;
                    currentLastWord = checkWord;
                    frameSortedMap.clear();
                    lastWord = null;
                }
            }
        } else {
            lastWord = letter;
            countFrameHold = 1;
        }


        lastTime = System.currentTimeMillis();
    }

    @Override
    public String getWord() {
        return text;
    }

    private String searchCurrentWord() {
        for (String key : frameSortedMap.keySet()) {
            if (frameSortedMap.get(key) >= MIN_FRAME_DETECT) {
                return key;
            }
        }
        return null;
    }

    @Override
    public void clearState(){
        text = "";
        lastWord = null;
        currentLastWord = null;
        countFrameHold = 0;
        timeSpace = 0L;
        frameSortedMap.clear();
        lastTime = 0L;
    }
}
