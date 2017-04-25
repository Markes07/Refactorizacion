package refactorizacionTest;

import static org.junit.Assert.*;

import org.junit.Test;

import refactorizacion.Customer;
import refactorizacion.Movie;
import refactorizacion.Rental;

public class StatmentTest {

	@Test
	public void testStatment() {
		Customer customer = new Customer("Fran");
		Customer customer2 = new Customer("Pepe");
		Customer customer3 = new Customer("Lucia");
		
		Movie pel1 = new Movie("Terminator", 0);
		Movie pel2 = new Movie("Matrix II", 1);
		Movie pel3 = new Movie("Intocable", 2);
		
		Rental rental1 = new Rental(pel1, 5);
		Rental rental2 = new Rental(pel2, 7);
		Rental rental3 = new Rental(pel3, 10);
		customer.addRental(rental1);
		customer.addRental(rental2);
		customer.addRental(rental3);
		
		customer2.addRental(rental1);
		customer2.addRental(rental2);
		customer3.addRental(rental3);
		
		String stat1 = customer.statement();
		String stat2 = customer2.statement();
		String stat3 = customer3.statement();
		
		//Tests
		assertEquals(stat1, "Rental Record for Fran\n\tTerminator\t6.5\n"
				+ "\tMatrix II\t21.0\n\tIntocable\t12.0\n"
				+ "Amount owed is 39.5\nYou earned 4 frequent renter points");
		
		assertEquals(stat2, "Rental Record for Pepe\n\tTerminator\t6.5\n"
				+ "\tMatrix II\t21.0\n"
				+ "Amount owed is 27.5\nYou earned 3 frequent renter points");
		
		assertEquals(stat3, "Rental Record for Lucia\n\tIntocable\t12.0\n"
				+ "Amount owed is 12.0\nYou earned 1 frequent renter points");
	}
	
	@Test
	public void testHtmlStatment() {
		Customer customer = new Customer("Fran");
		Customer customer2 = new Customer("Pepe");
		Customer customer3 = new Customer("Lucia");
		
		Movie pel1 = new Movie("Terminator", 0);
		Movie pel2 = new Movie("Matrix II", 1);
		Movie pel3 = new Movie("Intocable", 2);
		
		Rental rental1 = new Rental(pel1, 5);
		Rental rental2 = new Rental(pel2, 7);
		Rental rental3 = new Rental(pel3, 10);
		customer.addRental(rental1);
		customer.addRental(rental2);
		customer.addRental(rental3);
		
		customer2.addRental(rental1);
		customer2.addRental(rental2);
		customer3.addRental(rental3);
		
		String stat1 = customer.htmlStatement();
		String stat2 = customer2.htmlStatement();
		String stat3 = customer3.htmlStatement();
		
		//Tests
		assertEquals(stat1, "<H1>Rentals for <EM>Fran</EM></H1><P>\nTerminator: 6.5<BR>\n"
				+ "Matrix II: 21.0<BR>\nIntocable: 12.0<BR>\n"
				+ "<P>You owe <EM>39.5</EM><P>\nOn this rental you earned <EM>4</EM> frequent renter points<P>");
		
		assertEquals(stat2, "<H1>Rentals for <EM>Pepe</EM></H1><P>\nTerminator: 6.5<BR>\n"
				+ "Matrix II: 21.0<BR>\n"
				+ "<P>You owe <EM>27.5</EM><P>\nOn this rental you earned <EM>3</EM> frequent renter points<P>");
		
		assertEquals(stat3, "<H1>Rentals for <EM>Lucia</EM></H1><P>\n"
				+ "Intocable: 12.0<BR>\n"
				+ "<P>You owe <EM>12.0</EM><P>\nOn this rental you earned <EM>1</EM> frequent renter points<P>");
	}
}