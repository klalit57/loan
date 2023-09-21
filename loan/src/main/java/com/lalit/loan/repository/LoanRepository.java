package com.lalit.loan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lalit.loan.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {
	
	//Get All Loans (/loans)
	List<Loan> findAll();
	
	//Add loan (/loans/add)
	//inbuilt save method
	
	//get loan by loan id (/loans/{loanId})
	Optional<Loan> findById(String id);
	
	//get loan by customer id (/loans/{customerId)
	List<Loan> findByCustomerID(String id);
		
	//get loans by lenderId (/loans/{lenderId})
	List<Loan> findByLenderID(String id);
	
	//get aggregate loans by Lender (aggregate remaining amount, Interest and Penalty)(/loans/aggregate/lender)
	@Query("SELECT SUM(l.remainingAmount), l.interestPerDay, l.penaltyPerDay FROM Loan l GROUP BY l.lenderID")
    List<Object[]> aggregateLoanByLenderId();
	
    @Query("SELECT SUM(l.remainingAmount), l.interestPerDay, l.penaltyPerDay FROM Loan l GROUP BY l.customerID")
    List<Object[]> aggregateLoanByCustomerId();

    @Query("SELECT SUM(l.remainingAmount), l.interestPerDay, l.penaltyPerDay FROM Loan l GROUP BY l.interestPerDay")
    List<Object[]> aggregateLoanByInterestPerDay();

}
