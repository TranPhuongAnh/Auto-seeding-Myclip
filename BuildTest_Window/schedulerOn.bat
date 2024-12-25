@echo off
set "current_dir=%cd%"

:: Tạo tác vụ ngầm trong Task Scheduler: Tên task là SeedingApp, mỗi 1p sẽ chạy file run.bat 1 lần
schtasks /create /tn "SeedingApp" /tr "%current_dir%/run.bat" /sc minute /mo 1

cmd /c