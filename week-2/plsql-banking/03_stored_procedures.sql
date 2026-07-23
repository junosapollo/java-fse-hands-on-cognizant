CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
  UPDATE Accounts SET Balance = Balance * 1.01, LastModified = SYSDATE
   WHERE UPPER(AccountType) = 'SAVINGS';
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(p_department VARCHAR2, p_bonus_percentage NUMBER) AS
BEGIN
  UPDATE Employees SET Salary = Salary * (1 + p_bonus_percentage / 100)
   WHERE Department = p_department;
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE TransferFunds(
  p_source_account NUMBER, p_target_account NUMBER, p_amount NUMBER
) AS
  v_balance Accounts.Balance%TYPE;
BEGIN
  IF p_amount <= 0 THEN RAISE_APPLICATION_ERROR(-20010, 'Amount must be positive'); END IF;
  SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_source_account FOR UPDATE;
  IF v_balance < p_amount THEN RAISE_APPLICATION_ERROR(-20011, 'Insufficient balance'); END IF;
  UPDATE Accounts SET Balance = Balance - p_amount, LastModified = SYSDATE WHERE AccountID = p_source_account;
  UPDATE Accounts SET Balance = Balance + p_amount, LastModified = SYSDATE WHERE AccountID = p_target_account;
  IF SQL%ROWCOUNT = 0 THEN RAISE_APPLICATION_ERROR(-20012, 'Target account does not exist'); END IF;
  COMMIT;
END;
/
