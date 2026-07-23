CREATE OR REPLACE PROCEDURE SafeTransferFunds(
  p_source_account NUMBER, p_target_account NUMBER, p_amount NUMBER
) AS
  v_balance Accounts.Balance%TYPE;
BEGIN
  IF p_amount <= 0 THEN RAISE_APPLICATION_ERROR(-20001, 'Transfer amount must be positive'); END IF;
  SAVEPOINT transfer_start;
  SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_source_account FOR UPDATE;
  IF v_balance < p_amount THEN RAISE_APPLICATION_ERROR(-20002, 'Insufficient funds'); END IF;
  UPDATE Accounts SET Balance = Balance - p_amount, LastModified = SYSDATE WHERE AccountID = p_source_account;
  UPDATE Accounts SET Balance = Balance + p_amount, LastModified = SYSDATE WHERE AccountID = p_target_account;
  IF SQL%ROWCOUNT = 0 THEN RAISE_APPLICATION_ERROR(-20003, 'Target account does not exist'); END IF;
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK TO transfer_start;
    INSERT INTO Error_Log(ProcedureName, ErrorMessage) VALUES ('SafeTransferFunds', SQLERRM);
    COMMIT;
    RAISE;
END;
/

CREATE OR REPLACE PROCEDURE UpdateSalary(p_employee_id NUMBER, p_percentage NUMBER) AS
BEGIN
  UPDATE Employees SET Salary = Salary * (1 + p_percentage / 100) WHERE EmployeeID = p_employee_id;
  IF SQL%ROWCOUNT = 0 THEN RAISE_APPLICATION_ERROR(-20004, 'Employee does not exist'); END IF;
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    INSERT INTO Error_Log(ProcedureName, ErrorMessage) VALUES ('UpdateSalary', SQLERRM);
    COMMIT;
    RAISE;
END;
/

CREATE OR REPLACE PROCEDURE AddNewCustomer(
  p_customer_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER
) AS
BEGIN
  INSERT INTO Customers(CustomerID, Name, DOB, Balance) VALUES (p_customer_id, p_name, p_dob, p_balance);
  COMMIT;
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    INSERT INTO Error_Log(ProcedureName, ErrorMessage) VALUES ('AddNewCustomer', 'Customer ID already exists');
    COMMIT;
    RAISE_APPLICATION_ERROR(-20005, 'Customer ID already exists');
END;
/
