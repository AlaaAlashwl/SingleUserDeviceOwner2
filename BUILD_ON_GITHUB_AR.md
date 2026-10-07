# بناء APK بدون Android Studio

1. أنشئ مستودع GitHub جديدًا.
2. ارفع محتويات هذا المجلد إلى المستودع، بما فيها مجلد `.github`.
3. في GitHub افتح تبويب **Actions**.
4. اختر **Build APK** ثم اضغط **Run workflow**.
5. بعد انتهاء البناء افتح التشغيل الناجح ثم قسم **Artifacts**.
6. نزّل `SingleUserDeviceOwner-Android12-APK` وستجد داخله `app-debug.apk`.

## تثبيت APK على OnePlus

```bat
adb install -t app-debug.apk
```

ثم اجعل التطبيق Device Owner:

```bat
adb shell dpm set-device-owner --user 0 com.example.singleuser/.AdminReceiver
```

تحقق:

```bat
adb shell dpm list-owners
```

ثم افتح التطبيق مرة واحدة.

## إزالة Device Owner

```bat
adb shell dpm remove-active-admin --user 0 com.example.singleuser/.AdminReceiver
adb uninstall com.example.singleuser
```

ملاحظة: يجب أن يكون الجهاز في حالة تسمح بإنشاء Device Owner عبر ADB، وقد يمنع وجود حسابات أو إدارة جهاز سابقة عملية التعيين. التطبيق مصمم لـ Android 12 (API 31) ويطبق فقط `DISALLOW_ADD_USER`.
