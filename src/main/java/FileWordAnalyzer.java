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

        wordsAlphabetically.addAll(Arrays.asList(words));

        Collections.sort(wordsAlphabetically);

        return wordsAlphabetically;
    }


    public List<String> wordsContainingSubString(String subString) {
        FilePartReader reader = new FilePartReader();
        List<String> wordsContainingSubString = new ArrayList<>();
        List<String> allWords = new ArrayList<>();
        String chosenText = reader.readLines(1,10);
        String[] words = chosenText.split("\\s");
        allWords.addAll(Arrays.asList(words));

        for(String word : allWords){
            if(word.contains(subString)) {
                wordsContainingSubString.add(word);
            }
        }

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
        fileWordAnalyzer.wordsContainingSubString("a");
    }
}
