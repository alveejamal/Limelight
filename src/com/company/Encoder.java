package com.company;

public interface Encoder {

    public String [] [] horizontalFlip(String[][] keyboard);
    public String [] [] verticalFlip(String[][] keyboard);
    public String [] [] shift(String[][] keyboard, int value);
    public String[][] keyboardEncoder(String encode);
    public String encodeString(String input, String[][] keyboard);
}
