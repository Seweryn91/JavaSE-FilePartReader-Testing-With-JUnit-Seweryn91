import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileWordAnalyzer {

    FilePartReader filePartReader;

    FileWordAnalyzer(FilePartReader filePartReader){
        this.filePartReader = filePartReader;
    }

    public List<String> wordsByABC(){
        String chosenText = filePartReader.readLines();
        String[] words = chosenText.split("\\s");

        List<String> wordsAlphabetically = new ArrayList<>(Arrays.asList(words));

        Collections.sort(wordsAlphabetically);
        return wordsAlphabetically;
    }


    public List<String> wordsContainingSubString(String subString) {
        List<String> wordsContainingSubString = new ArrayList<>();
        String chosenText = filePartReader.readLines();
        String[] words = chosenText.split("\\s");
        List<String> allWords = new ArrayList<>(Arrays.asList(words));

        for(String word : allWords){
            if(word.contains(subString)) {
                wordsContainingSubString.add(word);
            }
        }
        return wordsContainingSubString;

    }

    public List<String> wordsArePalindrome() {
        List<String> palindromeWords = new ArrayList<>();
        String chosenText = filePartReader.readLines();
        String[] words = chosenText.split("\\s");
        List<String> allWords = new ArrayList<>(Arrays.asList(words));
        StringBuilder sb = new StringBuilder();

        for(String word : allWords){
            sb.append(word);
            String wordReversed = sb.reverse().toString();
            if(word.equals(wordReversed)){
                palindromeWords.add(word);
            }
            sb.setLength(0);
        }
        return palindromeWords;
    }

    public static void main(String args[]){
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("test_data.txt", 1, 10);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        fileWordAnalyzer.wordsByABC();
        fileWordAnalyzer.wordsContainingSubString("a");
        fileWordAnalyzer.wordsArePalindrome();
    }
}
