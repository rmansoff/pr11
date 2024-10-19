import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LogAnalyzer {
    public void analyzeLogs(String logFilePath, String outputFilePath) {
        Map<String, Integer> logCounts = new HashMap<>();
        Map<String, Integer> successCounts = new HashMap<>();

        int totalLogs = 0;
        int totalSuccessLogs = 0;

        // Читання лог-файлу та підрахунок кількості логів кожного типу
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalLogs++;

                // Підрахунок логів різних типів
                if (line.contains("SUCCESS")) {
                    totalSuccessLogs++;
                    String user = extractUser(line);
                    successCounts.put(user, successCounts.getOrDefault(user, 0) + 1);
                }

                // Підрахунок логів по типу
                String logType = extractLogType(line);
                logCounts.put(logType, logCounts.getOrDefault(logType, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Виведення статистики в консоль
        System.out.println("Log counts: " + logCounts);
        System.out.println("Success counts per user: " + successCounts);
        System.out.println("Total success logs: " + totalSuccessLogs);
        System.out.println("Total logs: " + totalLogs);
        System.out.println("Success rate: " + (totalSuccessLogs * 100.0 / totalLogs) + "%");

        // Запис результатів в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write("Log counts: " + logCounts + "\n");
            writer.write("Success counts per user: " + successCounts + "\n");
            writer.write("Total success logs: " + totalSuccessLogs + "\n");
            writer.write("Total logs: " + totalLogs + "\n");
            writer.write("Success rate: " + (totalSuccessLogs * 100.0 / totalLogs) + "%\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для визначення типу логу
    private String extractLogType(String log) {
        if (log.contains("ERROR")) {
            return "ERROR";
        } else if (log.contains("DEBUG")) {
            return "DEBUG";
        } else if (log.contains("INFO")) {
            return "INFO";
        } else if (log.contains("SUCCESS")) {
            return "SUCCESS";
        } else {
            return "UNKNOWN";
        }
    }

    // Метод для витягування імені користувача з логу
    private String extractUser(String log) {
        int userIndex = log.indexOf("User: ");
        if (userIndex != -1) {
            return log.substring(userIndex + 6).split(" ")[0]; // Зразковий парсинг імені
        }
        return "Unknown";
    }
}
