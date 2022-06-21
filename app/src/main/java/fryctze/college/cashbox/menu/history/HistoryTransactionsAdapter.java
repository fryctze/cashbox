package fryctze.college.cashbox.menu.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fryctze.college.cashbox.R;
import fryctze.college.cashbox.databinding.ItemTransactionBinding;

public class HistoryTransactionsAdapter extends RecyclerView.Adapter<HistoryTransactionsAdapter.ViewHolder>{
    private ItemTransactionClickListener listener;
    private ArrayList<ModelTransaction> dataset;

    public HistoryTransactionsAdapter(ItemTransactionClickListener listener) { ;
        this.listener = listener;
        dataset = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTransactionBinding binding;

        public ViewHolder(ItemTransactionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ModelTransaction model, ItemTransactionClickListener listener) {
            binding.tvName.setText(model.getName().toString());
            binding.tvDate.setText(model.getDate().toString());

            if (model.isGain()){
                binding.tvNominal.setTextColor(ContextCompat.getColor(this.itemView.getContext(), R.color.green_text));
                binding.tvNominal.setText(String.format("+Rp.%s", model.getNominal().toString()));
            } else {
                binding.tvNominal.setTextColor(ContextCompat.getColor(this.itemView.getContext(), R.color.red_text));
                binding.tvNominal.setText(String.format("-Rp.%s", model.getNominal().toString()));
            }

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickItem(model);
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemTransactionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataset.get(position), listener);
    }

    public void setDataset(ArrayList<ModelTransaction> data) {
        this.dataset.clear();
        this.dataset.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (this.dataset != null)
            return dataset.size();
        return 0;
    }

    interface ItemTransactionClickListener{
        void onClickItem(ModelTransaction model);
    }

}
