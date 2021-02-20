set projectLocation=C:\Users\{USERNAME}\test-workspace\tradeRevTest
java -cp %projectLocation%\lib\*;%projectLocation%\bin org.testng.TestNG %projectLocation%\testng.xml
pause
