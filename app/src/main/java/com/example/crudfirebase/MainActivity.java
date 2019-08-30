package com.example.crudfirebase;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudfirebase.model.Requests;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference database;
    private EditText text1, text2, text3;
    private Button btn;
    private ProgressDialog loading;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance().getReference();

        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        btn = (Button) findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Snama = text1.getText().toString();
                String Semail = text2.getText().toString();
                String Sdesk = text3.getText().toString();

                if(Snama.equals("")){
                    text1.setError("Silakan Masukkan Nama Anda");
                    text1.requestFocus();
                }else if(Semail.equals("")){
                    text2.setError("Silakan Masukkan Email Anda");
                    text2.requestFocus();
                }else if(Sdesk.equals("")){
                    text3.setError("Silakan Masukkan Deskripsi Anda");
                    text3.requestFocus();
                }else {
                    loading = ProgressDialog.show(MainActivity.this, null, "Please Wait", true, false);

                    submitUser(new Requests(
                            Snama.toLowerCase(),
                            Semail.toLowerCase(),
                            Sdesk.toLowerCase()
                    ));
                }
            }
        });
    }

    private void submitUser(Requests requests){
        database.child("Request")
                //.child("Request Satu")
                .push()
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();

                        text1.setText("");
                        text2.setText("");
                        text3.setText("");

                        Toast.makeText(MainActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
