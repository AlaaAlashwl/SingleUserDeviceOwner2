# Single User Lock — Android 12

تطبيق صغير جدًا (Java، بدون مكتبات خارجية) مخصص لجهاز Android 12 / API 31.
وظيفته الوحيدة هي منع إنشاء مستخدمين إضافيين باستخدام `UserManager.DISALLOW_ADD_USER` بعد أن يصبح التطبيق Device Owner.

## التثبيت والتفعيل عبر ADB

1. ثبّت ملف APK الناتج من Android Studio/Gradle:

```bat
adb install -t app-debug.apk
```

2. اجعل التطبيق Device Owner على المستخدم 0:

```bat
adb shell dpm set-device-owner --user 0 com.example.singleuser/.AdminReceiver
```

3. تحقق:

```bat
adb shell dpm list-owners
adb shell dumpsys user | findstr /i "no_add_user"
```

يمكن فتح التطبيق مرة واحدة للتأكد من الحالة؛ القيد يُطبّق أيضًا عند تفعيل الـadmin.

## إزالة Device Owner وإلغاء المنع

هذا المشروع معرف `testOnly=true` لتسهيل إدارة جهاز الاختبار بواسطة ADB. لإزالة المالك:

```bat
adb shell dpm remove-active-admin --user 0 com.example.singleuser/.AdminReceiver
```

ثم يمكن إزالة التطبيق:

```bat
adb uninstall com.example.singleuser
```

## ملاحظات

- قبل `set-device-owner` يجب ألا يكون للجهاز Device Owner آخر (وعند بعض طرق provisioning قد تكون هناك شروط إضافية من النظام/OEM).
- هذا التطبيق يمنع **إضافة مستخدمين جدد** فقط، ولا يحذف المستخدمين الموجودين أصلًا.
- لا يحتاج Root.

## البناء السحابي بدون Android Studio

راجع الملف `BUILD_ON_GITHUB_AR.md`. يوجد GitHub Actions workflow جاهز يبني `app-debug.apk` تلقائيًا على Ubuntu باستخدام JDK 17 وAndroid SDK.
