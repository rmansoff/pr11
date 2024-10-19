public class Main {
    public static void main(String[] args) {
        // Створення об'єктів FileProcessor і LogAnalyzer
        FileProcessor fileProcessor = new FileProcessor();
        LogAnalyzer logAnalyzer = new LogAnalyzer();

        // Шлях до файлів
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";
        String logFilePath = "logs.txt";
        String analyticFilePath = "analytic.results.txt";

        // Виклик методів обробки файлів і логів
        fileProcessor.processFile(inputFilePath, outputFilePath);
        logAnalyzer.analyzeLogs(logFilePath, analyticFilePath);
    }
}
