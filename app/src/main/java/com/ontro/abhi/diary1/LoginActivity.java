package com.ontro.abhi.diary1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.CursorIndexOutOfBoundsException;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    public DatabaseHelper mydb;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws RuntimeException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        int new_user=0;
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setText("");
        final Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        //populateAutoComplete();
        mydb = new DatabaseHelper(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final Cursor res = mydb.Get_user();

        if(res.getCount()==0)
        {
            mEmailSignInButton.setText("SIGN UP");
            new_user=1;
        }
        else
        {
            res.moveToFirst();
            mEmailSignInButton.setText("SIGN IN");
            //mPasswordView.setText(res.getString(1));
            mEmailView.setText(res.getString(0));
        }


        final int finalNew_user = new_user;
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //  attemptLogin();
                if (!((isEmailValid(mEmailView.getText().toString()))&&(isPasswordValid(mPasswordView.getText().toString())))) {
                    if (!(isEmailValid(mEmailView.getText().toString())))
                        Toast.makeText(LoginActivity.this, "Incorrect e-Mail Id", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(LoginActivity.this, "Password length should be more than 4", Toast.LENGTH_LONG).show();
                }
                else {
                    if (finalNew_user == 1) {
                        boolean isInserted = mydb.Reg_user(mEmailView.getText().toString(), mPasswordView.getText().toString());
                        if (isInserted == true) {
                            Toast.makeText(LoginActivity.this, "Registration successfull", Toast.LENGTH_LONG).show();
                            mPasswordView.setText("");
                            mEmailSignInButton.setText("SIGN IN");
                            startActivity(new Intent(LoginActivity.this, choose_date1.class));
                        } else
                            Toast.makeText(LoginActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                    } else {
                        //res.moveToFirst();
                        String t1 = mPasswordView.getText().toString();
                        if (res.getString(1).equals(t1)) {
                            mPasswordView.setText("");
                            startActivity(new Intent(LoginActivity.this, choose_date1.class));
                        } else
                            Toast.makeText(LoginActivity.this, "Wrong Crdentials entered", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



}

