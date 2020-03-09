package com.example.vibrate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText input;
    String text, rand;
    Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);

        input = findViewById(R.id.input);

//        rand = generate();

        findViewById(R.id.encode).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                text = input.getText().toString().trim();
//                text = rand;
                String code = morse(text);
                if (!check(code))
                    Toast.makeText(MainActivity.this, morse(code.charAt(0)), Toast.LENGTH_SHORT).show();
                else {
                    long[] time = new long[2 * code.length()];
                    int[] amp = new int[2 * code.length()];
                    for (int i = 0; i < code.length(); i++) {
                        time[2 * i] = 100L;
                        amp[2 * i] = 0;
                        amp[2 * i + 1] = 50;
                        if (code.charAt(i) == '.') time[2 * i + 1] = 120L;
                        else if (code.charAt(i) == '-') time[2 * i + 1] = 400L;
                        else {
                            time[2 * i + 1] = 400L;
                            amp[2 * i + 1] = 0;
                        }
                    }

                    Toast.makeText(MainActivity.this, "Vibrated!", Toast.LENGTH_SHORT).show();
                    vib.vibrate(VibrationEffect.createWaveform(time, amp, -1));
                }
            }
        });

    }

    boolean check(String code) {
        for (int i = 0; i < code.length(); i++)
            if (code.charAt(i) != '.' && code.charAt(i) != '-' && code.charAt(i) != '0')
                return false;
        return true;
    }

    String morse(String text) {
        String m = "";
        for (int i = 0; i < text.length(); i++)
            m += morse(text.charAt(i)) + '0';
        return m;
    }

    String morse(char ch) {
        switch (ch) {
            case 'a':
            case 'A':
                return ".-";
            case 'b':
            case 'B':
                return "-...";
            case 'c':
            case 'C':
                return "-.-.";
            case 'd':
            case 'D':
                return "-..";
            case 'e':
            case 'E':
                return ".";
            case 'f':
            case 'F':
                return "..-.";
            case 'g':
            case 'G':
                return "--.";
            case 'h':
            case 'H':
                return "....";
            case 'i':
            case 'I':
                return "..";
            case 'j':
            case 'J':
                return ".---";
            case 'k':
            case 'K':
                return "-.-";
            case 'l':
            case 'L':
                return ".-..";
            case 'm':
            case 'M':
                return "--";
            case 'n':
            case 'N':
                return "-.";
            case 'o':
            case 'O':
                return "---";
            case 'p':
            case 'P':
                return ".--.";
            case 'q':
            case 'Q':
                return "--.-";
            case 'r':
            case 'R':
                return ".-.";
            case 's':
            case 'S':
                return "...";
            case 't':
            case 'T':
                return "-";
            case 'u':
            case 'U':
                return "..-";
            case 'v':
            case 'V':
                return "...-";
            case 'w':
            case 'W':
                return ".--";
            case 'x':
            case 'X':
                return "-..-";
            case 'y':
            case 'Y':
                return "-.--";
            case 'z':
            case 'Z':
                return "--..";
            case '1':
                return ".----";
            case '2':
                return "..---";
            case '3':
                return "...--";
            case '4':
                return "....-";
            case '5':
                return ".....";
            case '6':
                return "-....";
            case '7':
                return "--...";
            case '8':
                return "---..";
            case '9':
                return "----.";
            case '0':
                return "-----";
        }
        return "";
    }


    String generate() {

        Random r = new Random();
        int[] a = new int[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            a = r.ints(6, 0, 36).toArray();
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 10) ans.append((char) (a[i] + 48));
            else ans.append((char) (a[i] + 55));
        }
        return ans.toString();
    }
}
