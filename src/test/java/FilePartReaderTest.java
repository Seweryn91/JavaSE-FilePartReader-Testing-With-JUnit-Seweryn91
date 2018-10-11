import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FileWordAnalyzerTest {

    private FilePartReader filePartReader;
    private FileWordAnalyzer fileWordAnalyzer;


    @Mock
    private FilePartReader filePartReaderMock;


    @BeforeEach
    void setup() {
        this.filePartReader = new FilePartReader();
        this.fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
    }



    @Test
    @DisplayName("Test word sorting by alphabetic order")
    void wordsByABCTest() {
        List<String> expectedWords = new ArrayList<>(Arrays.asList("0j", "1a1", "2a", "2b", "3a", "3b", "3c", "4a", "4bb4", "4cr", "4d", "5ax", "5b", "5c", "5d", "5e", "6a", "6bb", "6ca", "6d", "6ea", "6f", "7ea", "7f", "7g", "7ttw", "8eef", "8o8", "8ww", "9p9", "9rrw"));
        filePartReader.setup("test_data.txt",1,10);

        List wordsAlphabhetically = fileWordAnalyzer.wordsByABC();

        assertEquals(expectedWords, wordsAlphabhetically);
    }


    @Test
    @DisplayName("Test if exception thrown in method setup with fromline < 1")
    void testSetupFromLineLT1() {
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("test_data.txt",0,5));
    }


    @Test
    @DisplayName("Test if exception thrown in method setup with toLine < fromLine")
    void testSetupToLineLTFromLine() {
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("test_data.txt",5,2));
    }



    @Test
    @DisplayName("Test reading lines from 1 to 2")
    void testReadLines1_2() {
        String expected = "1a1\n2b 2a\n";
        filePartReader.setup("test_data.txt",1,2);

        String recieved = filePartReader.readLines();

        assertEquals(expected,recieved);
    }


    @Test
    @DisplayName("Test reading lines from 2 to 4")
    void testReadLines2_4() {
        String expected = "2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a\n";
        filePartReader.setup("test_data.txt",2,4);
        String recieved = filePartReader.readLines();
        assertEquals(expected,recieved);
    }


    @Test
    @DisplayName("Test finding palindromes")
    void testWordsArePalindrome() {
        filePartReader.setup("test_data.txt",1,10);
        List<String> received = fileWordAnalyzer.wordsArePalindrome();
        List<String> expected = new ArrayList(Arrays.asList("1a1","4bb4","8o8","9p9"));

        assertEquals(expected,received);
    }


    @Test
    @DisplayName("Test finding strings containing given substring")
    void testWordsContainingSubString(){
        MockitoAnnotations.initMocks(this);
        fileWordAnalyzer = new FileWordAnalyzer(filePartReaderMock);
        when(filePartReaderMock.readLines()).thenReturn("1a1\n2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a\n5e 5d 5c 5b 5ax\n6f 6ea 6d 6ca 6bb 6a\n7g 7f 7ea\n8eef 8o8 8ww\n9rrw 9p9n\n0j");
        List received;
        List<String> expected = Arrays.asList("5e", "6ea", "7ea", "8eef");

        received = fileWordAnalyzer.wordsContainingSubString("e");

        assertEquals(expected,received);
    }


    @Test
    @DisplayName("Test reading all lines from file")
    void testReadLinesAll(){
        String expected = "1a1\n2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a\n5e 5d 5c 5b 5ax\n6f 6ea 6d 6ca 6bb 6a\n7g 7ttw 7f 7ea\n8eef 8o8 8ww\n9rrw 9p9\n0j\n";
        filePartReader.setup("test_data.txt",1,10);
        String recieved = filePartReader.readLines();
        assertEquals(expected,recieved);
    }


    @Test
    @DisplayName("Test reading while 'toLine' is past file length")
    void testReadLinesPastEof(){
        String expected = "1a1\n2b 2a\n3c 3b 3a\n4d 4cr 4bb4 4a\n5e 5d 5c 5b 5ax\n6f 6ea 6d 6ca 6bb 6a\n7g 7ttw 7f 7ea\n8eef 8o8 8ww\n9rrw 9p9\n0j\n";
        filePartReader.setup("test_data.txt",1,999);
        String received = filePartReader.readLines();
        assertEquals(expected,received);
    }

}