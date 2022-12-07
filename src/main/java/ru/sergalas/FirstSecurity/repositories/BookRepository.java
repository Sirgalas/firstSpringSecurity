package ru.sergalas.FirstSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sergalas.FirstSecurity.entities.book.Book;
import ru.sergalas.FirstSecurity.entities.users.entities.User;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public List<Book> findByUser(User user);

    public List<Book> findAllByOrderByYear();
    public List<Book> findAllByOrderByYearDesc();
    public List<Book> findByNameLike(String title);

}
