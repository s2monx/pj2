package com.example.pj2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String AUTHORITY = "com.example.pj1";
    static final String URL = "content://"+AUTHORITY+"/data";
    static final Uri uri = Uri.parse(URL);
    Button btnThemTG, btnSuaTG, btnXoaTG, btnXemTG;
    EditText etMaTG, etTenTG, etDiachi, etEmail;
    GridView gvTG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXaTG();
        quanLyTacGia();
    }
    public void anhXaTG() {
        btnThemTG = findViewById(R.id.btnThemTG);
        btnSuaTG = findViewById(R.id.btnSuaTG);
        btnXoaTG = findViewById(R.id.btnXoaTG);
        btnXemTG = findViewById(R.id.btnXemTG);

        etMaTG = findViewById(R.id.etMaTG);
        etTenTG = findViewById(R.id.etTenTG);
        etDiachi = findViewById(R.id.etDiachi);
        etTenTG = findViewById(R.id.etTenTG);
        etEmail = findViewById(R.id.etEmail);

        gvTG = findViewById(R.id.gvTG);
    }
    public void quanLyTacGia() {
        btnXemTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list_string = new ArrayList<>();
                Cursor cursor = getContentResolver().query(uri, null, null, null, "name");
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        list_string.add(cursor.getInt(0) + "");
                        list_string.add(cursor.getString(1) + "");
                        list_string.add(cursor.getString(2) + "");
                        list_string.add(cursor.getString(3) + "");
                    } while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list_string);
                    gvTG.setAdapter(adapter);
                } else
                    Toast.makeText(getApplicationContext(), "Khong co", Toast.LENGTH_SHORT).show();
            }
        });
        btnThemTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("id", Integer.parseInt(etMaTG.getText().toString()));
                values.put("name", etTenTG.getText().toString());
                values.put("address", etDiachi.getText().toString());
                values.put("email", etEmail.getText().toString());
                Uri insert_uri = getContentResolver().insert(uri, values);
                Toast.makeText(getApplicationContext(), "Luu thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btnSuaTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("id", Integer.parseInt(etMaTG.getText().toString()));
                values.put("name", etTenTG.getText().toString());
                values.put("address", etDiachi.getText().toString());
                values.put("email", etEmail.getText().toString());
                if (getContentResolver().update(uri, values,
                        "id=?",
                        new String[]{etMaTG.getText().toString()}) > 0) ;
                Toast.makeText(getBaseContext(), "Sua thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
        btnXoaTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContentResolver().delete(uri,
                        "id=?",
                        new String[]{etMaTG.getText().toString()}) > 0) ;
                Toast.makeText(getBaseContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
