package payment;

public class Payment {
	
	boolean paid_status = false;
	public String attemptPayment(Card card, double amt, String key) {
		String msg = "";
		if (amt > 0 && card.getBalance() > amt) {
			card.upDateBalance(-amt, key);
			msg = "success";
			paid_status = true;
		}
		else {
			if(amt <= 0) {
				System.out.println("Cannot enter a negative amount!");
			}
			if(card.getBalance() <= amt) {
				System.out.println("There amount entered exceeds your balance!");
			}
		}
		return msg;
	}
	/*public void refundAmount(Card card, double amt) {
		if (amt > 0 ) {
			card.upDateBalance(+amt);
		}
		else {
			System.out.println("The amount to be refunded cannot be negative!");
		}
	}*/
}
