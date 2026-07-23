-- Exercise 1: control structures
DECLARE
  v_age NUMBER;
BEGIN
  FOR c IN (SELECT CustomerID, DOB FROM Customers) LOOP
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, c.DOB) / 12);
    IF v_age > 60 THEN
      UPDATE Loans SET InterestRate = GREATEST(InterestRate - 1, 0)
       WHERE CustomerID = c.CustomerID;
    END IF;
  END LOOP;
END;
/

BEGIN
  FOR c IN (SELECT CustomerID, Balance FROM Customers) LOOP
    UPDATE Customers SET IsVIP = CASE WHEN c.Balance > 10000 THEN 'Y' ELSE 'N' END,
                         LastModified = SYSDATE
     WHERE CustomerID = c.CustomerID;
  END LOOP;
END;
/

BEGIN
  FOR l IN (
    SELECT c.Name, l.LoanID, l.EndDate
      FROM Loans l JOIN Customers c ON c.CustomerID = l.CustomerID
     WHERE l.EndDate BETWEEN TRUNC(SYSDATE) AND TRUNC(SYSDATE) + 30
  ) LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder: ' || l.Name || ' loan ' || l.LoanID ||
                         ' is due on ' || TO_CHAR(l.EndDate, 'YYYY-MM-DD'));
  END LOOP;
END;
/
