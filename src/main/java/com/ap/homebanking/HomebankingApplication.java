package com.ap.homebanking;

import com.ap.homebanking.models.*;
import com.ap.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData (ClientRepositiry clientRepositiry,
								   AccountRepository accountRepository,
								   TransactionRepository transactionRepository,
								   LoanRepository loanRepository,
								   ClientLoanRepository clientLoanRepository,
								   CardRepository cardRepository){
	return( args -> {
		Client client1 = new Client(
				"Melba",
				"Morel",
				"melba@mindhub.com",
				passwordEncoder.encode("melba")
		);
		Client client2 = new Client(
				"Mariana",
				"Soto",
				"marianasolcito2012@gmail.com",
				passwordEncoder.encode("123456")
		);

		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);



		Account account1 = new Account(

				"VIN-1",
				today,
				5000
		);
		Account account2 = new Account(

				"VIN-2",
				tomorrow,
				7500
		);
		Account account3 = new Account(

				"VIN-"+getRandomNumber(11111111, 9999999),
				tomorrow,
				20000
		);
		Account account4 = new Account(

				"VIN-"+getRandomNumber(11111111, 9999999),
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
		Loan loan1 = new Loan(
				"Hipotecario",
				500000,
				List.of(12, 24, 36, 48,60)
				);
		Loan loan2 = new Loan(
				"Personal",
				100000,
				List.of(6, 12, 24)
		);
		Loan loan3 = new Loan(
				"Automotriz",
				300000,
				List.of(6, 12, 24, 36)
		);
		ClientLoan clientLoan1 = new ClientLoan(400000,
												60
		);
		ClientLoan clientLoan2 = new ClientLoan(500000,
												12
		);
		ClientLoan clientLoan3 = new ClientLoan(100000,
												24
		);
		ClientLoan clientLoan4 = new ClientLoan(200000,
												36
		);

		LocalDate anio0 = LocalDate.now();
		LocalDate futuro = anio0.plusYears(5);

		Card card1 = new Card(
				CardType.DEBIT,
				CardColor.GOLD,
				getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999),
				getRandomNumber(1, 999),
				futuro,
				anio0
		);
		Card card2 = new Card(
				CardType.CREDIT,
				CardColor.TITANIUM,
				getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999),
				getRandomNumber(1, 999),
				futuro,
				anio0
		);
		Card card3 = new Card(
				CardType.CREDIT,
				CardColor.SILVER,
				getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999)+"-"+getRandomNumber(1111, 9999),
				getRandomNumber(1, 999),
				futuro,
				anio0
		);





		loan1.addClientLoans(clientLoan1);
		loan2.addClientLoans(clientLoan2);
		loan2.addClientLoans(clientLoan3);
		loan3.addClientLoans(clientLoan4);


		client1.addClientLoans(clientLoan1);
		client1.addClientLoans(clientLoan2);
		client2.addClientLoans(clientLoan3);
		client2.addClientLoans(clientLoan4);





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

		client1.addCards(card1);
		client1.addCards(card2);
		client2.addCards(card3);


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

		loanRepository.save(loan1);
		loanRepository.save(loan2);
		loanRepository.save(loan3);

		clientLoanRepository.save(clientLoan1);
		clientLoanRepository.save(clientLoan2);
		clientLoanRepository.save(clientLoan3);
		clientLoanRepository.save(clientLoan4);

		cardRepository.save(card1);
		cardRepository.save(card2);
		cardRepository.save(card3);

	});
}
}
