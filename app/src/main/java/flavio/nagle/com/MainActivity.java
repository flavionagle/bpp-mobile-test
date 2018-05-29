package flavio.nagle.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import flavio.nagle.com.model.LoginResponse;
import flavio.nagle.com.repositories.InvoiceRepository;
import flavio.nagle.com.retrofit.callbacks.OnLoginListener;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InvoiceRepository repository = new InvoiceRepository(this);
        repository.login(new OnLoginListener() {
            @Override
            public void onSuccess(Object o) {
                Log.d("Login success", o.toString());
            }

            @Override
            public void onError(Object o) {
                Toast.makeText(getApplicationContext(), ((LoginResponse) o).getCode() + " " + ((LoginResponse) o).getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        repository.getInvoice();
    }
}
