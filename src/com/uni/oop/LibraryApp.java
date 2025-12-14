package com.uni.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private List<Book> books;
    private Scanner appScanner;
    private boolean running;
    private int menuCallCount;

    private void init() {
        appScanner = new Scanner(System.in);
        running = true;
        menuCallCount = 0;
    }

    public LibraryApp() {
        books = new ArrayList<Book>();
        init();
    }

    public LibraryApp(List<Book> books) {
        this.books = new ArrayList<Book>(books);
        init();
    }

    public void run() {
        while (running) {
            showMenu();
            int next_input = Integer.parseInt(appScanner.nextLine());
            switch (next_input) {
                case 1:
                    clearTerminal();
                    printAllBooks();
                    waitForEnter();
                    break;
                case 2:
                    clearTerminal();
                    addNewBook();
                    waitForEnter();
                    break;
                case 3:
                    clearTerminal();
                    searchBooksTitle();
                    waitForEnter();
                    break;
                case 4:
                    clearTerminal();
                    borrowBook();
                    waitForEnter();
                    break;
                case 5:
                    clearTerminal();
                    returnBook();
                    waitForEnter();
                    break;
                case 6:
                    clearTerminal();
                    deleteBookId();
                    waitForEnter();
                    break;
                case 7:
                    clearTerminal();
                    System.out.println("!!!Goodbye!!!");
                    running = false;
                    break;
            }
        }
    }

    private void showMenu() {
        List<String> menuLines = new ArrayList<String>();

        if (menuCallCount == 0) {
            menuLines.add("Welcome to Library App!");
        } else {
            menuLines.add("}---------------{");
            menuLines.add("|               |");
            menuLines.add("|  LIBRARY APP  |");
            menuLines.add("|               |");
            menuLines.add("}---------------{");
        }

        menuLines.add("1. Print all books");
        menuLines.add("2. Add new book");
        menuLines.add("3. Search books by title");
        menuLines.add("4. Borrow a book");
        menuLines.add("5. Return a book");
        menuLines.add("6. Delete a book by id");
        menuLines.add("7. Quit");

        System.out.println(String.join("\n", menuLines));
        menuCallCount++;
    }

    private void clearTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private void waitForEnter() {
        System.out.println("\nPress Enter to return menu...");
        appScanner.nextLine();
    }

    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library");
            return;
        }

        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    private void addNewBook() {
        String title = null;
        String author = null;
        Integer year = null;

        while (title == null) {
            try {
                System.out.println("New book's title:");
                String input = appScanner.nextLine();
                Book temp = new Book();
                temp.setTitle(input);
                title = input;
            } catch (IllegalArgumentException e) {
                System.out.println("!!! " + e.getMessage() + " !!!");
            }
        }

        while (author == null) {
            try {
                System.out.println("New book's author:");
                String input = appScanner.nextLine();
                Book temp = new Book();
                temp.setAuthor(input);
                author = input;
            } catch (IllegalArgumentException e) {
                System.out.println("!!! " + e.getMessage() + " !!!");
            }
        }

        while (year == null) {
            try {
                System.out.println("New book's year:");
                int input = Integer.parseInt(appScanner.nextLine());
                Book temp = new Book();
                temp.setYear(input);
                year = input;
            } catch (NumberFormatException e) {
                System.out.println("!!! Year must be a number !!!");
            } catch (IllegalArgumentException e) {
                System.out.println("!!! " + e.getMessage() + " !!!");
            }
        }

        Book newBook = new Book(title, author, year);
        books.add(newBook);

        System.out.println("Successfully added:");
        System.out.println(newBook.toString());
    }

    private void searchBooksTitle() {
        List<String> books_found = new ArrayList<String>();
        System.out.println("Search book's title");
        String input = appScanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(input.toLowerCase())) {
                books_found.add(book.getTitle());
            }
        }

        if (books_found.isEmpty()) {
            System.out.println("No book(s) founded with title " + input);
        } else {
            System.out.println("Found results for " + input + ":");
            System.out.println(String.join("\n", books_found));
        }
    }

    private void borrowBook() {
        System.out.println("Book's id:");
        int input = requestId();
        boolean founded = false;

        for (Book book : books) {
            if (book.getId() == input) {
                founded = true;
                if (book.isAvailable()) {
                    book.markAsBorrowed();
                    System.out.println("Successfully borrowed book: " + book.toString());
                } else {
                    System.out.println("!!! The book is already borrowed !!!");
                }
            }
        }

        if (!founded) {
            System.out.println("Cannot find book with id: " + input);
        }
    }

    private void returnBook() {
        System.out.println("Book's id:");
        int input = requestId();
        boolean founded = false;

        for (Book book : books) {
            if (book.getId() == input) {
                founded = true;
                if (!book.isAvailable()) {
                    book.markAsReturned();
                    System.out.println("Successfully returned book: " + book.toString());
                } else {
                    System.out.println("!!! The book is available !!!");
                }
                break;
            }
        }

        if (!founded) {
            System.out.println("Cannot find book with id: " + input);
        }
    }

    private void deleteBookId() {
        System.out.println("Book's id:");
        int input = requestId();
        boolean founded = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == input) {
                System.out.println("Successfully deleted book: " + books.get(i));
                books.remove(i);
                founded = true;
                break;
            }
        }


        if (!founded) {
            System.out.println("Cannot find book with id: " + input);
        }
    }

    private int requestId() {
        Integer input = null;
        while (input == null) {
            try {
                int temp = Integer.parseInt(appScanner.nextLine());
                if (temp < 0) {
                    System.out.println("Id cannot be negative!");
                } else {
                    input = temp;
                }
            } catch (NumberFormatException e) {
                System.out.println("Id should be a positive integer!");
            }
        }
        return input;
    }
}
