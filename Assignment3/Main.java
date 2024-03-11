import java.util.Scanner;
import searching.BinarySearch;
import searching.LinearSearch;
import sorting.*;
import stack.Stack;
import queue.Queue;
import linkedlist.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Searching");
            System.out.println("2. Sorting");
            System.out.println("3. Stack");
            System.out.println("4. Queue");
            System.out.println("5. LinkedList");
            System.out.println("6. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    searchingOptions();
                    break;
                case 2:
                    sortingOptions();
                    break;
                case 3:
                    stackOptions();
                    break;
                case 4:
                    queueOptions();
                    break;
                case 5:
                    linkedListOptions();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private static void searchingOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a search algorithm:");
        System.out.println("1. Binary Search");
        System.out.println("2. Linear Search");
        int option = scanner.nextInt();

        System.out.println("Enter the size of the array:");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.println("Enter element " + (i + 1) + ":");
            array[i] = scanner.nextInt();
        }
        System.out.println("Enter the target element:");
        int target = scanner.nextInt();

        int index;
        switch (option) {
            case 1:
                index = BinarySearch.search(array, target);
                break;
            case 2:
                index = LinearSearch.search(array, target);
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
        if (index != -1) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found.");
        }
    }

    private static void sortingOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a sorting algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Merge Sort");
        System.out.println("5. Quick Sort");
        int option = scanner.nextInt();

        System.out.println("Enter the size of the array:");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            System.out.println("Enter element " + (i + 1) + ":");
            array[i] = scanner.nextInt();
        }

        switch (option) {
            case 1:
                BubbleSort.sort(array);
                break;
            case 2:
                SelectionSort.sort(array);
                break;
            case 3:
                InsertionSort.sort(array);
                break;
            case 4:
                MergeSort.sort(array);
                break;
            case 5:
                QuickSort.sort(array);
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
        System.out.println("Sorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void stackOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the stack:");
        int size = scanner.nextInt();
        Stack stack = new Stack(size);
        System.out.println("Enter the elements of the stack:");
        for (int i = 0; i < size; i++) {
            System.out.println("Enter element " + (i + 1) + ":");
            int element = scanner.nextInt();
            stack.push(element);
        }
        while (true) {
            System.out.println("Stack Operations:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the element to push:");
                    int element = scanner.nextInt();
                    stack.push(element);
                    break;
                case 2:
                    System.out.println("Popped element: " + stack.pop());
                    break;
                case 3:
                    System.out.println("Peeked element: " + stack.peek());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void queueOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the queue:");
        int size = scanner.nextInt();
        Queue queue = new Queue(size);
        System.out.println("Enter the elements of the queue:");
        for (int i = 0; i < size; i++) {
            System.out.println("Enter element " + (i + 1) + ":");
            int element = scanner.nextInt();
            queue.insert(element);
        }
        while (true) {
            System.out.println("Queue Operations:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek Front");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the element to enqueue:");
                    int element = scanner.nextInt();
                    queue.insert(element);
                    break;
                case 2:
                    System.out.println("Dequeued element: " + queue.remove());
                    break;
                case 3:
                    System.out.println("Front element: " + queue.peekFront());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void linkedListOptions() {
        Scanner scanner = new Scanner(System.in);
        LinkedList linkedList = new LinkedList();

        System.out.println("Enter the size of the LinkedList:");
        int size = scanner.nextInt();
        System.out.println("Enter the elements of the LinkedList:");
        for (int i = 1; i <= size; i++) {
            System.out.println("Enter element " + i + ":");
            int element = scanner.nextInt();
            linkedList.insert(element);
        }

        linkedList.display();

        while (true) {
            System.out.println("LinkedList Operations:");
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at End");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete at Beginning");
            System.out.println("5. Delete at End");
            System.out.println("6. Delete at Position");
            System.out.println("7. Display");
            System.out.println("8. Exit");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the element to insert at the beginning:");
                    int dataBegin = scanner.nextInt();
                    linkedList.insertAtBeginning(dataBegin);
                    break;
                case 2:
                    System.out.println("Enter the element to insert at the end:");
                    int dataEnd = scanner.nextInt();
                    linkedList.insertAtEnd(dataEnd);
                    break;
                case 3:
                    System.out.println("Enter the element to insert:");
                    int data = scanner.nextInt();
                    System.out.println("Enter the position to insert at:");
                    int position = scanner.nextInt();
                    linkedList.insertAtPosition(data, position);
                    break;
                case 4:
                    linkedList.deleteAtBeginning();
                    break;
                case 5:
                    linkedList.deleteAtEnd();
                    break;
                case 6:
                    System.out.println("Enter the position to delete:");
                    int delPosition = scanner.nextInt();
                    linkedList.deleteAtPosition(delPosition);
                    break;
                case 7:
                    linkedList.display();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

            linkedList.display();
        }
    }
}