package com.malbolge.bookmanager.database.models;

import com.malbolge.bookmanager.utils.TextUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Book {
	private final int mId;
	private final SimpleStringProperty mTitle;
	private final SimpleStringProperty mAuthor;
	private final SimpleStringProperty mPublisher;
	private final SimpleIntegerProperty mEdition;
	private final SimpleStringProperty mIsbn;
	private final SimpleIntegerProperty mAmount;

	public Book(int id, int amount, String title, String author, String publisher, int edition, String isbn) {
		this.mId = id;
		this.mAmount = new SimpleIntegerProperty(amount <= 0 ? 1 : amount);
		this.mTitle = new SimpleStringProperty(throwIfEmpty(TextUtils.nullToEmpty(title)));
		this.mAuthor = new SimpleStringProperty(throwIfEmpty(TextUtils.nullToEmpty(author)));
		this.mPublisher = new SimpleStringProperty(throwIfEmpty(TextUtils.nullToEmpty(publisher)));
		this.mEdition = new SimpleIntegerProperty(edition <= 0 ? 1 : edition);
		this.mIsbn = new SimpleStringProperty(throwIfEmpty(TextUtils.nullToEmpty(isbn)));
	}

	public Book(String title, String author, String publisher, int edition, String isbn) {
		this(-1, 1, title, author, publisher, edition, isbn);
	}

	public int getId() {
		return this.mId;
	}

	public String getTitle() {
		return this.mTitle.get();
	}

	public void setTitle(@Nonnull final String title) {
		this.mTitle.set(title);
	}

	public String getAuthor() {
		return this.mAuthor.get();
	}

	public void setAuthor(@Nonnull final String author) {
		this.mAuthor.set(author);
	}

	public String getPublisher() {
		return this.mPublisher.get();
	}

	public void setPublisher(@Nonnull final String publisher) {
		this.mPublisher.set(publisher);
	}

	public int getEdition() {
		return this.mEdition.get();
	}

	public void setEdition(final int edition) {
		this.mEdition.set(edition);
	}

	public String getIsbn() {
		return this.mIsbn.get();
	}

	public void setIsbn(@Nonnull final String isbn) {
		this.mIsbn.set(isbn);
	}

	public int getAmount() {
		return this.mAmount.get();
	}

	public void setAmount(final int amount) {
		this.mAmount.set(amount);
	}

	@Nonnull
	private String throwIfEmpty(@Nullable final String str) {
		if (TextUtils.isEmpty(str))
			throw new IllegalArgumentException("Argument cannot be empty");
		return str;
	}

	@Override
	public String toString() {
		return "Title: " + this.mTitle.get() + "\n" +
				"Author: " + this.mAuthor.get() + "\n" +
				"Publisher: " + this.mPublisher.get() + "\n" +
				"Edition: " + this.mEdition.get() + "\n" +
				"ISBN: " + this.mIsbn.get() + "\n" +
				"amount: " + this.mAmount.get();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;

		if (other instanceof Book) {
			Book o = (Book) other;
			return this.mIsbn.get().equals(o.getIsbn());
		}
		return false;
	}
}
