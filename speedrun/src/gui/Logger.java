package gui;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Logger extends OutputStream {

    private StringBuilder buffer;
    private String prefix;
    private LoggerPanel panel;
    private PrintStream old;

    public Logger(String prefix, LoggerPanel panel, PrintStream old) {
        this.prefix = prefix;
        buffer = new StringBuilder(128);
        buffer.append("[").append(prefix).append("] ");
        this.old = old;
        this.panel = panel;
    }

    @Override
    public void write(int b) throws IOException {
        char c = (char) b;
        String value = Character.toString(c);
        buffer.append(value);
        if (value.equals("\n")) {
            panel.appendText(buffer.toString());
            buffer.delete(0, buffer.length());
            buffer.append("[").append(prefix).append("] ");
        }
        old.print(c);
    }            
}  