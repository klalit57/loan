package com.lalit.loan.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lalit.loan.exception.CustomerIdException;
import com.lalit.loan.exception.LenderIdException;
import com.lalit.loan.exception.LoanIdException;
import com.lalit.loan.model.AggregatedLoanDTO;
import com.lalit.loan.model.Loan;
import com.lalit.loan.repository.LoanRepository;

@Service
@Transactional
public class LoanService {
	
	@Autowired
	private LoanRepository loanRepository;

	public List<Loan> findAll() {
		return loanRepository.findAll();
	}
	
	public void saveLoan(Loan loan) {	
		loanRepository.save(loan);
	}

	public Loan findById(String id) throws LoanIdException {
		Optional<Loan> loan = null;
		loan =  loanRepository.findById(id);
			if(loan.isEmpty()) {
				throw new LoanIdException("Loan not found with loanId : " + id +"  Loan id should be L followed by some digit");
			}
		return loan.get();
	}
	
	public List<Loan> findByCustomerID(String id) throws CustomerIdException{
		List<Loan> loans = null;
			loans =  loanRepository.findByCustomerID(id);
		if(loans.isEmpty()) {
			throw new CustomerIdException("Loan not found with customerId : " + id +" Customer id should be C followed by some digit");
		}
		return loans;
	}
	
	public List<Loan> findByLenderID(String id) throws LenderIdException {
		
		List<Loan> loans = null;
		
			loans =  loanRepository.findByLenderID(id);
			if(loans.isEmpty()) {
			throw new LenderIdException("Loan not found with lenderId : " + id +" Lender id should be LEN followed by some digit");
			}
		return loans;
	}
	
	public List<AggregatedLoanDTO> aggregateLoanByLender(){
		List<Object[]> loans = loanRepository.aggregateLoanByLenderId();
		
		List<AggregatedLoanDTO> aggregatedLoans = new ArrayList<>();
		
		for (Object[] loanData : loans) {
	        AggregatedLoanDTO dto = new AggregatedLoanDTO();
	        dto.setTotalRemainingAmount((Double) loanData[0]);
	        dto.setInterestPerDay((Double) loanData[1]);
	        dto.setPenaltyPerDay((Double) loanData[2]);
	        aggregatedLoans.add(dto);
	    }
		return aggregatedLoans;
	}
	
	public List<AggregatedLoanDTO> aggregateLoanByCustomer(){
		List<Object[]> loans = loanRepository.aggregateLoanByCustomerId();
		
        List<AggregatedLoanDTO> aggregatedLoans = new ArrayList<>();
		
		for (Object[] loanData : loans) {
	        AggregatedLoanDTO dto = new AggregatedLoanDTO();
	        dto.setTotalRemainingAmount((Double) loanData[0]);
	        dto.setInterestPerDay((Double) loanData[1]);
	        dto.setPenaltyPerDay((Double) loanData[2]);
	        aggregatedLoans.add(dto);
	    }
		return aggregatedLoans;   
	}
	
	public List<AggregatedLoanDTO> aggregateLoanByInterest(){
		List<Object[]> loans = loanRepository.aggregateLoanByInterestPerDay();
		
List<AggregatedLoanDTO> aggregatedLoans = new ArrayList<>();
		
		for (Object[] loanData : loans) {
	        AggregatedLoanDTO dto = new AggregatedLoanDTO();
	        dto.setTotalRemainingAmount((Double) loanData[0]);
	        dto.setInterestPerDay((Double) loanData[1]);
	        dto.setPenaltyPerDay((Double) loanData[2]);
	        aggregatedLoans.add(dto);
	    }
		return aggregatedLoans; 
	}
	
}
