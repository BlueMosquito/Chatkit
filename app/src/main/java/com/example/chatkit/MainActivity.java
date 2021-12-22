package com.example.chatkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    float v = 0;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    SignInButton google;
    LottieAnimationView lottieAnimationView;
    LottieAnimationView hello;
    LottieAnimationView welcome;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        createRequest();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth.getInstance().signOut();
        google = findViewById(R.id.sign_in_button);
        lottieAnimationView = findViewById(R.id.lr);
        hello = findViewById(R.id.hello);
        welcome = findViewById(R.id.welcome);


        hello.animate().translationY(-70).setDuration(1500).setStartDelay(1000);
        welcome.animate().translationY(70).setDuration(1500).setStartDelay(1000);
        lottieAnimationView.animate().setDuration(1500);


        findViewById(R.id.sign_in_button).setOnClickListener(v -> signIn());


        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("SignUp"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        google.setTranslationY(300);
        tabLayout.setTranslationY(300);

        google.setAlpha(v);
        tabLayout.setAlpha(v);

        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(500).start();

        google.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, Extra.class);
            startActivity(intent1);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,Profile.class);
                    startActivity(intent);
                }
            }, 3000);
        });
    }

    private void createRequest() {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, this::onComplete);
    }

    private void onComplete(@NotNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
// Sign in success, update UI with the signed-in user's information
            FirebaseUser user = mAuth.getCurrentUser();
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "O_O", Toast.LENGTH_SHORT).show();
        }
    }
}
