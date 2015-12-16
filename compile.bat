@echo off
echo Deleting prevorious release...
rm -r -f build
mkdir build
echo Compiling...

set path=%path%;C:\Program Files\Java\jdk1.8.0_65\bin

javac -classpath .\src\ -encoding UTF-8 -d .\build\ .\src\rikiki\Rikiki.java
@echo on