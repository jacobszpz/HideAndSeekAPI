package hideandseek;

import java.io.IOException;

import hideandseek.objects.Status;
import hideandseek.objects.Ticket;

@SuppressWarnings("unused")
public class Test {

	public static void main(String[] args) throws IOException {
		HideAndSeek.development = true;		
		HideAndSeek.setSessionID("9D20AD3EA7AFF11AEBFC871FFBC29FB3");
		
		
		HideAndSeek.makeMove(5, Ticket.YELLOW);
		//HideAndSeek.joinGame("Charles", "1114");
		//System.out.println(HideAndSeek.getSessionID());

		//HideAndSeek.fetchDrXLog();
//		HideAndSeek.fetchGameState();
//		
//		System.out.print(HideAndSeek.getGameState());
		

	}

}
