package ru.sergalas.FirstSecurity.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sergalas.FirstSecurity.entities.book.Book;
import ru.sergalas.FirstSecurity.entities.users.User;
import ru.sergalas.FirstSecurity.repositories.BookRepository;
import ru.sergalas.FirstSecurity.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookRepository bookRepository)
    {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<User> findAll()
    {
        return  userRepository.findAll();
    }

    public User findOne(Integer id)
    {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseThrow(IllegalArgumentException::new);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(Integer id, User user)
    {
        User person = this.findOne(id);
        user.setPassword(person.getPassword());

        userRepository.save(user);
    }

    public void delete(Integer id)
    {
       userRepository.deleteById(id);
    }

    public List<Book> getAllUserBook(User user)
    {
        return bookRepository.findByUser(user);
    }
}
