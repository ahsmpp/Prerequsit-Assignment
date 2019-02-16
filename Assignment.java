/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author anish
 */
public class Assignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        run();
        return;
    }
    
    //Code to run the Class
    public static void run() {
        // TODO code application logic here
    String[] elements = { "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl",
                "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr",
                "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs",
                "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W",
                "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np",
                "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg",
                "Cn", "Uut", "Uuq", "Uup", "Uuh", "Uus", "Uuo" };

        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String inputWord = myScanner.next();

        String chemWord = spellWord(inputWord, elements);

        System.out.println(chemWord);

        myScanner.close();
    }
    

    /**
     * @param word the word that we want to re-spell using abbrevications of elements in the periodic table
     * @param elements an array that contains the abbreviations of elements in the periodic table
     *
     * @return word re-spelled using abbreviations of elements in the periodic table
     *
     * The spellWord function will take in two parameters, word and elements.
     * 1. Word represents the word that we want to re-spell using abbreviation of the elements from
     * the periodic table.
     * 2. Elements represents possible element abbreviations that you can use to re-spell the word
     *
     * The function needs to return a String representing the word that you have re-sprelled
     * using abbreviations of the elements in the periodic table.
     *
     */
    private static String spellWord(String word, String[] elements) {
        // write your code here to spell word with element abbreviations
        String oldWord = word;
        int change = 0;

        //For Loop to call a function to compare each element in the array with the input word
        for(int i = 0; i < elements.length; i++){
            word = check(word, elements[i], elements);
        }

        //Loop to check if the input word has been changed to Element word
        for(int i = 0; i < word.length(); i++){
            if(oldWord.charAt(i) != word.charAt(i)){
                if(compare(word.substring(i, i + 1), elements)){//check if input word characters are changed to single character elements
                    change++;
                }
            }
            else if(i - 1 >= 0 && oldWord.charAt(i) == word.charAt(i)){//check if input word characters are changed to double character elements
                if(oldWord.charAt(i - 1) != word.charAt(i - 1)){
                    if(compare(word.substring(i - 1, i + 1), elements)){
                        change++;
                        if(!compare(word.substring(i - 1, i), elements)){
                            change++;
                        }
                    }
                }
            }

            //check if input word characters are changed to treble character elements
            if(i - 2 >= 0 && oldWord.charAt(i) == word.charAt(i) && oldWord.charAt(i - 1) == word.charAt(i - 1)){
                if(oldWord.charAt(i - 2) != word.charAt(i - 2)){
                    if(compare(word.substring(i - 2, i + 1), elements)){
                        change++;
                        change++;
                    }
                }
            }
        }

        if(change == word.length())
            return word;
        else
            return "cannot spell word";
    }

    // feel free to write additional helper functions below
    //Function to check if part of the input word is same as any of the elements in the array
    private static Boolean compare(String subWord, String[] elements){
        Boolean same = false;

        //Loop to check part of input word with all the elements in the array
        for(int i = 0; i < elements.length; i++){
            if(subWord.equals(elements[i])){
                same = true;
                break;
            }
        }
        return same;
    }

    //Function to convert input word to element word
    private static String check(String word, String element, String[] elements){
        int eSize = element.length();

        //Loop to check if element is found in input word and replace if true
        for(int i = 0; i < word.length() && word.length() - i >= eSize; i++){
            String test = word.substring(i, eSize + i);

            if(i - 1 >= 0 && compare(word.substring(i - 1, i + 1), elements)){
                    continue;
            }
            
            if(i - 2 >= 0 && compare(word.substring(i - 2, i + 1), elements)){
                    continue;
            }

            //check if part of input word is equal to the element, if yes, the case sensitive element is replace into the input word
            if(test.equalsIgnoreCase(element))
                word = word.replace(test, element);
        }

        return word;
    }
}
