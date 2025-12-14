    package com.uni.oop;

    import java.time.Year;

    public class Book {
        private int id;
        private static int idGen;
        private String title;
        private String author;
        private int year;
        private boolean available;

        public Book() {
            id = idGen++;
            available = true;
        }

        public Book(String title, String author, int year) {
            this();
            setTitle(title);
            setAuthor(author);
            setYear(year);
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setAuthor(String author) {
            if (author != null && !author.isBlank()) {
                this.author = author;
            }
            else {
                throw new IllegalArgumentException("Author must  be specified");
            }
        }

        public void setTitle(String title) {
             if (title != null && !title.isBlank()) {
                 this.title = title;
             }
             else {
                 throw new IllegalArgumentException("Title must  be specified");
             }
        }

        public void setYear(int year) {
            if (300 <= year && year <= Year.now().getValue()) {
                this.year = year;
            }
            else {
                throw new IllegalArgumentException("Year is outside of range");
            }
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public int getId() {
            return id;
        }

        public int getYear() {
            return year;
        }

        public String getAuthor() {
            return author;
        }

        public String getTitle() {
            return title;
        }

        public boolean isAvailable() {
            return available;
        }

        public void markAsBorrowed() {
            setAvailable(false);
        }

        public void markAsReturned() {
            setAvailable(true);
        }

        @Override
        public String toString() {
            return String.format("Book id: %d, Title: %s, Author: %s, Year: %d, Available: %b", id, title, author, year, available);
        }
    }
