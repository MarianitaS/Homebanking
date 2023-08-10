package com.ap.homebanking;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.models.TransactionType;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepositiry;
import com.ap.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

@Bean
public CommandLineRunner initData (ClientRepositiry clientRepositiry, AccountRepository accountRepository, TransactionRepository transactionRepository){
return( args -> {
	Client client1 = new Client(
			"Melba",
			"Morel",
			"melba@mindhub.com"
	);
	Client client2 = new Client(
			"Mariana",
			"Soto",
			"marianasolcito2012@gmail.com"
	);

	LocalDate today = LocalDate.now();
	LocalDate tomorrow = today.plusDays(1);

	Account account1 = new Account(

			"VIN001",
			today,
			5000
	);
	Account account2 = new Account(

			"VIN002",
			tomorrow,
			7500
	);
	Account account3 = new Account(

			"VIN003",
			tomorrow,
			20000
	);
	Account account4 = new Account(

			"VIN004",
			today,
			72500
	);

	Transaction transaction1 = new Transaction(
			TransactionType.DEBIT,
			-2500,
			"Supermercado",
			LocalDateTime.now()

	);
	Transaction transaction2 = new Transaction(
			TransactionType.CREDIT,
			2500,
			"Transferencia de Mama",
			LocalDateTime.now()

	);
	Transaction transaction3 = new Transaction(
			TransactionType.DEBIT,
			-500,
			"Farmacia",
			LocalDateTime.now()

	);
	Transaction transaction4 = new Transaction(
			TransactionType.CREDIT,
			125000,
			"Sueldo",
			LocalDateTime.now()

	);
	Transaction transaction5 = new Transaction(
			TransactionType.DEBIT,
			-35500,
			"Tarjeta de credito",
			LocalDateTime.now()

	);
	Transaction transaction6 = new Transaction(
			TransactionType.CREDIT,
			65000,
			"Aguinaldo",
			LocalDateTime.now()

	);
	Transaction transaction7 = new Transaction(
			TransactionType.DEBIT,
			-7500,
			"Libreria",
			LocalDateTime.now()

	);
	Transaction transaction8 = new Transaction(
			TransactionType.CREDIT,
			35000,
			"SUAF",
			LocalDateTime.now()

	);

	account1.addTransactions(transaction1);
	account1.addTransactions(transaction2);
	account2.addTransactions(transaction3);
	account2.addTransactions(transaction4);
	account3.addTransactions(transaction5);
	account3.addTransactions(transaction6);
	account4.addTransactions(transaction7);
	account4.addTransactions(transaction8);


	client1.addAccount(account1);
	client1.addAccount(account2);
	client2.addAccount(account3);
	client2.addAccount(account4);


	clientRepositiry.save(client1);
	clientRepositiry.save(client2);

	accountRepository.save(account1);
	accountRepository.save(account2);
	accountRepository.save(account3);
	accountRepository.save(account4);

	transactionRepository.save(transaction1);
	transactionRepository.save(transaction2);
	transactionRepository.save(transaction3);
	transactionRepository.save(transaction4);
	transactionRepository.save(transaction5);
	transactionRepository.save(transaction6);
	transactionRepository.save(transaction7);
	transactionRepository.save(transaction8);

});

}

}
