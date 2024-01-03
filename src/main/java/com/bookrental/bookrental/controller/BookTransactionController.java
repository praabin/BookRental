package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.model.BookTransaction;
import com.bookrental.bookrental.pojo.rent.BookRentRequest;
import com.bookrental.bookrental.pojo.returnn.BookReturnRequest;
import com.bookrental.bookrental.pojo.trasaction.BookTransactionResponse;
import com.bookrental.bookrental.schedular.OverdueBookEmailScheduler;
import com.bookrental.bookrental.service.booktransaction.BookTransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booktransaction")
public class BookTransactionController {
    private final BookTransactionService bookTransactionService;
    private final OverdueBookEmailScheduler scheduler;


    public BookTransactionController(BookTransactionService bookTransactionService, OverdueBookEmailScheduler scheduler) {
        this.bookTransactionService = bookTransactionService;
        this.scheduler = scheduler;
    }

    @PostMapping("/rent")
    public ResponseEntity<BookTransaction> createTransaction(@RequestBody @Valid BookRentRequest bookRentRequest) {
        bookTransactionService.addBookTransaction(bookRentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/return")
    public ResponseEntity<BookTransaction> returnTransaction(@RequestBody BookReturnRequest bookRentRequest) {
        bookTransactionService.returnBookTransaction(bookRentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookTransactionResponse>> getAllTransaction() {
        return ResponseEntity.ok(bookTransactionService.getAllTransaction());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookTransactionResponse> getSingleTransaction(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookTransactionService.getSingleTransactionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookTransaction> delete(@PathVariable("id") Integer id) {
        bookTransactionService.deleteTransactionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<List<BookTransactionResponse>> getAllTransactionByMemberId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(bookTransactionService.getAllTransactionByMember(id));
    }

//    @GetMapping("/overdeu")
//    public ResponseEntity<String> triggerOverdueEmails() {
//        scheduler.triggerOverdueEmailsManually();
//        return ResponseEntity.ok("Overdue emails triggered manually.");
//    }
}
