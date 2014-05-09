package com.meutesouro.entity;

import java.util.Date;

/**
 * Titulos Publicos
 * 
 */
public class MoneyTitle {

	private String mName;
	private Date mExpiredDate;
	private TitleTax  mCurrentTitleTax;
	private TitleTax mAnualTitleTax;
	private boolean mFavorite;
	
	public String getName() {
		return mName;
	}
	public MoneyTitle setName(String name) {
		this.mName = name;
		
		return this;
	}
	public Date getExpiredDate() {
		return mExpiredDate;
	}
	public MoneyTitle setExpiredDate(Date expiredDate) {
		this.mExpiredDate = expiredDate;
		
		return this;
	}
	public TitleTax getCurrentTitleTax() {
		return mCurrentTitleTax;
	}
	public MoneyTitle setCurrentTitleTax(TitleTax currentTitleTax) {
		this.mCurrentTitleTax = currentTitleTax;
		
		return this;
	}
	public TitleTax getAnualTitleTax() {
		return mAnualTitleTax;
	}
	public MoneyTitle setAnualTitleTax(TitleTax anualTitleTax) {
		this.mAnualTitleTax = anualTitleTax;
		
		return this;
	}
	public boolean isFavorite() {
		return mFavorite;
	}
	public MoneyTitle setFavorite(boolean favorite) {
		this.mFavorite = favorite;
		
		return this;
	}
	
}
