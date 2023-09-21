package com.lalit.loan.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lalit.loan.exception.CustomerIdException;
import com.lalit.loan.exception.DueDateExpiredException;
import com.lalit.loan.exception.LenderIdException;
import com.lalit.loan.exception.LoanIdException;
import com.lalit.loan.model.AggregatedLoanDTO;
import com.lalit.loan.model.Loan;
import com.lalit.loan.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {
	private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/")
    public List<Loan> getAllLoans() {
        return loanService.findAll();
    }
	
	@PostMapping("/add")
	public ResponseEntity<String> saveLoan(@Valid @RequestBody Loan loan, BindingResult bindingResult) throws DueDateExpiredException {
	    if (bindingResult.hasErrors()) {
	        // Handle validation errors
	        return ResponseEntity.badRequest().body("Validation failed");
	    }
	    // Process the entity
	    Date paymentDate = loan.getPaymentDate();
	    Date dueDate = loan.getDueDate();
	    if(dueDate.before(paymentDate)) {
	    	  logger.warn("Loan is overdue",loan.getLoanID());
	    	  throw new DueDateExpiredException("Due date is expired, Loan is rejected");
	    }
	    
	    loanService.saveLoan(loan);    
	    return ResponseEntity.ok("Entity saved successfully");
	}
	
	@GetMapping("/loanId/{loanId}")
	public  Loan getLoanByLoanId(@PathVariable String loanId) throws LoanIdException , DueDateExpiredException{
		Loan loan = loanService.findById(loanId);
		Date paymentDate = loan.getPaymentDate();
	    Date dueDate = loan.getDueDate();
		if(dueDate.before(paymentDate)) {
	    	  logger.warn("Loan is overdue",loan.getLoanID());
	    	  throw new DueDateExpiredException("Due date is expired, Loan is rejected");
	    }
	    return loan;
	}

	
	@GetMapping("/customerId/{customerId}")
	public  List<Loan> getLoanByCustomerId(@PathVariable String customerId) throws CustomerIdException {
	    List<Loan> loans = loanService.findByCustomerID(customerId);
	    return loans;
	}
	
	@GetMapping("/lenderId/{lenderId}")
	public  List<Loan> getLoanByLenderId(@PathVariable String lenderId) throws LenderIdException {
	    List<Loan> loans = loanService.findByLenderID(lenderId);
	    return loans;
	}
	
	@GetMapping("/aggregate/lender")
	public  List<AggregatedLoanDTO> getAggregateLoanByLender(){
		List<AggregatedLoanDTO> loans = loanService.aggregateLoanByLender();  
	    return loans;
	}
	
	@GetMapping("/aggregate/customer")
	public  List<AggregatedLoanDTO> getAggregateLoanByCustomer(){
		List<AggregatedLoanDTO> loans = loanService.aggregateLoanByCustomer();
	    
	    return loans;
	}
	
	@GetMapping("/aggregate/interest")
	public  List<AggregatedLoanDTO> getAggregateLoanByInterest(){
		List<AggregatedLoanDTO> loans = loanService.aggregateLoanByInterest(); 
	    return loans;
	}
	
}
