@echo off
set "current_dir=%cd%"

:: Vô hiệu hoá tác vụ ngầm mà không cần xoá trong Task Scheduler
@REM schtasks /change /tn "AutoViewer" /disable

:: Xoá tác vụ ngầm trong Task Scheduler
schtasks /delete /tn "AutoViewer" /f

cmd /c