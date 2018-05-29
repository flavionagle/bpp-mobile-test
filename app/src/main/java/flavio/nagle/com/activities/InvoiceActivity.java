package flavio.nagle.com.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import flavio.nagle.com.R;
import flavio.nagle.com.adapters.InvoiceAdapter;
import flavio.nagle.com.model.InvoiceResponse;
import flavio.nagle.com.repositories.InvoiceRepository;
import flavio.nagle.com.retrofit.callbacks.OnInvoice;

public class InvoiceActivity extends AppCompatActivity implements OnInvoice {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice_activity);

        progressBar = findViewById(R.id.progress_bar);

        InvoiceRepository repository = new InvoiceRepository(this);
        repository.getInvoice(this);

    }

    @Override
    public void onResponse(ArrayList<InvoiceResponse> response) {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        InvoiceAdapter invoiceAdapter = new InvoiceAdapter(response, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(invoiceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.GONE);
    }
}
