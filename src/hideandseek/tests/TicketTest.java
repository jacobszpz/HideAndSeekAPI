package hideandseek.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import hideandseek.objects.Ticket;

class TicketTest {

	@Test
	void fromStringTest() {
		assertEquals(Ticket.GREEN, Ticket.fromString("green"));
		assertEquals(Ticket.RED, Ticket.fromString("red"));
		assertEquals(Ticket.YELLOW, Ticket.fromString("yellow"));
		assertNull(Ticket.fromString("BLUE"));
	}
	

	@Test
	void toStringTest() {
		assertEquals("green", Ticket.toString(Ticket.GREEN));
		assertEquals("red", Ticket.toString(Ticket.RED));
		assertEquals("yellow", Ticket.toString(Ticket.YELLOW));
	}
}
