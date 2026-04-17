@echo off
setlocal
set DIRNAME=%~dp0
set APP_HOME=%DIRNAME%..
set JAVA_HOME=%JAVA_HOME%
if not defined JAVA_HOME (
  set "JAVA_HOME=%APP_HOME%\jre"
)
if defined JAVA_HOME if exist "%JAVA_HOME%\bin\java.exe" (
  set "JAVACMD=%JAVA_HOME%\bin\java.exe"
) else (
  set "JAVACMD=java"
)
set "DEFAULT_JVM_OPTS=-Xmx64m"
if not defined JAVA_OPTS set "JAVA_OPTS=%DEFAULT_JVM_OPTS%"
"%JAVACMD%" %JAVA_OPTS% -classpath "%APP_HOME%\gradle\wrapper\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
