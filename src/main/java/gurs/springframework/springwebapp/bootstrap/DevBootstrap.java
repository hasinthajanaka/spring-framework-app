package gurs.springframework.springwebapp.bootstrap;

import gurs.springframework.springwebapp.model.Author;
import gurs.springframework.springwebapp.model.Book;
import gurs.springframework.springwebapp.model.Publisher;
import gurs.springframework.springwebapp.repositories.AuthorRepository;
import gurs.springframework.springwebapp.repositories.BookRepository;
import gurs.springframework.springwebapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.awt.desktop.AppForegroundListener;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();;
    }

    private void initData() {

        //publisher
        Publisher bookPublisher = new Publisher();
        bookPublisher.setName("foo");
        bookPublisher.setAddress("ac street name");
        publisherRepository.save(bookPublisher);

        // Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", bookPublisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Book ejb = new Book("J2EE Development with EJB", "12332", bookPublisher);
        rod.getBooks().add(ejb);
        ejb.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(ejb);
    }
}
