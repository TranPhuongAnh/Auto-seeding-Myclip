@echo off
echo Test tool
start "Run appium" cmd /c "appium --use-plugins=execute-driver --base-path /wd/hub"
start "Run file jar" cmd /c "timeout /t 8 && java -jar AppTest.jar"
cmd /c