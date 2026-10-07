@echo off
setlocal
if not exist "app-debug.apk" (
  echo ضع app-debug.apk بجوار هذا الملف أولاً.
  exit /b 1
)
adb install -t -r app-debug.apk || exit /b 1
adb shell dpm set-device-owner --user 0 com.example.singleuser/.AdminReceiver || exit /b 1
echo تم تعيين التطبيق كـ Device Owner.
adb shell dpm list-owners
echo افتح التطبيق مرة واحدة للتأكد من الحالة.
