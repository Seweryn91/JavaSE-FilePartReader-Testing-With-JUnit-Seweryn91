import java.io.*;
import java.util.Scanner;

public class FilePartReader {

    private String filePath;
    private Integer fromLine;
    private Integer toLine;
    private Scanner scanner;

    public FilePartReader() {
        filePath = "test_data.txt";
        fromLine = 0;
        toLine = 0;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) throws IllegalArgumentException {

        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;

        if ((fromLine > toLine) || fromLine < 1){
            throw new IllegalArgumentException("Provided arguments are invalid!");
        }

    }

    private String read(){
        StringBuilder fileContent = new StringBuilder();
        try {
            File file = new File(filePath);
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine());
                fileContent.append("\n");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return fileContent.toString();
    }

    public String readLines(){
        String text = read();
        StringBuilder lines = new StringBuilder();
        String[] textLines = text.split("\n");

        for (int i = 0; i<textLines.length; i++){
            if (i >= fromLine-1 && i <= toLine-1){
                lines.append(textLines[i]);
                lines.append("\n");
            }
        }

        return lines.toString();
    }

}