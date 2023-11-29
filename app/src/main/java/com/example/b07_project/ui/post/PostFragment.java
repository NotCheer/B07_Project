package com.example.b07_project.ui.post;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.b07_project.Post;
import com.example.b07_project.R;
import com.example.b07_project.databinding.FragmentPostBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;


public class PostFragment extends Fragment {
    /*
        //private FragmentPostBinding binding;
//
    //public View onCreateView(@NonNull LayoutInflater inflater,
    //                         ViewGroup container, Bundle savedInstanceState) {
    //    PostViewModel postViewModel =
    //            new ViewModelProvider(this).get(PostViewModel.class);
//
    //    binding = FragmentPostBinding.inflate(inflater, container, false);
    //    View root = binding.getRoot();
//
    //    //final TextView textView = binding.textPost;
    //    //postViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    //    return root;
    //}
     */
    private Spinner spinner_program;
    private Spinner spinner_A48;
    private Spinner spinner_A67;
    private Spinner spinner_A22;
    private Spinner spinner_A31;
    private Spinner spinner_A37;


    private Button button;
    private String selectedItem_program;
    private String selectedItem_A48;
    private String selectedItem_A67;
    private String selectedItem_A22;
    private String selectedItem_A31;
    private String selectedItem_A37;
    private Map<String, Double> optionMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        spinner_program = view.findViewById(R.id.spinner_program);
        spinner_A48 = view.findViewById(R.id.spinner_grade_A48);
        spinner_A67 = view.findViewById(R.id.spinner_grade_A67);
        spinner_A22 = view.findViewById(R.id.spinner_grade_A22);
        spinner_A31 = view.findViewById(R.id.spinner_grade_A31);
        spinner_A37 = view.findViewById(R.id.spinner_grade_A37);

        button = view.findViewById(R.id.button);

        setupSpinner(spinner_program,R.array.spinner_program_data);
        setupSpinner(spinner_A48,R.array.spinner_grade_data);
        setupSpinner(spinner_A67,R.array.spinner_grade_data);
        setupSpinner(spinner_A22,R.array.spinner_grade_data);
        setupSpinner(spinner_A31,R.array.spinner_grade_data);
        setupSpinner(spinner_A37,R.array.spinner_grade_data);

        initializeOptionMap();
        //spinner_program.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //    @Override
        //    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //        selectedItem_program = parent.getItemAtPosition(position).toString();
        //    }
        //
        //    @Override
        //    public void onNothingSelected(AdapterView<?> parent) {
        //    }
        //});
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allSpinnerSelected()){
                    selectedItem_program=spinner_program.getSelectedItem().toString();
                    selectedItem_A48=spinner_A48.getSelectedItem().toString();
                    selectedItem_A67=spinner_A67.getSelectedItem().toString();
                    selectedItem_A22=spinner_A22.getSelectedItem().toString();
                    selectedItem_A31=spinner_A31.getSelectedItem().toString();
                    selectedItem_A37=spinner_A37.getSelectedItem().toString();

                    double value_A48 = optionMap.getOrDefault(selectedItem_A48, 0.0);
                    double value_A67 = optionMap.getOrDefault(selectedItem_A67, 0.0);
                    double value_A22 = optionMap.getOrDefault(selectedItem_A22, 0.0);
                    double value_A31 = optionMap.getOrDefault(selectedItem_A31, 0.0);
                    double value_A37 = optionMap.getOrDefault(selectedItem_A37, 0.0);

                    double avg = (value_A48+value_A67+value_A22+value_A31+value_A37)/5.0;
                    boolean pass_A48 = value_A48 >= 3.0;
                    int cnt=0;
                    if(value_A67 >= 1.7){
                        cnt += 1;
                    }
                    if(value_A22 >= 1.7){
                        cnt += 1;
                    }
                    if(value_A37 >= 1.7){
                        cnt += 1;
                    }
                    if(selectedItem_program.equals("Specialist") || selectedItem_program.equals("Major")){
                        if(pass_A48 && cnt >= 2 && avg>=2.5){
                            Toast.makeText(getActivity(), "You are making the POST!!!!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getActivity(), "Sorry, your grade is not enough for making the POST.", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        double avg_minor = (value_A48+value_A67+value_A22)/3.0;
                        if(pass_A48 && avg_minor >= 3.0){
                            Toast.makeText(getActivity(), "You are making the POST!!!!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "Sorry, your grade is not enough for making the POST.", Toast.LENGTH_LONG).show();
                        }
                    }

                }
                else{
                    Toast.makeText(getActivity(), "Please select every option", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
    private void setupSpinner(Spinner spinner, int itemsArrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                itemsArrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private boolean allSpinnerSelected(){
        return isSpinnerSelected(spinner_program) && isSpinnerSelected(spinner_A48) && isSpinnerSelected(spinner_A67)
                && isSpinnerSelected(spinner_A22) && isSpinnerSelected(spinner_A31) && isSpinnerSelected(spinner_A37);
    }
    private boolean isSpinnerSelected(Spinner spinner) {
        return spinner.getSelectedItemPosition() > 0; // 0 is the default unselected item
    }

    private void initializeOptionMap() {
        optionMap = new HashMap<>();
        optionMap.put("A+ (90-100)", 4.0);
        optionMap.put("A (85-89)", 4.0);
        optionMap.put("A- (80-84)", 3.7);
        optionMap.put("B+ (77-79)", 3.3);
        optionMap.put("B (73-76)", 3.0);
        optionMap.put("B- (70-72)", 2.7);
        optionMap.put("C+ (67-69)", 2.3);
        optionMap.put("C (63-66)<", 2.0);
        optionMap.put("C- (60-62)", 1.7);
        optionMap.put("D+ (57-59)", 1.3);
        optionMap.put("D (53-56)", 1.0);
        optionMap.put("D- (50-52)", 0.7);
        optionMap.put("F (0-49)", 0.0);
        // Add more mappings as needed
    }
}


/**
 * //@Override
 *     //public void onDestroyView() {
 *     //    super.onDestroyView();
 *     //    binding = null;
 *     //}
 *
 * }
 */

