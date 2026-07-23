CREATE OR REPLACE FUNCTION CalculateAge(p_dob DATE) RETURN NUMBER IS
BEGIN
  RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
  p_loan_amount NUMBER, p_annual_interest_rate NUMBER, p_duration_years NUMBER
) RETURN NUMBER IS
  v_monthly_rate NUMBER := p_annual_interest_rate / 1200;
  v_months NUMBER := p_duration_years * 12;
BEGIN
  IF v_months <= 0 OR p_loan_amount < 0 THEN RAISE_APPLICATION_ERROR(-20020, 'Invalid loan inputs'); END IF;
  IF v_monthly_rate = 0 THEN RETURN p_loan_amount / v_months; END IF;
  RETURN p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months) /
         (POWER(1 + v_monthly_rate, v_months) - 1);
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance(p_account_id NUMBER, p_amount NUMBER) RETURN BOOLEAN IS
  v_balance Accounts.Balance%TYPE;
BEGIN
  SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
  RETURN v_balance >= p_amount;
EXCEPTION
  WHEN NO_DATA_FOUND THEN RETURN FALSE;
END;
/
