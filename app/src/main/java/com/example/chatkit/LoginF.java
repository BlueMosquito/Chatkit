package com.example.chatkit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginF extends Fragment {

    EditText pass;
    EditText email;
    Button login;
    TextView forgetPass;
    float v = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.log_in, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetPass= root.findViewById(R.id.forgetPass);
        login = root.findViewById(R.id.button);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        login.setTranslationX(800);
        forgetPass.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        login.setAlpha(v);
        forgetPass.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        return root;
    }
}
