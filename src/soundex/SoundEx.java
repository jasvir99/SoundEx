/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundex;

import java.lang.Character;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jass
 */
public class SoundEx {

    /**
     * @token whose soundEx code is to be calculated
     */
    private final String token;
    /**
     * constant for single space.
     */
    private final char singleSpace = ' ';
    /**
     * length of soundEx code.
     */
    private final int codeLength = 4;
    /**
     * characters need to be removed.
     */
    private final char[] charsToBeRemoved;

    private final Map<Character, Character> characterMap;

    /**
     * Parameterized Constructor for SoundEx class.
     *
     * @param word String to be converted
     */
    public SoundEx(final String word) {
        this.characterMap = new HashMap<Character, Character>() {
            {
                put('b', '1');
                put('f', '1');
                put('p', '1');
                put('v', '1');
                put('c', '2');
                put('g', '2');
                put('j', '2');
                put('k', '2');
                put('q', '2');
                put('s', '2');
                put('x', '2');
                put('z', '2');
                put('d', '3');
                put('t', '3');
                put('l', '4');
                put('m', '5');
                put('n', '5');
                put('r', '6');
                
            }
        };
        this.charsToBeRemoved = new char[]{'a', 'e', 'i', 'o', 'u'};
        token = word.toLowerCase();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        // TODO code application logic here
        while (true) {
            System.out.print("\n Enter string:");
            Scanner userInput = new Scanner(System.in);
            String token;
            token = userInput.next();
            SoundEx ex = new SoundEx(token);
            System.out.println(ex.characterMapping());
        }
    }

    /**
     * @param text String from which duplicate characters need to be
     * removed.
     * @return String after removing duplicate characters.
     */
    private String removeDuplicateCharacters(final String text) {
        String result;
        StringBuilder temp = new StringBuilder(text);
        char previousCharacter;

        for (int i = 0; i < temp.length(); i++) {
            previousCharacter = temp.charAt(i);
            for (int j = i + 1; j < temp.length(); j++) {
                if (previousCharacter == temp.charAt(j)) {
                    temp.setCharAt(j, singleSpace);
                } else {
                    break;
                }
            }

        }
        result = temp.toString().replaceAll("\\s+", "");

        return result;
    }

    /**
     *
     * @return returns character mappings
     */
    private String characterMapping() {
        StringBuilder temp
            = new StringBuilder(removeDuplicateCharacters(token));
        String tempResult = "";
        String result;
        String firstCharacter = "";
        for (int i = 0; i < temp.length(); i++) {
            if (i == 0) {
                firstCharacter = Character.toString(temp.charAt(i));
            } else if (!(new String(charsToBeRemoved)
                .contains(String.valueOf(temp.charAt(i))))) {

                tempResult = tempResult + Character.toString(temp.charAt(i));
            }
        }
        temp = new StringBuilder(tempResult);
        for (int i = 0; i < temp.length(); i++) {
            temp.setCharAt(i, characterMap.get(temp.charAt(i)));
        }

        result = firstCharacter + temp;
        result = removeDuplicateCharacters(result);
        if (result.length() < codeLength) {
            result = result + "00000";
        }
        return result.substring(0, codeLength);

    }

}
