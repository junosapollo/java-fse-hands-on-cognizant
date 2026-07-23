# Week 2 — PL/SQL banking exercises

The scripts implement all seven exercise groups and all three scenarios in each group. They use Oracle SQL/PLSQL features such as `DBMS_OUTPUT`, packages, triggers, cursors, `BOOLEAN` functions, identity columns, and `RAISE_APPLICATION_ERROR`.

Run them in order with SQL*Plus or SQLcl:

```text
@00_schema.sql
@01_control_structures.sql
@02_error_handling.sql
@03_stored_procedures.sql
@04_functions.sql
@05_triggers.sql
@06_cursors.sql
@07_packages.sql
@99_verify.sql
```

Oracle is not installed on the development machine, so these scripts are syntax-authored and statically reviewed here; `99_verify.sql` is the runtime acceptance check when an Oracle instance is available.
