import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Basic_filecontent_analysis {

    public static void main(String[] args) {
        // Check if the correct number of command line arguments is provided
        if (args.length < 2) {
            System.out.println("directory_path");
            return;
        }

        // Extract directory path and stopwords from command line arguments
        String directory_Path = args[0];
        String[] stop_words = new String[args.length - 1];
        System.arraycopy(args, 1, stop_words, 0, stop_words.length);

        // Call the method to iterate through files in the specified directory
        iterate_Files(directory_Path, stop_words);
    }

    private static void iterate_Files(String directoryPath, String[] stop_words) {
        // Create a File object for the specified directory path
        File directory = new File(directoryPath);

        // Check if the directory exists
        if (!directory.exists()) {
            System.out.println("Not found!");
            return;
        }

        // Check if the provided path is a directory
        if (!directory.isDirectory()) {
            System.out.println("path is not a directory!");
            return;
        }

        // Get the list of files in the directory
        File[] files = directory.listFiles();

        // Check if the directory is not empty
        if (files != null) {
            int totalFiles = 0;

            // Iterate through each file in the directory
            for (File file : files) {
                if (file.isFile()) {
                    totalFiles++;

                    // Display file information and content
                    System.out.println("Full Path: " + file.getAbsolutePath());
                    System.out.println("File Name: " + file.getName());
                    display_FileContent(file);
                    System.out.println("Number of Words: " + countWords(file));
                    display_Word_Frequency(file, stop_words);
                    display_FileContent_Without_Stop_words(file, stop_words);
                    System.out.println("------------------------");
                }
            }

            // Display total number of files processed
            System.out.println("Total Number of Files: " + totalFiles);
        } else {
            System.out.println("Error reading directory contents.");
        }
    }

    private static void display_FileContent(File file) {
        // Display the content of the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            System.out.println("Content of File:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        }
    }

    private static int countWords(File file) {
        // Count the number of words in the file
        int wordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        }

        return wordCount;
    }

    private static void display_Word_Frequency(File file, String[] stopwords) {
        // Display the frequency of each word in the file, excluding stopwords
        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (!isStop_word(word, stopwords)) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        }

        System.out.println("Word Frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static boolean isStop_word(String word, String[] stop_words) {
        // Check if a word is a stopword
        for (String stopword : stop_words) {
            if (word.equalsIgnoreCase(stopword)) {
                return true;
            }
        }
        return false;
    }

    private static void display_FileContent_Without_Stop_words(File file, String[] stop_words) {
        // Display the content of the file excluding stopwords
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            System.out.println("Content without Stop_words:");
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (!isStop_word(word, stop_words)) {
                        System.out.print(word + " ");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        }
    }
}
