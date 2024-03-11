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
            "a", "an", "the", "and", "but", "or", "for", "nor", "so", "yet",
            "I", "you", "he", "she", "it", "we", "they",
            "is", "am", "are", "was", "were",
            "has", "have", "had",
            "do", "does", "did", "having", "his", 
            "this", "that", "these", "those",
            "my", "your", "his", "her", "its", "our", "their",
            "me", "him", "us", "them",
            "with", "at", "by", "from", "into", "on", "to", "in", "out", "over", "under",
            "of", "up", "down", "about", "above", "below", "through", "between", "among",
            "all", "any", "each", "few", "more", "most", "other", "some", "such", "no", "yes",
            "here", "there", "now", "then",
            "very", "more", "most", "less",
            "like", "unlike", "similar", "different",
            "as", "than", "so", "too", "enough",
            "before", "after", "while", "during", "since", "until",
            "because", "if", "unless", "while", "though", "although", "even though", "whether", "as if", "as though",
            "within", "without",
            "about", "above", "against", "around", "at", "before", "behind", "below", "beneath", 
            "beside", "between", "beyond", "by", "down", "for", "from", "in", "inside", "into", "near", 
            "off", "on", "onto", "out", "outside", "past", "through", "toward", "under", "upon", 
            "in front of", "across", "throughout", "amongst", "amid", "besides", "when", "As", "yes", "used", "like", "often", "then", "has", "us"
            // Add more as needed
        ));

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
            String rootForm = getRootForm(word);
            if (!rootForm.equals(word)) {
                inflectedWords.add(word + " --> " + rootForm);
            }
        }

        // Display the results in the outputTextArea
        outputTextArea.setText(String.join("\n", inflectedWords));
    }

    private String getRootForm(String word) {
        if (stopWords.contains(word.toLowerCase())) {
            return word;
        }

        // Add your custom logic for handling specific cases
        if (word.endsWith("s") && word.length() > 1) {
            return handlePluralNoun(word);
        } else if (word.endsWith("es") && word.length() > 2) {
            return handlePluralNoun(word);
        } else if (word.endsWith("ing") && word.length() > 3) {
            return handleVerbProgressive(word);
        } else if (word.endsWith("ed") && word.length() > 2) {
            return handleVerbPastTense(word);
        } else if (word.endsWith("en") && word.length() > 2) {
            return handleVerbPastParticiple(word);
        } else if (word.endsWith("'s") && word.length() > 2) {
            return handleGenitiveCase(word);
        } else {
            return word;
        }
    }

    private String handlePluralNoun(String word) {
        return word.substring(0, word.length() - 1);
    }

    private String handleVerbProgressive(String word) {
        return word.substring(0, word.length() - 3);
    }

    private String handleVerbPastTense(String word) {
        return word.endsWith("ied") ? word.substring(0, word.length() - 3) + "y" : word.substring(0, word.length() - 2);
    }

    private String handleVerbPastParticiple(String word) {
        return word.substring(0, word.length() - 2);
    }

    private String handleGenitiveCase(String word) {
        return word.endsWith("'s") ? word.substring(0, word.length() - 2) : word;
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
