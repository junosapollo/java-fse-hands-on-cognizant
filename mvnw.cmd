@echo off
where mvn >nul 2>nul
if %ERRORLEVEL% == 0 (
  mvn %*
  exit /b %ERRORLEVEL%
)
set MAVEN_VERSION=3.9.9
set MAVEN_CACHE_DIR=.maven
set MAVEN_HOME=%MAVEN_CACHE_DIR%\apache-maven-%MAVEN_VERSION%
if not exist "%MAVEN_HOME%\bin\mvn.cmd" (
  echo Maven is not installed. Run mvnw on a Unix-like environment or install Maven 3.9.9.
  exit /b 1
)
call "%MAVEN_HOME%\bin\mvn.cmd" %*
