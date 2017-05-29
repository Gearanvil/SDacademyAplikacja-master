package com.example.andrzejdevcom.sdacademyaplikacja.presenters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.andrzejdevcom.sdacademyaplikacja.MainActivity;
import com.example.andrzejdevcom.sdacademyaplikacja.R;
import com.example.andrzejdevcom.sdacademyaplikacja.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Katarzyna on 2017-05-27.
 */

public class LoginPresenter {

    //login dziala na takiej samej zasadzie jak register oprocz jednej zmiany cyzli nazwy metody

    //inicjalizacja obiektu Firebase
    private FirebaseAuth firebaseAuth;
    //progressDialog obiekt
    private ProgressDialog progressDialog;


    public void userLogin(final Activity activity, String email, String password) {
        //inicjalizacja progressDialog i firebase
        progressDialog = new ProgressDialog(activity);
        firebaseAuth = FirebaseAuth.getInstance();
        //metody sprawdzajace
        if (TextUtils.isEmpty(email)) {
            Utils.showToast(activity, activity.getString(R.string.toast_enter_email));
            return;
        } else if (!Utils.isEmailVavlid(email)) {
            Utils.showToast(activity, "Please enter the valid email..");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            //stopping function execution further
            Utils.showToast(activity, "Password is empty..");
            return;
        } else if (!Utils.isPasswordValid(password)) {
            Utils.showToast(activity, "Password is to short..");
            return;
        }

        //wiadomosc dla uzytkownika ze sie loguje
        progressDialog.setMessage("Loggin...");
        progressDialog.show();

        //metoda do logowania
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //start the game activity
                            activity.finish();
                            activity.startActivity(new Intent(activity, MainActivity.class));
                        } else {
                            Utils.showToast(activity, "Something went wrong.. try again");
                        }

                    }
                });
    }
}
