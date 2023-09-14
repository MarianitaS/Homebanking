package com.ap.homebanking;

import com.ap.homebanking.utils.CardUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;

@SpringBootTest
class HomebankingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void cardNumberIsCreated(){
		String cardNumber = CardUtils.getCardNumber();
		assertThat(cardNumber,is(not(emptyOrNullString())));
	}
	public void cardNumberIsString(){
		String cardNumber = CardUtils.getCardNumber();
		assertThat(cardNumber,any(String.class));
	}
	@Test
	public void carCVVIsCreated(){
		int cvvNumber = CardUtils.getCVV();
		assertThat(cvvNumber,is(not(0)));
	}
@Test
	public void carCVVMax(){
		int cvvNumber = CardUtils.getCVV();
		assertThat(cvvNumber,lessThan( 999));
	}

}
