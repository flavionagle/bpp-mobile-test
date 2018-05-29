package flavio.nagle.com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import flavio.nagle.com.R;
import flavio.nagle.com.model.LoginResponse;
import flavio.nagle.com.repositories.InvoiceRepository;
import flavio.nagle.com.retrofit.callbacks.OnLogin;

public class MainActivity extends AppCompatActivity{

    private EditText login;
    private EditText password;
    private ProgressBar progressBar;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_bar);

        loginButton.setOnClickListener(v->{
            if(canLogin())
                login();
        });
    }

    private boolean canLogin() {
        if(!login.getText().toString().isEmpty() &&
                !password.getText().toString().isEmpty()){
            return true;
        }
        if(login.getText().toString().isEmpty()){
            login.setError("Este campo não pode ser vazio");
        }
        if(password.getText().toString().isEmpty()){
            password.setError("Este campo não pode ser vazio");
        }
        return false;
    }

    private void login(){
        progressBar.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.GONE);
        InvoiceRepository repository = new InvoiceRepository(this);
        repository.login(login.getText().toString(),password.getText().toString(), new OnLogin() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                progressBar.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);
                Log.d("Login success", loginResponse.toString());
                startActivity(new Intent(getApplicationContext(), InvoiceActivity.class));
            }

            @Override
            public void onError(LoginResponse loginResponse) {
                progressBar.setVisibility(View.GONE);
                loginButton.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), loginResponse.getCode() + " " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
