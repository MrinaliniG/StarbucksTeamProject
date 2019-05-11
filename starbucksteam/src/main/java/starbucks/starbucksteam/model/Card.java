package starbucks.starbucksteam.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="card")
//@SequenceGenerator(name="CARD_SEQUENCE_GENERATOR", sequenceName="CARD_SEQUENCE", initialValue=123467890, allocationSize=1)
public class Card {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CARD_SEQUENCE_GENERATOR")
	private int cardid;
	
	private int cardcode;
	
	private Date cardcreateddate;
	private double balance;
	
	 @ManyToOne
	 @JoinColumn(name = "email")
	 @JsonIgnore
	 private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getCardid() {
		return cardid;
	}
	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
	public int getCardcode() {
		return cardcode;
	}
	public void setCardcode(int cardcode) {
		this.cardcode = cardcode;
	}
	/*public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}*/
	public Date getCardcreateddate() {
		return cardcreateddate;
	}
	public void setCardcreateddate(Date cardcreateddate) {
		this.cardcreateddate = cardcreateddate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
