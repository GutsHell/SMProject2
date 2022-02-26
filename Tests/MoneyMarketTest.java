import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyMarketTest {

	//this method tests to see if a non-loyal customer account returns the respective monthly interest rate
	@Test
	void monthly_interest_is_eight_tenths_percent_when_not_loyal() {
		Date dob = new Date("1/15/2001");
		Profile holder = new Profile("John", "Doe", dob);
		MoneyMarket acc = new MoneyMarket(holder, 2000, 0);

		assertEquals(0.008/12, acc.monthlyInterest());
	}

	//this method tests to see if a loyal customer account returns the respective monthly interest rate
	@Test
	void monthly_interest_is_nine_tenths_and_five_hundreths_percent_when_loyal() {
		Date dob = new Date("1/15/2001");
		Profile holder = new Profile("John", "Doe", dob);
		MoneyMarket acc = new MoneyMarket(holder, 3000, 1);

		assertEquals(0.0095/12, acc.monthlyInterest());
	}
}