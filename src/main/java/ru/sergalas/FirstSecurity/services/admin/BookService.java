package ru.sergalas.FirstSecurity.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergalas.FirstSecurity.entities.book.Book;
import ru.sergalas.FirstSecurity.entities.book.Date;
import ru.sergalas.FirstSecurity.entities.users.entities.User;
import ru.sergalas.FirstSecurity.repositories.BookRepository;
import ru.sergalas.FirstSecurity.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository)
    {
        this.bookRepository = bookRepository;
        this.userRepository =  userRepository;
    }

    public List<Book> findAll(boolean sortYear)
    {
        if(sortYear){
            return bookRepository.findAllByOrderByYear();
        }
        return bookRepository.findAll();
    }

    public List<Book> findPagination(Integer page, Integer booksPerPage, boolean sortByYear)
    {
        if(sortByYear){
            return  bookRepository.findAll(PageRequest.of(page,booksPerPage, Sort.by("year"))).getContent();
        }
        return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
    }

    public Book findOne(Integer id)
    {
        Optional<Book> findBook = bookRepository.findById(id);
        return findBook.orElseThrow(IllegalArgumentException::new);
    }

    public void save(Book book)
    {
        bookRepository.save(book);
    }

    public void delete(Integer id)
    {
        bookRepository.deleteById(id);
    }

    /*public List<Book> getAllUserBook(User user)
    {
        return bookRepository.findByUser(user);
    }*/

    public void deleteUser(int id)
    {
        Book book = this.findOne(id);
        book.deleteUser();
        bookRepository.save(book);
    }

    public void addUser(Integer id, User user)
    {
         bookRepository.findById(id).ifPresent(
                book -> {
                    book.setUser(user);
                    book.setDate(
                            Date.builder()
                            .date_receiving(LocalDate.now())
                            .build());
                }
        );
        ;
    }

    public User showUserFromBookId(Integer book_id)
    {
        return bookRepository.findById(book_id).map(Book::getUser).orElse(null);
    }
}
