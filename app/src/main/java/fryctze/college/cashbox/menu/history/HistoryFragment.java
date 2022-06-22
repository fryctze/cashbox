package fryctze.college.cashbox.menu.history;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import fryctze.college.cashbox.R;
import fryctze.college.cashbox.menu.MainActivity;
import fryctze.college.cashbox.databinding.FragmentHistoryBinding;
import fryctze.college.cashbox.utiliy.DatabaseHelper;

public class HistoryFragment extends Fragment implements HistoryTransactionsAdapter.ItemTransactionClickListener {

    private FragmentHistoryBinding binding;
    private HistoryTransactionsAdapter historyTransactionsAdapter;

    private Dialog historyCustomDialog;

    TextView detailDate, detailName, detailNominal, detailDesc;

    ArrayList<ModelTransaction> dataset = new ArrayList<>();

    int selected_id, selected_pos;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //((MainActivity) requireActivity()).setupToolbar("History");
        ((MainActivity) requireActivity()).setTitle("History");
        setupListHistoryTransactions();
        initCustomDialog();

        retrieve();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClickItem(ModelTransaction model, int pos) {
        detailDate.setText(model.getDate());
        detailName.setText(model.getName());
        detailNominal.setText("Rp"+model.getNominal());
        detailDesc.setText(model.getDesc());
        selected_id = model.getId();
        selected_pos = pos;
        historyCustomDialog.show();
    }

    private void setupListHistoryTransactions() {
        historyTransactionsAdapter = new HistoryTransactionsAdapter(this);
        binding.rvListHistoryTransactions.setAdapter(historyTransactionsAdapter);
        binding.rvListHistoryTransactions.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvListHistoryTransactions.setItemAnimator(new DefaultItemAnimator());
    }

    private void initCustomDialog(){
        historyCustomDialog = new Dialog(getContext());
        historyCustomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        historyCustomDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        historyCustomDialog.setContentView(R.layout.dialog_history);
        historyCustomDialog.setCancelable(true);

        detailDate = historyCustomDialog.findViewById(R.id.tv_trans_date);
        detailName = historyCustomDialog.findViewById(R.id.tv_trans_name);
        detailNominal = historyCustomDialog.findViewById(R.id.tv_trans_nominal);
        detailDesc = historyCustomDialog.findViewById(R.id.tv_trans_desc);
        Button btnDelete = historyCustomDialog.findViewById(R.id.btn_trans_delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
                historyCustomDialog.dismiss();
            }
        });
    }

    private void retrieve(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        databaseHelper.openDB();
        dataset.clear();
        ArrayList<ModelTransaction> data =  databaseHelper.getAllTransaction();
        for (int i = data.size()-1; i > -1; i--) {
            dataset.add(data.get(i));
        }
        historyTransactionsAdapter.setDataset(dataset);
    }

    private void delete(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        databaseHelper.openDB();
        dataset.remove(dataset.get(selected_pos));
        databaseHelper.deleteTransaction(selected_id);
        historyTransactionsAdapter.setDataset(dataset);

        selected_pos = -1;
        selected_id = -1;

        Toast.makeText(getContext(), "Successfully delete a data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        retrieve();
    }
}