package com.ap.homebanking;

import com.ap.homebanking.models.Account;
import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.AccountRepository;
import com.ap.homebanking.repositories.ClientRepositiry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

@Bean
public CommandLineRunner initData (ClientRepositiry clientRepositiry, AccountRepository accountRepository){
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




client1.addAcount(account1);
client1.addAcount(account2);
client2.addAcount(account3);
client2.addAcount(account4);


clientRepositiry.save(client1);
clientRepositiry.save(client2);

accountRepository.save(account1);
accountRepository.save(account2);
accountRepository.save(account3);
accountRepository.save(account4);

});

}

}
