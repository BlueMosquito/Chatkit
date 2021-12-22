package com.example.chatkit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUF extends Fragment implements View.OnClickListener{

    EditText fullName, emailAddress, phoneNumber, password, confirmPassword;
    Button signUp;
    float v = 0;

    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        //signUp.setOnClickListener(this);

        fullName = root.findViewById(R.id.name);
        emailAddress = root.findViewById(R.id.email);
        phoneNumber = root.findViewById(R.id.phone);
        password = root.findViewById(R.id.password);
        confirmPassword = root.findViewById(R.id.confirmP);
        signUp = root.findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();

        fullName.setTranslationX(800);
        emailAddress.setTranslationX(800);
        phoneNumber.setTranslationX(800);
        password.setTranslationX(800);
        confirmPassword.setTranslationX(800);
        signUp.setTranslationX(800);

        fullName.setAlpha(v);
        emailAddress.setAlpha(v);
        phoneNumber.setAlpha(v);
        password.setAlpha(v);
        confirmPassword.setAlpha(v);
        signUp.setAlpha(v);

        fullName.animate().translationX(1).alpha(1).setDuration(1000).setStartDelay(700).start();
        emailAddress.animate().translationX(1).alpha(1).setDuration(1000).setStartDelay(700).start();
        phoneNumber.animate().translationX(1).alpha(1).setDuration(1000).setStartDelay(1100).start();
        password.animate().translationX(1).alpha(1).setDuration(1000).setStartDelay(1100).start();
        confirmPassword.animate().translationX(1).alpha(1).setDuration(1000).setStartDelay(1100).start();
        signUp.animate().translationX(1).alpha(1).setDuration(1000).setStartDelay(700).start();

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                register();
                break;
        }
    }

    public void register() {

        String fullN = fullName.getText().toString().trim();
        String emailA = emailAddress.getText().toString().trim();
        String phoneN = phoneNumber.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String confirmP = confirmPassword.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(emailA, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    helperClass user = new helperClass(fullN, emailA, phoneN, pass, confirmP);

                    FirebaseDatabase.getInstance().getReference("helperClass")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                            }
                        }
                    });
                }
            }
        });
    }
}
