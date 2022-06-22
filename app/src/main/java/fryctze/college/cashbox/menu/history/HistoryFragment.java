package fryctze.college.cashbox.menu.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import fryctze.college.cashbox.menu.MainActivity;
import fryctze.college.cashbox.databinding.FragmentHistoryBinding;

public class HistoryFragment extends Fragment implements HistoryTransactionsAdapter.ItemTransactionClickListener {

    private FragmentHistoryBinding binding;
    private HistoryTransactionsAdapter historyTransactionsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((MainActivity) requireActivity()).setupToolbar("History");
        setupListHistoryTransactions();
        setupDummyData();

        return root;
    }

    private void setupDummyData() {
        ArrayList<ModelTransaction> dataset = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            dataset.add(new ModelTransaction("its data number "+i, "31 Feb 2022", "10000", true));
        }
        for (int i = 0; i < 30; i++) {
            dataset.add(new ModelTransaction("its data number "+i, "31 Feb 2022", "10000", false));
        }
        historyTransactionsAdapter.setDataset(dataset);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClickItem(ModelTransaction model) {
        Toast.makeText(getContext(), "clicked "+model.getName(), Toast.LENGTH_SHORT).show();
    }

    private void setupListHistoryTransactions() {
        historyTransactionsAdapter = new HistoryTransactionsAdapter(this);
        binding.rvListHistoryTransactions.setAdapter(historyTransactionsAdapter);
        binding.rvListHistoryTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvListHistoryTransactions.setItemAnimator(new DefaultItemAnimator());
    }
}