package com.lalit.loan.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Loan {
	
	@Id
	@NotNull
	@Pattern(regexp = "L[0-9]{1}", message = "invalid loan Id. It should start with L follwed by number")
	@Column(name="loan_id")
	private String loanID;
	
	@NotNull
	@Pattern(regexp = "C[0-9]{1}", message = "invalid customer Id. it should start with C folled by some number")
	@Column(name="customer_id")
	private String customerID;
	
	@NotNull
	@Pattern(regexp = "LEN[0-9]{1}", message = "invalid lender Id. It should start with LEN followed by some number")
	@Column(name="lender_id")
	private String lenderID;
	
	@Min(value = 1, message = "Amount should be more than 0")
	@Column(name="amount")
	private double amount;
	
	@Column(name="remaining_amount")
	private double remainingAmount;
		
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="payment_date")
	private Date paymentDate;
	
	@Column(name="interest_Per_Day")
	private double interestPerDay;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="due_date")
	private Date dueDate;
	
	@Column(name="penalty_per_day")
	private double penaltyPerDay;
	
	@Column(name="cancel")
	private String cancel;
	
	public Loan(@NotNull String loanID, @NotNull String customerID, @NotNull String lenderID, int amount,
			int remainingAmount, Date paymentDate, double interestPerDay, Date dueDate, double penaltyPerDay,
			String cancel) {
		super();
		this.loanID = loanID;
		this.customerID = customerID;
		this.lenderID = lenderID;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.paymentDate = paymentDate;
		this.interestPerDay = interestPerDay;
		this.dueDate = dueDate;
		this.penaltyPerDay = penaltyPerDay;
		this.cancel = cancel;
	}

	public Loan(@NotNull String loanID, @NotNull String customerID, @NotNull String lenderID, int amount,
			int remainingAmount, Date paymentDate, double interestPerDay, Date dueDate, double penaltyPerDay) {
		super();
		this.loanID = loanID;
		this.customerID = customerID;
		this.lenderID = lenderID;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.paymentDate = paymentDate;
		this.interestPerDay = interestPerDay;
		this.dueDate = dueDate;
		this.penaltyPerDay = penaltyPerDay;
	}
	
	public Loan() {
		super();
	}


	public String getLoanID() {
		return loanID;
	}

	public void setLoanID(String loanID) {
		this.loanID = loanID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getLenderID() {
		return lenderID;
	}

	public void setLenderID(String lenderID) {
		this.lenderID = lenderID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getInterestPerDay() {
		return interestPerDay;
	}

	public void setInterestPerDay(double interestPerDay) {
		this.interestPerDay = interestPerDay;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public double getPenaltyPerDay() {
		return penaltyPerDay;
	}

	public void setPenaltyPerDay(double penaltyPerDay) {
		this.penaltyPerDay = penaltyPerDay;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

}
