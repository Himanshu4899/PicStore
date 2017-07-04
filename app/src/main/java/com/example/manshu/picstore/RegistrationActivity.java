package com.example.manshu.picstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText txtEmailAddress;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        txtEmailAddress = (EditText) findViewById(R.id.txtEmailRegistration);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegistration);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void onSaveInstanceState(Bundle outState) {
        // Make sure you save the current image resource
        super.onSaveInstanceState(outState);
        outState.putString("email",txtEmailAddress.getText().toString());
        outState.putString("passwd",txtEmailAddress.getText().toString());



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String abc = savedInstanceState.getString("email");
        String abcd = savedInstanceState.getString("passwd");

    }

    public void btnRegistrationUser_Click(View v){

        String eemail = txtEmailAddress.getText().toString().trim();
        String ppassword = txtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(eemail)|| TextUtils.isEmpty(ppassword)){
            Toast.makeText(RegistrationActivity.this,"Empty Fields",Toast.LENGTH_LONG).show();
        }
        else {

            final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Please Wait...", "Processing..", true);
            firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(i);

                    } else {
                        Log.e("Error", task.getException().toString());
                        Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }

                }
            });
        }



    }
}
