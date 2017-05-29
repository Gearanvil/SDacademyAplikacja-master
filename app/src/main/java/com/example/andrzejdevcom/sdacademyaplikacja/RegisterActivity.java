package com.example.andrzejdevcom.sdacademyaplikacja;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andrzejdevcom.sdacademyaplikacja.presenters.RegisterPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmail)
    EditText etEmail;
    @BindView(R.id.editTextPassword)
    EditText etPassword;

    //pole presenter
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //inicjacia presentera , gdy jest nullem stworzy obiekt klasy
        if (presenter == null) {
            presenter = new RegisterPresenter();
        }
    }

    @OnClick(R.id.textViewSignin)
    public void singInUser() {
        //otwarcie aktywnosci LoginActivity z metodami do logowania
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @OnClick(R.id.buttonRegister)
    public void createUser() {
        //pobieranie emaila, passworda do rejestracji
        String email = etEmail.getText().toString().toLowerCase().trim();
        String password = etPassword.getText().toString().trim();
        //wywolanie metody z presentera
        presenter.registerUser(this, email, password);
    }
}
