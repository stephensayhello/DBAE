package de.classes;

import java.util.List;

public class Warenkorb {
	/**
	 * Eine Liste von Produkten im Warenkorb.
	 */
	private List<Produkte> inhalt;
	/**
	 * Der Account, der den Warenkorb bestellt.
	 */
	private Account account;
	
	public List<Produkte> getInhalt() {
		return inhalt;
	}

	public void setInhalt(List<Produkte> inhalt) {
		this.inhalt = inhalt;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
