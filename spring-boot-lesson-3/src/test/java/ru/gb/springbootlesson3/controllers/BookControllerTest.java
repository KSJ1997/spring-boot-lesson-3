package ru.gb.springbootlesson3.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.services.BookService;

import java.util.Arrays;
import java.util.List;
import ru.gb.springbootlesson3.entity.User;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetBookById() throws Exception {
        long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");

        when(bookService.getBookById(bookId)).thenReturn(book);

        mockMvc.perform(get("/book/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    public void testDeleteBookById() throws Exception {
        long bookId = 1L;

        mockMvc.perform(delete("/book/{id}", bookId))
                .andExpect(status().isNoContent());


        verify(bookService).deleteBookById(bookId);
    }

    private BookService verify(BookService bookService2) {
        throw new UnsupportedOperationException("Unimplemented method 'verify'");
    }

    @Test
    public void testCreateBook() throws Exception {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("New Book");

        Book createdBook = new Book();
        createdBook.setId(1L);
        createdBook.setTitle("New Book");

        when(bookService.createBook(any(BookRequest.class))).thenReturn(createdBook);

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"New Book\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("New Book"));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Book 2"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        long bookId = 1L;
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("Updated Book");

        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setTitle("Updated Book");

        when(bookService.updateBook(bookId, bookRequest)).thenReturn(updatedBook);

        mockMvc.perform(put("/book/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Updated Book\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.title").value("Updated Book"));
    }
}
