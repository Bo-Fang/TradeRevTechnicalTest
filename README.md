# TradeRevTechnicalTest

REQUIREMENTS:
- [Install Latest Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 

HOW TO RUN TEST:

1. Open tradeRevTest folder
2. Edit the RunTest.bat
3. Change projectLocation to directory of the tradRevTest folder i.e. "C:\Users\{USERNAME}\test-workspace\tradeRevTest"
4. Save and click on RunTest.bat

HOW TO SEE TEST RESULTS:

1. Open tradeRevTest/test-output folder
2. Click on index.html
3. Click on Reporter output to see scenario 3 log

Possible Issues:
1. Error: Could not find or load main class org.testng.TestNG 
Solution: Make sure the first line in the RunTest.bat has the correct path to the tradRevTest folder
2. Exception in thread "main" java.lang.UnsupportedClassVersionError: TradeRevTest has been compiled by a more recent version of the Java Runtime (class file version 59.0)
Solution: See link above to install latest Java sdk
