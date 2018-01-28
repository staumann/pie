package core.io.implementation;

import core.io.definition.OutPutHandler;

public class DefaultOutputHandlerImpl implements OutPutHandler{

    private int lastPrintedStringLength;
    private long totalPrintCount;

    {
        lastPrintedStringLength = 0;
        totalPrintCount = 0;
    }
    public DefaultOutputHandlerImpl() {
    }

    @Override
    public void printLine(String text) {
        this.calculateLength(text);
        System.out.print(text);
    }

    @Override
    public void replaceLastLine(String text) {
        this.removeFromConsole(lastPrintedStringLength);
        System.out.print(text);
        totalPrintCount = totalPrintCount - lastPrintedStringLength;
        this.lastPrintedStringLength = text.length();
        totalPrintCount = totalPrintCount + lastPrintedStringLength;

    }

    @Override
    public void removeLine() {
       this.removeFromConsole(lastPrintedStringLength);
        totalPrintCount = totalPrintCount - lastPrintedStringLength;
        lastPrintedStringLength = 0;

    }

    @Override
    public void newLine(String text) {
        System.out.println(text);

    }

    @Override
    public void newLine() {
        System.out.println();
    }

    @Override
    public void clear() {
       this.removeFromConsole(totalPrintCount);
       this.totalPrintCount = 0;
    }

    private void calculateLength(String text) {
        this.lastPrintedStringLength = text.length();
        totalPrintCount = totalPrintCount + text.length();
    }

    private void removeFromConsole(long length) {
        for(int i=0; i < length; i++) {
            System.out.print("\b");
        }
        for(int i=0; i < length; i++) {
            System.out.print(" ");
        }
        for(int i=0; i < length; i++) {
            System.out.print("\b");
        }
    }

    @Override
    public void removeLetterFromConsole() {
        System.out.print("\b");
        System.out.print(" ");
        System.out.print("\b");
    }

    public void showLoading() {
        try {
            for(int i = 0; i < 3; i++) {
                java.lang.Thread.sleep(500);
                System.out.print(".");
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
