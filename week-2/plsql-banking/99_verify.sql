SET SERVEROUTPUT ON

DECLARE
  v_age NUMBER;
  v_installment NUMBER;
  v_balance NUMBER;
  v_vip CHAR(1);
BEGIN
  SELECT IsVIP INTO v_vip FROM Customers WHERE CustomerID = 2;
  IF v_vip <> 'Y' THEN RAISE_APPLICATION_ERROR(-20100, 'VIP control-structure check failed'); END IF;

  v_age := CalculateAge(DATE '2000-01-01');
  IF v_age < 20 THEN RAISE_APPLICATION_ERROR(-20101, 'Age function check failed'); END IF;
  v_installment := CalculateMonthlyInstallment(1200, 0, 1);
  IF ABS(v_installment - 100) > 0.01 THEN RAISE_APPLICATION_ERROR(-20102, 'Installment function check failed'); END IF;

  TransferFunds(1, 2, 50);
  SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = 1;
  IF v_balance < 0 THEN RAISE_APPLICATION_ERROR(-20103, 'Transfer check failed'); END IF;

  DBMS_OUTPUT.PUT_LINE('PL/SQL verification checks passed');
END;
/
