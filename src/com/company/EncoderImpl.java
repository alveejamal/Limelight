package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EncoderImpl implements Encoder {

    //using a 2-d string is easier to perform the encoding as it gives us more flexibility
    //while looping through the keyboard. its using extra space, but simpler and easier solution.
    public String[][] keyboard = new String[4][10];

    // we use this map to determine the position of the char in the qwerty keyboard.
    // using a map to get the position is faster instead of iterating through an array.
    public HashMap<Character, String> mainMap = new HashMap();

    public void createMapData() {
        mainMap.put('1', "0,0");
        mainMap.put('2', "0,1");
        mainMap.put('3', "0,2");
        mainMap.put('4', "0,3");
        mainMap.put('5', "0,4");
        mainMap.put('6', "0,5");
        mainMap.put('7', "0,6");
        mainMap.put('8', "0,7");
        mainMap.put('9', "0,8");
        mainMap.put('0', "0,9");
        mainMap.put('q', "1,0");
        mainMap.put('w', "1,1");
        mainMap.put('e', "1,2");
        mainMap.put('r', "1,3");
        mainMap.put('t', "1,4");
        mainMap.put('y', "1,5");
        mainMap.put('u', "1,6");
        mainMap.put('i', "1,7");
        mainMap.put('o', "1,8");
        mainMap.put('p', "1,9");
        mainMap.put('a', "2,0");
        mainMap.put('s', "2,1");
        mainMap.put('d', "2,2");
        mainMap.put('f', "2,3");
        mainMap.put('g', "2,4");
        mainMap.put('h', "2,5");
        mainMap.put('j', "2,6");
        mainMap.put('k', "2,7");
        mainMap.put('l', "2,8");
        mainMap.put(';', "3,9");
        mainMap.put('z', "3,0");
        mainMap.put('x', "3,1");
        mainMap.put('c', "3,2");
        mainMap.put('v', "3,3");
        mainMap.put('b', "3,4");
        mainMap.put('n', "3,5");
        mainMap.put('m', "3,6");
        mainMap.put(',', "3,7");
        mainMap.put('.', "3,8");
        mainMap.put('/', "3,9");
    }

    public void setData() {
        keyboard[0][0] = "1";
        keyboard[0][1] = "2";
        keyboard[0][2] = "3";
        keyboard[0][3] = "4";
        keyboard[0][4] = "5";
        keyboard[0][5] = "6";
        keyboard[0][6] = "7";
        keyboard[0][7] = "8";
        keyboard[0][8] = "9";
        keyboard[0][9] = "0";
        keyboard[1][0] = "q";
        keyboard[1][1] = "w";
        keyboard[1][2] = "e";
        keyboard[1][3] = "r";
        keyboard[1][4] = "t";
        keyboard[1][5] = "y";
        keyboard[1][6] = "u";
        keyboard[1][7] = "i";
        keyboard[1][8] = "o";
        keyboard[1][9] = "p";
        keyboard[2][0] = "a";
        keyboard[2][1] = "s";
        keyboard[2][2] = "d";
        keyboard[2][3] = "f";
        keyboard[2][4] = "g";
        keyboard[2][5] = "h";
        keyboard[2][6] = "j";
        keyboard[2][7] = "k";
        keyboard[2][8] = "l";
        keyboard[2][9] = ";";
        keyboard[3][0] = "z";
        keyboard[3][1] = "x";
        keyboard[3][2] = "c";
        keyboard[3][3] = "v";
        keyboard[3][4] = "b";
        keyboard[3][5] = "n";
        keyboard[3][6] = "m";
        keyboard[3][7] = ",";
        keyboard[3][8] = ".";
        keyboard[3][9] = "/";
    }

    /*
    *reverse the horizontal array till it reaches the mid point of each row
    * complexity should be O(n/2)
     */
    @Override
    public String[][] horizontalFlip(String[][] keyboard) {

        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length / 2; j++) {
                String aux = keyboard[i][j];
                keyboard[i][j] = keyboard[i][keyboard[i].length - j - 1];
                keyboard[i][keyboard[i].length - j - 1] = aux;
            }
        }
        return keyboard;
    }

    /*
    * reverse the rows
    * complexity should be O(n/2)
     */
    @Override
    public String[][] verticalFlip(String[][] keyboard) {
        for (int i = 0; i < keyboard.length / 2; i++) {
            String[] temp = keyboard[i];
            keyboard[i] = keyboard[keyboard.length - i - 1];
            keyboard[keyboard.length - 1 - i] = temp;
        }
        return keyboard;

    }

    /*
    * shift each element by the given +/- value
    * Complexity should be O(n)
     */
    @Override
    public String[][] shift(String[][] keyboard, int value) {
        String[][] shifted = new String[4][10];
        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length; j++) {
                int newPosAux = j + value;
                int newRowPosAux = Math.floorDiv(newPosAux, keyboard[i].length);
                int newRowPos = (newRowPosAux + i) % keyboard.length;
                int newColPos = newPosAux % keyboard[i].length;
                if (newRowPos < 0)
                    newRowPos = keyboard.length + newRowPos;
                if (newColPos < 0)
                    newColPos = keyboard[i].length + newColPos;
                shifted[newRowPos][newColPos] = keyboard[i][j];
            }
        }
        return shifted;
    }

    /*
    *encodes the keyboard based on the encode string
     */

    @Override
    public String[][] keyboardEncoder(String encode) {
        setData();
        String [] [] encodedKeyBoard = this.keyboard;
        if (encode != null) {
            encode = encode.replaceAll("\\s+", "");
            List<String> encodeCommands = Arrays.asList(encode.split(","));

            for (String encodeCommand : encodeCommands) {
                if (encodeCommand.toUpperCase().equals("H"))
                    encodedKeyBoard = horizontalFlip(encodedKeyBoard);
                else if (encodeCommand.toUpperCase().equals("V"))
                    encodedKeyBoard = verticalFlip(encodedKeyBoard);
                else {
                    try {
                        int value = Integer.parseInt(encodeCommand);
                        encodedKeyBoard = shift(encodedKeyBoard, value);
                    } catch (Exception e) {
                        System.out.println("invalid transformation command");
                    }
                }
            }
        }
        return encodedKeyBoard;
    }

    /*
    *takes the enconded keyboard and input text
    * returns encoded input.
     */
    @Override
    public String encodeString(String input, String[][] keyboard) {
        int len = input.length();
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        createMapData();
        while (i < len) {
            char key = input.toLowerCase().charAt(i);
            String pos = mainMap.get(key);
            if (pos != null) {
                List<String> positions = Arrays.asList(pos.split(","));
                int row = Integer.parseInt(positions.get(0));
                int col = Integer.parseInt(positions.get(1));
                stringBuffer.append(keyboard[row][col]);
            } else
                stringBuffer.append(input.charAt(i));
            i++;
        }
        return stringBuffer.toString();
    }


}
