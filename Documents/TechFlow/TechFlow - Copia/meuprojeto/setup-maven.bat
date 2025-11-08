@echo off
echo ==========================================
echo   Configurando Maven Local para TechFlow
echo ==========================================
echo.

REM Criar diretório para Maven local
if not exist "tools" mkdir tools
cd tools

REM Baixar Maven se não existir
if not exist "apache-maven-3.9.5" (
    echo Baixando Maven 3.9.5...
    powershell -Command "Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.zip' -OutFile 'maven.zip'"
    
    echo Extraindo Maven...
    powershell -Command "Expand-Archive -Path 'maven.zip' -DestinationPath '.'"
    del maven.zip
)

cd ..

REM Configurar PATH temporário
set MAVEN_HOME=%CD%\tools\apache-maven-3.9.5
set PATH=%MAVEN_HOME%\bin;%PATH%

echo.
echo ==========================================
echo   Maven configurado localmente!
echo   MAVEN_HOME: %MAVEN_HOME%
echo ==========================================
echo.

REM Testar Maven
mvn -version

echo.
echo Para usar Maven nesta sessão, execute:
echo set MAVEN_HOME=%CD%\tools\apache-maven-3.9.5
echo set PATH=%%MAVEN_HOME%%\bin;%%PATH%%

pause
