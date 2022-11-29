package ru.sergalas.FirstSecurity.controllers.admin.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sergalas.FirstSecurity.controllers.admin.BaseAdminController;
import ru.sergalas.FirstSecurity.entities.book.Book;
import ru.sergalas.FirstSecurity.entities.users.User;
import ru.sergalas.FirstSecurity.services.admin.AdminServices;
import ru.sergalas.FirstSecurity.services.admin.BookService;
import ru.sergalas.FirstSecurity.services.admin.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController extends BaseAdminController {

    private final BookService bookService;
    private final UserService userService;


    @Autowired
    public BookController(BookService bookService, UserService userService, AdminServices adminServices) {
        super(adminServices);
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("")
    public String index(
            Model model,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
            @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {
        if(page == null || booksPerPage == null){
            model.addAttribute("books",bookService.findAll(sortByYear));
        } else {
            model.addAttribute("books",bookService.findPagination(page, booksPerPage, sortByYear));
        }

        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("user") User user) {
        model.addAttribute("book",bookService.findOne(id));

       User bookOwner = bookService.showUserFromBookId(id);

        if(bookOwner != null){
            model.addAttribute("owner",bookOwner);
        } else {
            model.addAttribute("users", userService.findAll());
        }
        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "book/new";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book",bookService.findOne(id));
        return "book/edit";
    }

    @PatchMapping("{/id}")
    public String update (@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                          @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookService.save(book);

        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookService.deleteUser(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("user") User selectUser) {
        bookService.addUser(id, selectUser);
        return "redirect:/books/" + id;
    }
}
