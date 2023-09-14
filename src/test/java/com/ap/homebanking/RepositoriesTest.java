package com.ap.homebanking;

import com.ap.homebanking.controllers.AccountController;
import com.ap.homebanking.dtos.AccountDto;
import com.ap.homebanking.models.*;
import com.ap.homebanking.repositories.*;
import com.ap.homebanking.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoriesTest {

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepositiry clientRepositiry;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    TransactionRepository transactionRepository;

    //Loan
    @Test
    public void existLoans(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,is(not(empty())));
    }
    @Test
    public void existPersonalLoan(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("Hipotecario"))));
    }
    //Account
    @Test
    public void accountNumber(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts, hasItem(hasProperty("number", startsWith("VIN"))));
    }
    @Test
    public void maxAccount(){
        List<Client> accounts = clientRepositiry.findAll();
        int sizze = accounts.get(1).getAccounts().size();
        assertThat(sizze, lessThan(3));
    }
    //Card
    @Test
    public void maxCardType(){
        List<Card> cards = cardRepository.findAll();
        int cardType = cards.get(1).getType().ordinal();
               assertThat(cardType, lessThan(2));
    }
    @Test
    public void cardType(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, hasItem(hasProperty("cvv",lessThan(999))));
    }
    //Client
    @Test
    public void getEmail(){
        List<Client> client =  clientRepositiry.findAll();
         String email = client.get(1).getEmail();
        assertThat(email, containsString("@"));
    }
    @Test
    public void password(){
        List<Client> clients = clientRepositiry.findAll();
        assertThat(clients, hasItem(hasProperty("password", notNullValue())));
    }
//Transaction
@Test
public void transactinType(){
    String transaction = transactionRepository.findAll().get(1).getType().toString();
           assertThat(transaction, anyOf(containsString("DEBIT"),containsString("CREDIT")));
}
    @Test
    public void amount(){
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions, hasItem(hasProperty("amount", isA(Double.class))));
    }


}

