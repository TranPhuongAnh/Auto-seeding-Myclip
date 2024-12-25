@echo off

:: Mở cửa sổ CMD và cài đặt Appium
start "Install Appium" cmd /c "echo Installing Appium... && npm install -g appium && appium -v && echo Appium installed successfully."

:: Mở cửa sổ CMD và kiểm tra, cài đặt uiautomator2 driver
start "Install Appium UiAutomator2 driver" cmd /c "timeout /t 50 && echo Installing uiautomator2 driver... && appium driver install uiautomator2 || echo UiAutomator2 driver already installed && appium driver list --installed"

:: Mở cửa sổ CMD và kiểm tra, cài đặt xcuitest driver
start "Install Appium XCUITest driver" cmd /c "timeout /t 50 && echo Installing xcuitest driver... && appium driver install xcuitest || echo XCUITest driver already installed && appium driver list --installed"

:: Mở cửa sổ CMD và kiểm tra, cài đặt execute-driver plugin
start "Install Appium execute-driver plugin" cmd /c "timeout /t 50 && echo Installing execute-driver plugin... && appium plugin install execute-driver || echo execute-driver plugin already installed && appium plugin list --installed"

cmd /c