package com.lalit.loan.model;

public class AggregatedLoanDTO {
    private Double totalRemainingAmount;
    private Double interestPerDay;
    private Double penaltyPerDay;
	public AggregatedLoanDTO(Double totalRemainingAmount, Double interestPerDay, Double penaltyPerDay) {
		super();
		this.totalRemainingAmount = totalRemainingAmount;
		this.interestPerDay = interestPerDay;
		this.penaltyPerDay = penaltyPerDay;
	}
	public AggregatedLoanDTO() {
		super();
	}
	public Double getTotalRemainingAmount() {
		return totalRemainingAmount;
	}
	public void setTotalRemainingAmount(Double totalRemainingAmount) {
		this.totalRemainingAmount = totalRemainingAmount;
	}
	public Double getInterestPerDay() {
		return interestPerDay;
	}
	public void setInterestPerDay(Double interestPerDay) {
		this.interestPerDay = interestPerDay;
	}
	public Double getPenaltyPerDay() {
		return penaltyPerDay;
	}
	public void setPenaltyPerDay(Double penaltyPerDay) {
		this.penaltyPerDay = penaltyPerDay;
	}
	@Override
	public String toString() {
		return "AggregatedLoanDTO [totalRemainingAmount=" + totalRemainingAmount + ", interestPerDay=" + interestPerDay
				+ ", penaltyPerDay=" + penaltyPerDay + "]";
	}

}
