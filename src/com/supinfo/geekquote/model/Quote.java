package com.supinfo.geekquote.model;

import java.io.Serializable;
import java.util.Date;

public class Quote implements Serializable {

	private String strQuote;
	private int rating;
	private Date date;

	public Quote(String strQuote) {
		this.strQuote = strQuote;
		this.date = new Date();
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
