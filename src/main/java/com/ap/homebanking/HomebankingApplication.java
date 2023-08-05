package com.ap.homebanking;

import com.ap.homebanking.models.Client;
import com.ap.homebanking.repositories.ClientRepositiry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.CookieManager;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

@Bean
public CommandLineRunner initData (ClientRepositiry clientRepositiry){
return( args -> {
	Client client1 = new Client(
			1,
			"Melba",
			"Morel",
			"melba@mindhub.com");
	Client client2 = new Client(
			2,
			"Mariana",
			"Soto",
			"marianasolcito2012@gmail.com"
	);

clientRepositiry.save(client1);
clientRepositiry.save(client2);

});

}

}
