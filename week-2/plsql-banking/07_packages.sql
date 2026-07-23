CREATE OR REPLACE PACKAGE CustomerManagement AS
  PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER);
  PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER);
  FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
  PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER) IS
  BEGIN INSERT INTO Customers(CustomerID, Name, DOB, Balance) VALUES (p_id, p_name, p_dob, p_balance); END;
  PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER) IS
  BEGIN UPDATE Customers SET Name = p_name, Balance = p_balance WHERE CustomerID = p_id; END;
  FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER IS v_balance NUMBER;
  BEGIN SELECT Balance INTO v_balance FROM Customers WHERE CustomerID = p_id; RETURN v_balance; END;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE EmployeeManagement AS
  PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_department VARCHAR2, p_hire_date DATE);
  PROCEDURE UpdateEmployee(p_id NUMBER, p_name VARCHAR2, p_salary NUMBER, p_department VARCHAR2);
  FUNCTION AnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
  PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_department VARCHAR2, p_hire_date DATE) IS
  BEGIN INSERT INTO Employees(EmployeeID, Name, Position, Salary, Department, HireDate) VALUES (p_id, p_name, p_position, p_salary, p_department, p_hire_date); END;
  PROCEDURE UpdateEmployee(p_id NUMBER, p_name VARCHAR2, p_salary NUMBER, p_department VARCHAR2) IS
  BEGIN UPDATE Employees SET Name = p_name, Salary = p_salary, Department = p_department WHERE EmployeeID = p_id; END;
  FUNCTION AnnualSalary(p_id NUMBER) RETURN NUMBER IS v_salary NUMBER;
  BEGIN SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_id; RETURN v_salary * 12; END;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE AccountOperations AS
  PROCEDURE OpenAccount(p_id NUMBER, p_customer_id NUMBER, p_type VARCHAR2, p_balance NUMBER);
  PROCEDURE CloseAccount(p_id NUMBER);
  FUNCTION TotalCustomerBalance(p_customer_id NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
  PROCEDURE OpenAccount(p_id NUMBER, p_customer_id NUMBER, p_type VARCHAR2, p_balance NUMBER) IS
  BEGIN INSERT INTO Accounts(AccountID, CustomerID, AccountType, Balance) VALUES (p_id, p_customer_id, p_type, p_balance); END;
  PROCEDURE CloseAccount(p_id NUMBER) IS
  BEGIN DELETE FROM Accounts WHERE AccountID = p_id; END;
  FUNCTION TotalCustomerBalance(p_customer_id NUMBER) RETURN NUMBER IS v_total NUMBER;
  BEGIN SELECT NVL(SUM(Balance), 0) INTO v_total FROM Accounts WHERE CustomerID = p_customer_id; RETURN v_total; END;
END AccountOperations;
/
