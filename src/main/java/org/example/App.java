package org.example;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Publisher;
import org.example.entity.Reader;
import org.example.enums.Gender;
import org.example.enums.Genre;
import org.example.service.serviceImpl.AuthorServiceImpl;
import org.hibernate.HibernateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {


        try {
            Author author1 = new Author("Aigerim", "Avaz", "ai@wd", 2005, "USA", Gender.FEMALE, new ArrayList<>(), new ArrayList<>());
            Author author2 = new Author("Adel", "Avz", "i@wd", 2010, "USA", Gender.FEMALE, new ArrayList<>(), new ArrayList<>());

            Publisher publisher1 = new Publisher("Publisher1", "address1", new ArrayList<>(), new ArrayList<>());
            Publisher publisher2 = new Publisher("Publisher2", "address2", new ArrayList<>(), new ArrayList<>());

            Book book1 = new Book("Book1", "Country1", 1010, 322, Genre.Action, null, author1, publisher1);
            Book book2 = new Book("Book2", "Country2", 1019, 342, Genre.Action, null, author1, publisher1);

            List<Book> books = new ArrayList<>();
            books.add(book1);
            books.add(book2);

            Reader reader = new Reader("Name", 23, "sdf", books);
            reader.getBook().add(book1);
            reader.getBook().add(book2);

            // Add books to authors and publishers

            publisher1.getBooks().add(book1);
            publisher2.getBooks().add(book2);

            // Add authors and publishers to books
            book1.setAuthor(author1);
            book1.setPublisher(publisher1);
            book2.setAuthor(author2);
            book2.setPublisher(publisher2);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1 - Author");
                System.out.println("2 - Book");
                System.out.println("3 - Publisher");
                System.out.println("4 - Reader");
                System.out.println("0 - Exit");
                System.out.print("Select an option: ");
                int x = scanner.nextInt();
                scanner.nextLine(); // Чтение новой строки после ввода числа

                switch (x) {
                    case 1:
                        AuthorServiceImpl authorService = new AuthorServiceImpl();
                        while (true) {
                            System.out.println("SELECT AN OPTION:");
                            System.out.println("1. SAVE AUTHOR");
                            System.out.println("2. UPDATE AUTHOR");
                            System.out.println("3. GET AUTHOR BY ID");
                            System.out.println("4. GET AUTHORS BY PUBLISHER ID");
                            System.out.println("5. DELETE AUTHOR BY ID");
                            System.out.println("0. EXIT");
                            int choice = scanner.nextInt();
                            scanner.nextLine(); // Чтение новой строки после ввода числа

                            switch (choice) {
                                case 1:
                                    Author author3 = new Author();
                                    System.out.println("Enter first name:");
                                    author3.setFirst_name(scanner.next());
                                    System.out.println("Enter last name:");
                                    author3.setLast_name(scanner.next());
                                    System.out.println("Enter email:");
                                    author3.setEmail(scanner.next());
                                    System.out.println("Enter date of birth (yyyy):");
                                    author3.setDate_of_birth(scanner.nextInt());
                                    scanner.nextLine(); // Чтение новой строки после ввода числа
                                    System.out.println("Enter country:");
                                    author3.setCountry(scanner.next());
                                    System.out.println("Enter gender (MALE/FEMALE):");
                                    author3.setGender(Gender.valueOf(scanner.next().toUpperCase()));

                                    String result = authorService.saveAuthor(author3);
                                    System.out.println(result);
                                    break;
                                case 2:
                                    System.out.println("ENTER AUTHOR ID");
                                    System.out.println("Enter author ID:");
                                    long id = scanner.nextLong();
                                    Author newAuthor1 = new Author();
                                    System.out.println("Enter first name:");
                                    newAuthor1.setFirst_name(scanner.next());
                                    System.out.println("Enter last name:");
                                    newAuthor1.setLast_name(scanner.next());
                                    System.out.println("Enter email:");
                                    newAuthor1.setEmail(scanner.next());
                                    System.out.println("Enter date of birth (yyyyMMdd):");
                                    newAuthor1.setDate_of_birth(scanner.nextInt());
                                    scanner.nextLine(); // Чтение новой строки после ввода числа
                                    System.out.println("Enter country:");
                                    newAuthor1.setCountry(scanner.next());
                                    System.out.println("Enter gender (MALE/FEMALE):");
                                    newAuthor1.setGender(Gender.valueOf(scanner.next().toUpperCase()));
                                    newAuthor1.setId(id);

                                    authorService.updateAuthor(id, newAuthor1);
                                    break;
                                case 3:
                                    System.out.println("ENTER THE ID:");
                                    Long authorId = scanner.nextLong();
                                    Author authorById = authorService.getAuthorById(authorId);
                                    System.out.println(authorById);
                                    break;
                                case 4:
                                    System.out.println("Enter publisher ID:");
                                    Long publisherId = scanner.nextLong();
                                    Author authorsByPublisherId = authorService.getAuthorsByPublisherId(publisherId);
                                    System.out.println(authorsByPublisherId);
                                    break;
                            }
                        }
                }
            }
        }catch (HibernateException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}