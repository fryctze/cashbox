package fryctze.college.cashbox.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fryctze.college.cashbox.R;
import fryctze.college.cashbox.databinding.ActivityAddBinding;
import fryctze.college.cashbox.databinding.ActivityMainBinding;
import fryctze.college.cashbox.menu.history.ModelTransaction;
import fryctze.college.cashbox.utiliy.DatabaseHelper;

public class AddActivity extends AppCompatActivity {

    ActivityAddBinding binding;

    final Calendar myCalendar= Calendar.getInstance();
    int ISGAIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBinding();
        setSupportActionBar(binding.toolbar);
        switch (ISGAIN){
            case 0:
                this.setTitle("Add Expenses");
                break;
            case 1:
                this.setTitle("Add Income");
                break;
            case 2:
                this.setTitle("Add Goal");
                break;
        }
        setupUi();
    }

    private void setupUi() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        binding.etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    binding.etName.setError(null);
                    binding.etDate.setError(null);
                    binding.etNominal.setError(null);
                    if (ISGAIN == 2)
                        addGoal();
                    else
                        addExpenseIncome();
                    Toast.makeText(AddActivity.this, "Successfully insert data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void updateLabel() {
        String format = "E, d MMMM yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        binding.etDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void setupBinding() {
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        ISGAIN = intent.getIntExtra("IS_GAIN", 0);
    }

    private boolean isValid(){
        if (binding.etDate.getText().toString().equals("")){
            binding.etDate.setError("Date field is required");
            return false;
        }
        if (binding.etName.getText().toString().equals("")) {
            binding.etName.setError("Name field is required");
            return false;
        }
        if (binding.etNominal.getText().toString().equals("")){
            binding.etNominal.setError("Nominal field is required");
            return false;
        }
        try{
            Integer.parseInt(binding.etNominal.getText().toString());
        } catch (NumberFormatException ex){
            binding.etNominal.setError("Numeric only");
            return false;
        }
        return true;
    }

    private void addExpenseIncome(){
        DatabaseHelper databaseHelper = new DatabaseHelper(AddActivity.this);

        databaseHelper.openDB();
        databaseHelper.insertTransaction(
                new ModelTransaction(
                        binding.etName.getText().toString(),
                        binding.etDate.getText().toString(),
                        binding.etNominal.getText().toString(),
                        ISGAIN == 1,
                        binding.etDesc.getText().toString()
                )
        );
        databaseHelper.closeDB();
    }

    private void addGoal(){

    }
}