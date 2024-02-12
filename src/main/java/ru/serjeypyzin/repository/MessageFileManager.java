package ru.serjeypyzin.repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class MessageFileManager implements FileMessageRepository{
    @Override
    public void SaveLogToFile(String message, String path) {
        try {
            Path logFilePath = Paths.get(path);

            if (!Files.exists(logFilePath)) {
                Files.createFile(logFilePath);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(logFilePath, StandardOpenOption.APPEND)) {
                writer.write(message + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в лог: " + e.getMessage());
        }
    }

    @Override
    public String ReadLodFromFile(String path) {
        try {
            return Files.lines(Paths.get(path))
                    .collect(Collectors.joining(System.getProperty("line.separator")));
        } catch (IOException e) {
            System.err.println("Ошибка при чтении лога: " + e.getMessage());
            return null;
        }
    }
}
