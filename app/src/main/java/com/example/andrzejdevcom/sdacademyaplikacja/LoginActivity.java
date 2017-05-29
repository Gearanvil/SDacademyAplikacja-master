package com.example.andrzejdevcom.sdacademyaplikacja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrzejdevcom.sdacademyaplikacja.presenters.LoginPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    //obiekt login presenter
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //stworenie obiektu loginPresenter
        if (presenter == null) {
            presenter = new LoginPresenter();
        }
    }

    //metoda do singIn
    @OnClick(R.id.buttonSignIn)
    public void signIn() {
        String email = editTextEmail.getText().toString().toLowerCase().trim();
        String password = editTextPassword.getText().toString().trim();
        presenter.userLogin(this, email, password);
    }

    //otwarcie aktywnosci z rejestracja
    @OnClick(R.id.textViewRegister)
    public void startRegisterActivity() {
        finish();
        startActivity(new Intent(this, RegisterActivity.class));
    }
}