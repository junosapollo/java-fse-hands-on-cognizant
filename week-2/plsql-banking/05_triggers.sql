CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
  :NEW.LastModified := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
  INSERT INTO Audit_Log(EventName, EventDetails)
  VALUES ('TRANSACTION_INSERT', 'Transaction ' || :NEW.TransactionID || ' for account ' || :NEW.AccountID);
END;
/

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
  v_balance Accounts.Balance%TYPE;
BEGIN
  IF :NEW.Amount <= 0 THEN RAISE_APPLICATION_ERROR(-20030, 'Transaction amount must be positive'); END IF;
  IF :NEW.TransactionType = 'Withdrawal' THEN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = :NEW.AccountID;
    IF :NEW.Amount > v_balance THEN RAISE_APPLICATION_ERROR(-20031, 'Withdrawal exceeds balance'); END IF;
  END IF;
END;
/
