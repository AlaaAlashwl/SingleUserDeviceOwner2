package com.example.singleuser;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserManager;

public class AdminReceiver extends DeviceAdminReceiver {
    public static ComponentName component(Context context) { return new ComponentName(context, AdminReceiver.class); }

    public static boolean apply(Context context) {
        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName admin = component(context);
        if (!dpm.isDeviceOwnerApp(context.getPackageName())) return false;
        dpm.addUserRestriction(admin, UserManager.DISALLOW_ADD_USER);
        return true;
    }

    @Override public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        apply(context);
    }
}
