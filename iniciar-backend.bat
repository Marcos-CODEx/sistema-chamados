@echo off
echo Iniciando o servidor Spring Boot...
cd /d "%~dp0sistema-chamados"
mvnw.cmd spring-boot:run
pause