import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDatabaseTest {

	AccountDatabase database;
	Date dob = new Date("1/15/2001");
	Profile holder = new Profile("John", "Doe", dob);
	Profile holder2 = new Profile("Jane", "Doe", dob);
	Checking checking = new Checking(holder, 1000);
	CollegeChecking collegeChecking = new CollegeChecking(holder, 1000, 0);
	Savings savings = new Savings(holder, 1000, 1);
	MoneyMarket moneyMarket = new MoneyMarket(holder, 3000, 1);

	@BeforeEach
	public void initEach(){
		database = new AccountDatabase();
	}

	@Test
	void open_false_when_opening_two_identical_checking_accounts() {
		database.open(checking);
		assertFalse(database.open(checking));
	}

	@Test
	void open_false_when_opening_two_identical_college_checking_accounts() {
		database.open(collegeChecking);
		assertFalse(database.open(collegeChecking));
	}

	@Test
	void open_false_when_opening_two_identical_saving_accounts() {
		database.open(savings);
		assertFalse(database.open(savings));
	}

	@Test
	void open_false_when_opening_two_identical_money_market_accounts() {
		database.open(moneyMarket);
		assertFalse(database.open(moneyMarket));
	}

	@Test
	void open_false_when_opening_college_checking_account_after_having_regular_checking() {
		database.open(checking);
		assertFalse(database.open(collegeChecking));
	}

	@Test
	void open_false_when_opening_checking_account_after_having_college_checking() {
		database.open(collegeChecking);
		assertFalse(database.open(checking));
	}

	@Test
	void open_true_when_opening_different_accounts_but_same_person() {
		database.open(checking);
		assertTrue(database.open(savings));
		assertTrue(database.open(moneyMarket));
	}

	@Test
	void open_true_when_opening_a_closed_account() {
		database.open(checking);
		database.close(checking);
		assertTrue(database.open(checking));
	}

	@Test
	void close_true_when_account_is_open() {
		database.open(checking);
		database.close(checking);
	}

	@Test
	void close_false_when_account_does_not_exist() {
		assertFalse(database.close(checking));
	}

	@Test
	void close_false_when_account_is_already_closed() {
		database.open(checking);
		database.close(checking);
		assertFalse(database.close(checking));
	}
}