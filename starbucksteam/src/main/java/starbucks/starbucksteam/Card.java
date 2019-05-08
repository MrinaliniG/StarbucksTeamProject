package starbucks.starbucksteam;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="card")
public class Card {
	@Id
	private int cardid;
	private int cardcode;
	private String userid;
	private Date cardcreateddate;
	
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getCardcreateddate() {
		return cardcreateddate;
	}
	public void setCardcreateddate(Date cardcreateddate) {
		this.cardcreateddate = cardcreateddate;
	}
	
	
}
