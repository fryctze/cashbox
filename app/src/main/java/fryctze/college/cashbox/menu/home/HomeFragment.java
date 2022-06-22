package fryctze.college.cashbox.menu.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import fryctze.college.cashbox.databinding.FragmentHomeBinding;
import fryctze.college.cashbox.menu.MainActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ((MainActivity) requireActivity()).setTitle("Home");

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //((MainActivity) requireActivity()).setTitle("Home");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}