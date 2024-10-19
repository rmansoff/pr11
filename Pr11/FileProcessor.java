import java.io.*;

public class FileProcessor {
    public void processFile(String inputFilePath, String outputFilePath) {
        int charCount = 0;
        int wordCount = 0;
        int lineCount = 0;

        // Читання даних з input.txt і підрахунок символів, слів та рядків
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();
                wordCount += line.split("\\s+").length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Виведення результатів в консоль
        System.out.println("Characters: " + charCount);
        System.out.println("Words: " + wordCount);
        System.out.println("Lines: " + lineCount);

        // Запис результатів в output.txt
        try (FileOutputStream fos = new FileOutputStream(outputFilePath);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {

            writer.write("Characters: " + charCount + "\n");
            writer.write("Words: " + wordCount + "\n");
            writer.write("Lines: " + lineCount + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
