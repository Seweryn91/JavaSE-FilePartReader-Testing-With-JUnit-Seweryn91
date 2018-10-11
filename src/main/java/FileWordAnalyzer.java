import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileWordAnalyzer {


    public List<String> wordsByABC(){
        List<String> wordsAlphabetically = new ArrayList<>();
        FilePartReader reader = new FilePartReader();
        String chosenText = reader.readLines(1, 10);
        String[] words = chosenText.split("\\s");

        for(String word : words){
            wordsAlphabetically.add(word);
        }

        Collections.sort(wordsAlphabetically);

        return wordsAlphabetically;
    }


    public List<String> wordsContainingSubString(String subString) {
        List<String> wordsContainingSubString = new ArrayList<>();
        return wordsContainingSubString;

    }

    public List<String> wordsArePalindrome() {
        List<String> palindromeWords = new ArrayList<>();
        return palindromeWords;
    }

    public static void main(String args[]){
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("test_data.txt", 1, 10);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer();
        fileWordAnalyzer.wordsByABC();
    }
}
