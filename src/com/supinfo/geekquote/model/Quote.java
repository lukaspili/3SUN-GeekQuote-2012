package com.supinfo.geekquote.model;

import java.io.Serializable;
import java.util.Date;

public class Quote implements Serializable {

	private Long id;
	private String strQuote;
	private int rating;
	private Date date;

	public Quote(String strQuote, int rating) {
		this(null, strQuote, rating, new Date());
	}

	public Quote(Long id, String strQuote, int rating, Date date) {
		this.id = id;
		this.strQuote = strQuote;
		this.rating = rating;
		this.date = date;
	}

	@Override
	public String toString() {
		return strQuote;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
