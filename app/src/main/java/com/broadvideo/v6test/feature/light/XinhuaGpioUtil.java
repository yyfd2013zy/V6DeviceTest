package com.broadvideo.v6test.feature.light;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class XinhuaGpioUtil {


    public static void RootCommand(String cmd) {
        Process process = null;
        DataOutputStream os = null;
        DataInputStream is = null;
        try {
            process = Runtime.getRuntime().exec("/system/xbin/su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();

            int aa = process.waitFor();
            is = new DataInputStream(process.getInputStream());

            byte[] buffer = new byte[is.available()];
            is.read(buffer);

            //String out = new String(buffer);
            // Log.d(TAG, out + aa);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
    }
}
