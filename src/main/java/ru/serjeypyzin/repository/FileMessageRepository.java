package ru.serjeypyzin.repository;

public interface FileMessageRepository {

    void SaveLogToFile (String message);
    String ReadLogFromFile ();
}
