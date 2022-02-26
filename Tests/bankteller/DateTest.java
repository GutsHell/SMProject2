package bankteller;

import bankteller.Date;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

	//this method takes an invalid year before 1900
	@Test
	void isValid_false_when_year_before_1900() {
		Date date = new Date("2/10/1899");

		assertFalse(date.isValid());
	}

	//this method takes an invalid feb 29th date on a non-leap year
	@Test
	void isValid_false_when_feb_29_and_not_leap_year() {
		Date date = new Date("2/29/2001");

		assertFalse(date.isValid());
	}

	//this method takes a valid feb 29th date on a leap year
	@Test
	void isValid_true_when_feb_29_and_leap_year() {
		Date date = new Date("2/29/2004");

		assertTrue(date.isValid());
	}

	//this method takes invalid dates that is not between 1 and 30
	@Test
	void isValid_false_when_day_is_not_1_to_30() {
		Date date1 = new Date("1/0/2000");
		Date date2 = new Date ("1/32/2000");

		assertFalse(date1.isValid());
		assertFalse(date2.isValid());
	}

	//this method takes invalid months that is not between 1 and 12
	@Test
	void isValid_false_when_month_is_not_1_to_12() {
		Date date1 = new Date("0/15/2000");
		Date date2 = new Date("13/15/2000");

		assertFalse(date1.isValid());
		assertFalse(date2.isValid());
	}

	//this method takes invalid 31st dates on non extended months
	@Test
	void isValid_false_when_31st_and_not_extended_month() {
		Date date1 = new Date("2/31/2000");
		Date date2 = new Date("4/31/2000");
		Date date3 = new Date("6/31/2000");
		Date date4 = new Date("9/31/2000");
		Date date5 = new Date("11/31/2000");

		assertFalse(date1.isValid());
		assertFalse(date2.isValid());
		assertFalse(date3.isValid());
		assertFalse(date4.isValid());
		assertFalse(date5.isValid());
	}

	// this method takes valid 31st dates on extended months
	@Test
	void isValid_true_when_31st_and_extended_month() {
		Date date1 = new Date("1/31/2000");
		Date date2 = new Date("3/31/2000");
		Date date3 = new Date("5/31/2000");
		Date date4 = new Date("7/31/2000");
		Date date5 = new Date("8/31/2000");
		Date date6 = new Date("10/31/2000");
		Date date7 = new Date("12/31/2000");

		assertTrue(date1.isValid());
		assertTrue(date2.isValid());
		assertTrue(date3.isValid());
		assertTrue(date4.isValid());
		assertTrue(date5.isValid());
		assertTrue(date6.isValid());
		assertTrue(date7.isValid());
	}

	//this method takes a valid date after 1900 and also has a valid month and day
	@Test
	void isValid_true_when_year_after_1900_and_valid_month_and_day() {
		Date date = new Date("1/15/1901");

		assertTrue(date.isValid());
	}

}