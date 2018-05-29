package flavio.nagle.com.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import flavio.nagle.com.R;
import flavio.nagle.com.model.InvoiceResponse;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder>{
    private final ArrayList<InvoiceResponse> invoices;
    private final LayoutInflater mInflater;
    private final Context context;

    public InvoiceAdapter(ArrayList<InvoiceResponse> invoiceResponses, Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.invoices = invoiceResponses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.invoice_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int finalPosition = holder.getAdapterPosition();
        holder.merchantDescription.setText(this.invoices.get(finalPosition).getMccDescription());
        holder.merchantName.setText(this.invoices.get(finalPosition).getMerchantName());
        holder.transactionCurrency.setText(this.invoices.get(finalPosition).getTransactionCurrency());
        holder.transactionAmount.setText(String.format("%.2f", Float.valueOf(this.invoices.get(finalPosition).getTransactionAmount())));
        holder.transactionDate.setText(this.invoices.get(finalPosition).getTransactionFormattedDate().split("T")[0]);
        setTransactionColor(holder.transactionAmount, finalPosition, holder.constraintLayout);
    }

    private void setTransactionColor(TextView transactionAmount, int finalPosition, ConstraintLayout constraintLayout) {
        switch (this.invoices.get(finalPosition).getTransactionStatus()){
            case "Settled":
                transactionAmount.setTextColor(this.context.getResources().getColor(R.color.settled));
                constraintLayout.setAlpha(1);
                break;
            case "Pending":
                transactionAmount.setTextColor(this.context.getResources().getColor(R.color.pending));
                constraintLayout.setAlpha(0.85F);
                break;
            case "Reversed":
                transactionAmount.setTextColor(this.context.getResources().getColor(R.color.reversed));
                constraintLayout.setAlpha(0.70F);
                break;
            case "Declined":
                transactionAmount.setTextColor(this.context.getResources().getColor(R.color.declined));
                constraintLayout.setAlpha(0.65F);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private TextView merchantDescription;
        private TextView merchantName;
        private TextView transactionCurrency;
        private TextView transactionAmount;
        private TextView transactionDate;


        ViewHolder(View itemView) {
            super(itemView);
            this.merchantDescription = itemView.findViewById(R.id.merchant_description);
            this.merchantName = itemView.findViewById(R.id.merchant_name);
            this.transactionCurrency = itemView.findViewById(R.id.transaction_currency);
            this.transactionAmount = itemView.findViewById(R.id.transaction_amount);
            this.transactionDate = itemView.findViewById(R.id.transaction_date);
            this.constraintLayout = itemView.findViewById(R.id.constraint_Layout);
        }
    }
}
