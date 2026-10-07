@echo off
adb shell dpm remove-active-admin --user 0 com.example.singleuser/.AdminReceiver
adb uninstall com.example.singleuser
