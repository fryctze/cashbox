package fryctze.college.cashbox.menu.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import fryctze.college.cashbox.R;
import fryctze.college.cashbox.databinding.FragmentHomeBinding;
import fryctze.college.cashbox.menu.AddActivity;
import fryctze.college.cashbox.menu.MainActivity;
import fryctze.college.cashbox.menu.history.ModelTransaction;
import fryctze.college.cashbox.utiliy.DatabaseHelper;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Date myCalendar= Calendar.getInstance().getTime();
    String formatedDate;

    String currentBalance, Expenses, Income, Goal;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((MainActivity) requireActivity()).setTitle("Home");
        setupUI();

        String format = "MMMM yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        formatedDate = dateFormat.format(myCalendar);

        fetchData();
        fetchTotalData();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
        fetchTotalData();
    }

    private void setupUI(){
        binding.fabExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                intent.putExtra("IS_GAIN",0);
                startActivity(intent);
            }
        });

        binding.fabIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                intent.putExtra("IS_GAIN",1);
                startActivity(intent);
            }
        });

        binding.fabGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                intent.putExtra("IS_GAIN",2);
                startActivity(intent);
            }
        });
    }

    private void fetchData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        databaseHelper.openDB();

        ArrayList<ModelTransaction> transactions = databaseHelper.transactionOfMonth(formatedDate);
        Long expenses = 0L;
        Long income = 0L;

        for (int i = 0; i < transactions.size(); i++) {
            if (!transactions.get(i).isGain()){
                expenses += Long.parseLong(transactions.get(i).getNominal());
            } else {
                income += Long.parseLong(transactions.get(i).getNominal());
            }
        }

        Expenses = expenses.toString();
        Income = income.toString();
        binding.tvValueExpenses.setText("Rp."+Expenses);
        binding.tvValueIncome.setText("Rp."+Income);

        ArrayList<ModelGoal> goals = databaseHelper.goalOfMonth(formatedDate);
        Long goal = 0L;
        for (int i = 0; i < goals.size(); i++) {
            goal += Long.parseLong(goals.get(i).getNominal());
        }

        Goal = goal.toString();
        binding.tvValueGoal.setText("Rp."+goal);
    }

    private void fetchTotalData(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        databaseHelper.openDB();

        ArrayList<ModelTransaction> transactions = databaseHelper.getAllTransaction();
        Long expenses = 0L;
        Long income = 0L;

        for (int i = 0; i < transactions.size(); i++) {
            if (!transactions.get(i).isGain()){
                expenses += Long.parseLong(transactions.get(i).getNominal());
            } else {
                income += Long.parseLong(transactions.get(i).getNominal());
            }
        }

        Long current = income - expenses;
        if (current < 0){
            binding.tvCurrentBalance.setTextColor(ResourcesCompat.getColor(getResources(), R.color.red_text, null));
        } else {
            binding.tvCurrentBalance.setTextColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
        }
        currentBalance = current.toString();
        binding.tvCurrentBalance.setText("Rp."+currentBalance);
    }
}