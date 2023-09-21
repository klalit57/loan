package com.lalit.loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lalit.loan.model.Loan;
import com.lalit.loan.repository.LoanRepository;


@SpringBootApplication
@EnableJpaRepositories
public class LoanApplication {
	

	@Autowired
	LoanRepository loanRepository;
	
	@PostConstruct
	public void initialise() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
		Loan l1 = new Loan("L1","C1","LEN1",10000,10000,dateFormat.parse("05/06/2023"),1,dateFormat.parse("05/07/2023"),0.01);
		loanRepository.save(l1);
		Loan l2 = new Loan("L2","C2","LEN1",20000,5000,dateFormat.parse("01/06/2023"),1,dateFormat.parse("05/08/2023"),0.01);
		loanRepository.save(l2);
		Loan l3 = new Loan("L3","C3","LEN2",50000,30000,dateFormat.parse("04/04/2023"),2,dateFormat.parse("04/05/2023"),0.01);
		loanRepository.save(l3);
		Loan l4 = new Loan("L4","C4","LEN2",50000,30000,dateFormat.parse("04/04/2023"),2,dateFormat.parse("04/05/2023"),0.01);
		loanRepository.save(l4);
		} catch (ParseException e) {
	        e.printStackTrace();
	    }
		
	}

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
