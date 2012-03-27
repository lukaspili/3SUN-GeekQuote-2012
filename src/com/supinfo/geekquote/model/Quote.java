package com.supinfo.geekquote.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Quote implements Serializable {

	private String strQuote;
	private int rating;
	private Date date;

	public Quote(String strQuote) {
		this.strQuote = strQuote;
		this.date = new Date();
		this.rating = new Random().nextInt(5);
	}
	
	@Override
	public String toString() {
		return strQuote;
	}

	public String getStrQuote() {
		return strQuote;
	}

	public void setStrQuote(String strQuote) {
		this.strQuote = strQuote;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
