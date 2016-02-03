package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LoggerPanel extends JPanel
{

    private JTextArea output;

    public LoggerPanel() {
        setLayout(new BorderLayout());
        output = new JTextArea();
        add(new JScrollPane(output));
    }

    public void appendText(final String text) {
        if (EventQueue.isDispatchThread()) {
            output.append(text);
            output.setCaretPosition(output.getText().length());
        } else {

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    appendText(text);
                }
            });

        }
    }
}