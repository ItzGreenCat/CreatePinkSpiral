@echo off
setlocal
for /f "tokens=2 delims=:." %%x in ('chcp') do set _codepage=%%x
chcp 65001>nul
cd C:\Users\greencat.GREENCAT-PC\IdeaProjects\CreatePinkSpiral\run
"C:\Program Files\Java\jdk-21\bin\java.exe" @C:\Users\greencat.GREENCAT-PC\IdeaProjects\CreatePinkSpiral\build\moddev\serverRunClasspath.txt @C:\Users\greencat.GREENCAT-PC\IdeaProjects\CreatePinkSpiral\build\moddev\serverRunVmArgs.txt -Dfml.modFolders=createpinkspiral%%%%C:\Users\greencat.GREENCAT-PC\IdeaProjects\CreatePinkSpiral\build\classes\java\main;createpinkspiral%%%%C:\Users\greencat.GREENCAT-PC\IdeaProjects\CreatePinkSpiral\build\resources\main net.neoforged.devlaunch.Main @C:\Users\greencat.GREENCAT-PC\IdeaProjects\CreatePinkSpiral\build\moddev\serverRunProgramArgs.txt
if not ERRORLEVEL 0 (  echo Minecraft failed with exit code %ERRORLEVEL%  pause)
chcp %_codepage%>nul
endlocal