package com.example.singleuser;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.os.Bundle;
import android.os.UserManager;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        TextView tv = new TextView(this);
        tv.setTextSize(18);
        tv.setPadding(40, 60, 40, 40);
        DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        boolean owner = dpm.isDeviceOwnerApp(getPackageName());
        if (owner) {
            AdminReceiver.apply(this);
            tv.setText("Device Owner: نعم\\n\\nتم منع إضافة مستخدمين جدد.\\nالقيد: no_add_user");
        } else {
            tv.setText("التطبيق ليس Device Owner بعد.\\n\\nنفّذ أمر dpm set-device-owner من ADB ثم افتح التطبيق مرة واحدة.");
        }
        setContentView(tv);
    }
}
