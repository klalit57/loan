package com.lalit.loan;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.lalit.loan.exception.CustomerIdException;
import com.lalit.loan.exception.LenderIdException;
import com.lalit.loan.exception.LoanIdException;
import com.lalit.loan.model.AggregatedLoanDTO;
import com.lalit.loan.model.Loan;
import com.lalit.loan.repository.LoanRepository;
import com.lalit.loan.service.LoanService;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoanServiceTests {

	@Autowired
	private LoanService loanService;
	
	@MockBean
    private LoanRepository loanRepository;
	
	public List<Loan> getListOfLoan() throws ParseException{
		List<Loan> list = new ArrayList<Loan>();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	    Loan l1 = new Loan("L1","C1","LEN1",10000,10000,dateFormat.parse("05/06/2023"),1,dateFormat.parse("05/07/2023"),0.01);
		Loan l2 = new Loan("L2","C2","LEN1",20000,5000,dateFormat.parse("01/06/2023"),1,dateFormat.parse("05/08/2023"),0.01);
		Loan l3 = new Loan("L3","C3","LEN2",50000,30000,dateFormat.parse("04/04/2023"),2,dateFormat.parse("04/05/2023"),0.01);
		Loan l4 = new Loan("L4","C4","LEN2",50000,30000,dateFormat.parse("04/04/2023"),2,dateFormat.parse("04/05/2023"),0.01);
		list.add(l1);
	    list.add(l2);
	    list.add(l3);
	    list.add(l4);
	    }catch (ParseException e) {
	        throw e;
	    }
	    return list;
	}
	
	 @Test
	 public void findAllTest() throws ParseException {
	    List<Loan> list = getListOfLoan();
	    when(loanRepository.findAll()).thenReturn(list);
	    List<Loan> resList = loanService.findAll();
	    assertEquals(4, resList.size());
	  }

	  @Test
	  public void saveLoanTest() throws ParseException {
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  Loan l1=null;
		    try {
		    l1 = new Loan("L1","C1","LEN1",10000,10000,dateFormat.parse("05/06/2023"),1,dateFormat.parse("05/07/2023"),0.01);
		    }catch (ParseException e) {
		        throw e;
		    }
		    loanService.saveLoan(l1);
		    verify(loanRepository,times(1)).save(l1);	    
	  }
	  
	  @Test
	  public void findByLoanIdtest() throws ParseException, LoanIdException {
		    List<Loan> list = getListOfLoan();
		    when(loanRepository.findById("L1")).thenReturn(Optional.of(list.get(0)));
		    Optional<Loan> resList = Optional.of(loanService.findById("L1"));
		    assertEquals("L1", resList.get().getLoanID()); 
	  }
	  
	  @Test
	  public void findByCustomerIDTest() throws ParseException, CustomerIdException {	
		    List<Loan> list = new ArrayList<Loan>();
		    list.add(getListOfLoan().get(0));
		    when(loanRepository.findByCustomerID("C1")).thenReturn(list);
		    List<Loan> resList = loanService.findByCustomerID("C1");
		    assertEquals("C1", resList.get(0).getCustomerID());
		  }
	     
	   @Test
	   public void findByLenderIDTest() throws LenderIdException, ParseException {	
		    List<Loan> list = new ArrayList<Loan>();
		    list.add(getListOfLoan().get(2));
		    list.add(getListOfLoan().get(3));
		    when(loanRepository.findByLenderID("LEN2")).thenReturn(list);
		    List<Loan> resList = loanService.findByLenderID("LEN2");
		    assertEquals(2, resList.size());
		  }
	     
	     @Test
		 public void aggregateLoanByLenderTest(){
	    	List<Object[]> list = new ArrayList<>();
	    	Object [] o1 = {30000.0,1.0,0.1};
	    	Object [] o2 = {100000.0,2.0,0.1};
	    	list.add(o1 );
	    	list.add(o2 );
		    when( loanRepository.aggregateLoanByLenderId() ).thenReturn( list );
		    List<AggregatedLoanDTO> resList = loanService.aggregateLoanByLender();
		    assertEquals(2, resList.size());
		  }
	     
	     @Test
		 public void aggregateLoanByCustomerTest(){
	    	List<Object[]> list = new ArrayList<>();
	    	Object [] o1 = {10000.0,1.0,0.1};
	    	Object [] o2 = {20000.0,1.0,0.1};
	    	Object [] o3 = {50000.0,2.0,0.1};
	    	Object [] o4 = {50000.0,2.0,0.1};
	    	list.add(o1 );
	    	list.add(o2 );
	    	list.add(o3 );
	    	list.add(o4 );
		    when( loanRepository.aggregateLoanByCustomerId() ).thenReturn( list );
		    List<AggregatedLoanDTO> resList = loanService.aggregateLoanByCustomer();
		    assertEquals(4, resList.size());
		  }
	     
	     @Test
		 public void aggregateLoanByInterestTest(){
	    	List<Object[]> list = new ArrayList<>();
	    	Object [] o1 = {30000.0,1.0,0.1};
	    	Object [] o2 = {100000.0,2.0,0.1};
	    	list.add(o1 );
	    	list.add(o2 );
		    when( loanRepository.aggregateLoanByInterestPerDay() ).thenReturn( list );
		    //test
		    List<AggregatedLoanDTO> resList = loanService.aggregateLoanByInterest();
		    assertEquals(2, resList.size());
		  }
	       
}
