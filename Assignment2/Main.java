import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class InflectedWordsFinder extends JFrame {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton findInflectedWordsButton;
    private Set<String> stopWords;
    private Stemmer_word stemmer;  // Instance of Stemmer_word

    public InflectedWordsFinder() {
        setTitle("Inflected Words Finder");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputTextArea = new JTextArea();
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLACK)
        ));

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        outputTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.BLACK)
        ));

        findInflectedWordsButton = new JButton("Stem Inflected Words");
        findInflectedWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findInflectedWords();
            }
        });

        // Add common English words to the stopWords set
        stopWords = new HashSet<>(Arrays.asList(
                // Add your stop words here
        ));

        stemmer = new Stemmer_word();  // Initialize Stemmer_word

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(inputTextArea), gbc);

        gbc.gridy++;
        gbc.weighty = 0;
        mainPanel.add(findInflectedWordsButton, gbc);

        gbc.gridy++;
        gbc.weighty = 1;
        mainPanel.add(new JScrollPane(outputTextArea), gbc);

        add(mainPanel);
    }

    private void findInflectedWords() {
        String paragraph = inputTextArea.getText();
        java.util.List<String> words = extractWords(paragraph);
        java.util.List<String> inflectedWords = new ArrayList<>();

        for (String word : words) {
            String rootForm = stemmer.getRootForm(word, stopWords);
            if (!rootForm.equals(word)) {
                inflectedWords.add(word + " --> " + rootForm);
            }
        }

        // Display the results in the outputTextArea
        outputTextArea.setText(String.join("\n", inflectedWords));
    }

    private java.util.List<String> extractWords(String text) {
        return Arrays.asList(text.split("\\s+"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InflectedWordsFinder inflectedWordsFinder = new InflectedWordsFinder();
                inflectedWordsFinder.setVisible(true);
            }
        });
    }
}

class Stemmer_word {
    // Your Stemmer_word class remains unchanged
    // Include the methods from Stemmer_word that are needed for stemming in InflectedWordsFinder
}
