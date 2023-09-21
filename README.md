# loan
Assignment for Loan Application

Step 1:
Download the file, extract it, and import the Project as Maven in Eclipse.

Step 2:
Run the Application as Java Application

Step 3:
check all endpoints or uri in Postman

uri - 1 -
http://localhost:8080/loans,
GET

uri - 2 -
http://localhost:8080/loans/add
method Type - POST
Body- Json

{
    "loanID":"L5",
    "customerID":"C4",
    "lenderID":"LEN3",
    "amount":40000.0,
    "remainingAmount":40000.0,
    "paymentDate":"2024-01-31",
"interestPerDay":2.0,
"dueDate":"2024-01-31",
"penaltyPerDay":0.01
}

uri - 3 -
http://localhost:8080/loans/loanId/L1,
GET

uri - 4
http://localhost:8080/loans/customerId/C1,
GET

uri - 5
http://localhost:8080/loans/lenderId/LEN1,
GET

uri - 6
http://localhost:8080/loans/lender,
GET

uri - 7
http://localhost:8080/loans/customer,
GET

uri - 8
http://localhost:8080/loans/interest,
GET

Step - 4
check all Junit test cases for LoanServiceTest
