REM o script deve estar prsente na raiz do projeto para iniciar corretamente

@echo off
title Chute Certo - Carregando...

REM Cria a pasta 'out' caso não exista
REM if not exist out mkdir out

REM Compila o programa apontando para a pasta de saída
REM javac -d out EsqueletoRPG.java

REM INICIA EM NOVA JANELA
REM -cp out: define onde o Java deve procurar as classes
REM start cmd /k "java -cp out EsqueletoRPG"

REM exit

REM java -jar ChuteCerto.jar
REM pause

@echo off
:: Muda o foco para a pasta onde o arquivo .bat está executando
cd /d "%~dp0"

:: Abre uma nova janela do CMD executando o Java e mantém aberta (/k)
start cmd /k "java -jar ChuteCerto.jar"