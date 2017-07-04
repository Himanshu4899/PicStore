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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmailLogin;
    private EditText txtPwd;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtPwd = (EditText) findViewById(R.id.txtPasswordLogin);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),UploadActivity.class));
        }

    }
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();

        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        finish();
        startActivity(i);

        // optional depending on your needs
    }

    public void onSaveInstanceState(Bundle outState) {
        // Make sure you save the current image resource
        super.onSaveInstanceState(outState);
        outState.putString("email",txtEmailLogin.getText().toString());
        outState.putString("passwd",txtPwd.getText().toString());



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String abc = savedInstanceState.getString("email");
        String abcd = savedInstanceState.getString("passwd");

    }
    public void btnUserLogin_Click(View v){

        String eemail = txtEmailLogin.getText().toString().trim();
        String ppassword = txtPwd.getText().toString().trim();
        if (TextUtils.isEmpty(eemail)|| TextUtils.isEmpty(ppassword)){
            Toast.makeText(LoginActivity.this,"Empty Fields",Toast.LENGTH_LONG).show();
        }
    else {
            final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please Wait..", "Processing..", true);

            firebaseAuth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(), txtPwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override

                public void onComplete(@NonNull Task<AuthResult> task) {

                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this, UploadActivity.class);
                        //  i.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());
                        finish();
                        startActivity(i);


                    } else {
                        Log.e("Error", task.getException().toString());
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }


                }

            });
        }
    }
}
