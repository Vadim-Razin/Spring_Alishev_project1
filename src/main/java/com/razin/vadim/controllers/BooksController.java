package com.razin.vadim.controllers;

import com.razin.vadim.dao.BookDAO;
import com.razin.vadim.dao.PersonBookDAO;
import com.razin.vadim.dao.PersonDAO;
import com.razin.vadim.models.Book;
import com.razin.vadim.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonBookDAO personBookDAO;
    private final PersonDAO personDAO;
//    private final BookValidator bookValidator;

    @Autowired // необязательно, но читабельность на мой взгляд повышается
    public BooksController(BookDAO bookDAO, PersonBookDAO personBookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personBookDAO = personBookDAO;
        this.personDAO = personDAO;
    }

/*    @Autowired // необязательно, но читабельность на мой взгляд повышается
    public PeopleController(BookDAO bookDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
    }*/

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("personbook", personBookDAO.showByBookId(id));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
//        bookValidator.validate(book, bindingResult);
        // в bindingResult будут складываться ошибки и из @Valid и из Валидатора
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
//        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
}

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        personBookDAO.release(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/assign")
    public String assign(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personBookDAO.assign(id, person);
        return "redirect:/books";
    }
}
