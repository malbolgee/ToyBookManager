package com.malbolge.bookmanager.database.models;

import javafx.beans.property.SimpleStringProperty;

public class Member {

	private final int mId;
	private final SimpleStringProperty mFirstName;
	private final SimpleStringProperty mLastName;
	private final SimpleStringProperty mCpf;
	private final SimpleStringProperty mPhone;
	private final SimpleStringProperty mEmail;
	private final SimpleStringProperty mSex;

	public Member(int id, String firstName, String lastName, String cpf, String phone, String email, String sex) {
		this.mId = id;
		this.mFirstName = new SimpleStringProperty(firstName);
		this.mLastName = new SimpleStringProperty(lastName);
		this.mCpf = new SimpleStringProperty(cpf);
		this.mPhone = new SimpleStringProperty(phone);
		this.mEmail = new SimpleStringProperty(email);
		this.mSex = new SimpleStringProperty(sex);
	}

	public Member(String firstName, String lastName, String cpf, String phone, String email, String sex) {
		this(-1, firstName, lastName, cpf, phone, email, sex);
	}

	public int getId() {
		return this.mId;
	}

	public String getName() {
		return this.mFirstName.get() + " " + this.mLastName.get();
	}

	public String getFirstName() {
		return this.mFirstName.get();
	}

	public String getLastName() {
		return this.mLastName.get();
	}

	public String getCpf() {
		return this.mCpf.get();
	}

	public String getPhone() {
		return this.mPhone.get();
	}

	public String getEmail() {
		return this.mEmail.get();
	}

	public String getSex() {
		return this.mSex.get();
	}

	@Override
	public String toString() {
		return "First Name: " + this.mFirstName.get() + "\n" +
				"Last Name: " + this.mLastName.get() + "\n" +
				"CPF: " + this.mCpf.get() + "\n" +
				"Phone: " + this.mPhone.get() + "\n" +
				"Email: " + this.mEmail.get() + "\n" +
				"Sex: " + this.mSex.get();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;

		if (other instanceof Member) {
			Member o = (Member) other;
			return this.mCpf.get().equals(o.getCpf());
		}
		return false;
	}
}
