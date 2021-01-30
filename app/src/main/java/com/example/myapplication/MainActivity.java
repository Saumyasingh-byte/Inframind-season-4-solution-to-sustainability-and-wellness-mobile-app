package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView background;
    private TextView signIn;
    private TextView box1;

    private TextView name;
    private TextView heartRate;
    private TextView respirationRate;
    private TextView bloodSugar;
    private TextView bloodPressure;
    private TextView bodyTemp;
    private TextView oxygenSat;
    private TextView cholestrol;
    private TextView heartRate1;
    private TextView respirationRate1;
    private TextView bloodSugar1;
    private TextView bloodPressure1;
    private TextView bodyTemp1;
    private TextView oxygenSat1;
    private TextView cholestrol1;
    private TextView department;
    private Button mLoginBtn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        mEmailField = (EditText) findViewById(R.id.usernameEmail);
        background = (TextView) findViewById(R.id.backgroundPurple);
        name = (TextView) findViewById(R.id.name);
        heartRate = (TextView) findViewById(R.id.heartRateIdeal);
        bloodPressure = (TextView) findViewById(R.id.bloodPressureRate);
        bloodSugar = (TextView) findViewById(R.id.bloodSugarrate);
        bodyTemp = (TextView) findViewById(R.id.bodyTempRate);
        oxygenSat = (TextView) findViewById(R.id.oxygenRate);
        cholestrol = (TextView) findViewById(R.id.cholestrolrate);
        respirationRate = (TextView) findViewById(R.id.respirationRate);
        heartRate1 = (TextView) findViewById(R.id.heartRateIdeal);
        bloodPressure1 = (TextView) findViewById(R.id.bloodPressureRate);
        bloodSugar1 = (TextView) findViewById(R.id.bloodSugarrate);
        bodyTemp1 = (TextView) findViewById(R.id.bodyTempRate);
        oxygenSat1 = (TextView) findViewById(R.id.oxygenRate);
        cholestrol1 = (TextView) findViewById(R.id.cholestrolrate);
        respirationRate1 = (TextView) findViewById(R.id.respirationRate);
        department = (TextView) findViewById(R.id.department);
        signIn = (TextView) findViewById(R.id.tvSignIn);
        mPasswordField = (EditText) findViewById(R.id.password);
        mLoginBtn = (Button) findViewById(R.id.btnSubmit);
        box1=(TextView) findViewById(R.id.whiteBackground);
//        box2=(TextView) findViewById(R.id.whiteBackground1);
//        userId = mAuth.getCurrentUser().getUid();

        final DocumentReference documentReference=fstore.collection("users").document("user-1");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("department"));
                department.setText(value.getString("name"));
                heartRate.setText(value.getString("heartRate"));
                respirationRate.setText(value.getString("res"));
                bloodPressure.setText(value.getString("bloodPressure"));
                bloodSugar.setText(value.getString("bloodSugar"));
                bodyTemp.setText(value.getString("bodyTemp"));
                oxygenSat.setText(value.getString("oxy"));
                cholestrol.setText(value.getString("cholestrol"));
            }
        });




        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                if(firebaseAuth.getCurrentUser()!= null){
//                    startActivity(new Intent(MainActivity.this,AccountActivity.clasm
                    mEmailField.setVisibility(View.GONE);
                    mPasswordField.setVisibility(View.GONE);
                    signIn.setVisibility(View.GONE);
                    mLoginBtn.setVisibility(View.GONE);
                    background.setVisibility(View.VISIBLE);
                    box1.setVisibility(View.VISIBLE);
                    heartRate.setVisibility(View.VISIBLE);
                    respirationRate.setVisibility(View.VISIBLE);
                    bloodPressure.setVisibility(View.VISIBLE);
                    bloodSugar.setVisibility(View.VISIBLE);
                    bodyTemp.setVisibility(View.VISIBLE);
                    oxygenSat.setVisibility(View.VISIBLE);
                    cholestrol.setVisibility(View.VISIBLE);
                    heartRate1.setVisibility(View.VISIBLE);
                    respirationRate1.setVisibility(View.VISIBLE);
                    bloodPressure1.setVisibility(View.VISIBLE);
                    bloodSugar1.setVisibility(View.VISIBLE);
                    bodyTemp1.setVisibility(View.VISIBLE);
                    oxygenSat1.setVisibility(View.VISIBLE);
                    cholestrol1.setVisibility(View.VISIBLE);
                    name.setVisibility(View.VISIBLE);
                    department.setVisibility(View.VISIBLE);

                }
            }
        };

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }

        });

    }
    @Override
    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn() {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();

        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_SHORT).show();
                    }else{

                    }
                }
            });
        }
    }
}
