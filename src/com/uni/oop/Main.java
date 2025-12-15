package com.uni.oop;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(){
        List<Book> testBooks = new ArrayList<>();

        testBooks.add(new Book("Clean Code", "Robert C. Martin", 2008));
        testBooks.add(new Book("Effective Java", "Joshua Bloch", 2018));
        testBooks.add(new Book("Design Patterns", "Erich Gamma", 1994));
        testBooks.add(new Book("Introduction to Algorithms", "Thomas H. Cormen", 2009));
        testBooks.add(new Book("The Pragmatic Programmer", "Andrew Hunt", 1999));

        LibraryApp app = new LibraryApp(testBooks);
        app.run();
    }
}
