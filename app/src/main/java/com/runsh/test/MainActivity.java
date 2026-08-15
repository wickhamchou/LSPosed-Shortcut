package com.runsh.test;

import android.app.Activity;
import android.os.Bundle;
import java.io.DataOutputStream;
import java.io.InputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            InputStream input = getAssets().open("action.sh");

            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();

            Process process = Runtime.getRuntime().exec("su");

            DataOutputStream output =
                    new DataOutputStream(process.getOutputStream());

            output.write(buffer);
            output.writeBytes("\nexit\n");
            output.flush();

            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }

        finish();
    }
}