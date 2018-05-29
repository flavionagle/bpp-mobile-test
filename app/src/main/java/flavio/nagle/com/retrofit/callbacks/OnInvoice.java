package flavio.nagle.com.retrofit.callbacks;

import java.util.ArrayList;

import flavio.nagle.com.model.InvoiceResponse;

public interface OnInvoice {

    void onResponse(ArrayList<InvoiceResponse> response);
}
