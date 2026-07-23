DECLARE
  CURSOR GenerateMonthlyStatements IS
    SELECT a.CustomerID, a.AccountID, t.TransactionID, t.Amount, t.TransactionType
      FROM Accounts a JOIN Transactions t ON t.AccountID = a.AccountID
     WHERE t.TransactionDate >= TRUNC(SYSDATE, 'MM');
BEGIN
  FOR row_data IN GenerateMonthlyStatements LOOP
    DBMS_OUTPUT.PUT_LINE('Customer ' || row_data.CustomerID || ', account ' || row_data.AccountID ||
      ': ' || row_data.TransactionType || ' ' || row_data.Amount);
  END LOOP;
END;
/

DECLARE
  CURSOR ApplyAnnualFee IS SELECT AccountID FROM Accounts;
  c_fee CONSTANT NUMBER := 100;
BEGIN
  FOR row_data IN ApplyAnnualFee LOOP
    UPDATE Accounts SET Balance = Balance - c_fee, LastModified = SYSDATE
     WHERE AccountID = row_data.AccountID AND Balance >= c_fee;
  END LOOP;
  COMMIT;
END;
/

DECLARE
  CURSOR UpdateLoanInterestRates IS SELECT LoanID, InterestRate FROM Loans;
BEGIN
  FOR row_data IN UpdateLoanInterestRates LOOP
    UPDATE Loans SET InterestRate = GREATEST(InterestRate - 0.25, 0) WHERE LoanID = row_data.LoanID;
  END LOOP;
  COMMIT;
END;
/
