package core.io.definition;

public interface OutPutHandler {

    void printLine(String text);

    void replaceLastLine(String text);

    void removeLine();

    void newLine(String text);

    void newLine();

    void clear();

    void removeLetterFromConsole();
}
