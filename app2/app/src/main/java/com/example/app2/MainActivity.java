package com.example.app2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.ArrayRes;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button submit_btn;
    Spinner spinner_fundamental_units, spinner_to_1, spinner_to_2;
    EditText input_num;
    TextView resultant;

    public void spinnerSetter(@IdRes int Id, @ArrayRes int grp){
        Spinner spinner = findViewById(Id);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, grp, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        submit_btn = findViewById(R.id.submit_btn);
        spinner_fundamental_units = findViewById(R.id.units_spinner);
        spinner_to_1 = findViewById(R.id.spinner_to_1);
        spinner_to_2 = findViewById(R.id.spinner_to_2);
        input_num = findViewById(R.id.input_num);
        resultant = findViewById(R.id.result_text);

        spinnerSetter(spinner_fundamental_units.getId(), R.array.units_group);
        spinnerSetter(spinner_to_1.getId(), R.array.weights_units_array);
        spinnerSetter(spinner_to_2.getId(), R.array.weights_units_array);

        submit_btn.setOnClickListener(v -> {
            String from = spinner_to_1.getSelectedItem().toString();
            String into = spinner_to_2.getSelectedItem().toString();
            String inputStr = input_num.getText().toString();

            if (inputStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                return;
            }

            int inputVal = Integer.parseInt(inputStr);
          try {
              if (from.equals(into)) {
                  resultant.setText(String.valueOf("The value in " + into + " is " + inputVal));
              } else {
                  if(from.equals("Kilogram")){
                      switch (into){
                          case "Gram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000*inputVal));break;
                          case "Milligram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000000*inputVal));break;
                          case "Tonne": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.001*inputVal));break;
                          case "Quintal": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.01*inputVal));break;
                          case "Microgram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000000000*inputVal));break;
                          case "Pound": resultant.setText(String.valueOf("The value in " + into + " is " + (double)2.20462*inputVal));break;
                          case "Ounce": resultant.setText(String.valueOf("The value in " + into + " is " + (double)35.274*inputVal));break;
                      }
                  }if(from.equals("Gram")){
                      switch (into){
                          case "Kilogram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.001*inputVal));break;
                          case "Milligram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000*inputVal));break;
                          case "Tonne": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.001*0.001*inputVal));break;
                          case "Quintal": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.01*0.001*inputVal));break;
                          case "Microgram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000000000*0.001*inputVal));break;
                          case "Pound": resultant.setText(String.valueOf("The value in " + into + " is " + (double)2.20462*0.001*inputVal));break;
                          case "Ounce": resultant.setText(String.valueOf("The value in " + into + " is " + (double)35.274*0.001*inputVal));break;
                      }
                  }if(from.equals("Milligram")){
                      switch (into){
                          case "Gram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000*0.001*0.001*inputVal));break;
                          case "Kilogram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.001*0.001*inputVal));break;
                          case "Tonne": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.001*0.001*0.001*inputVal));break;
                          case "Quintal": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.01*0.001*0.001*inputVal));break;
                          case "Microgram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000000000*0.001*0.001*inputVal));break;
                          case "Pound": resultant.setText(String.valueOf("The value in " + into + " is " + (double)2.20462*0.001*0.001*inputVal));break;
                          case "Ounce": resultant.setText(String.valueOf("The value in " + into + " is " + (double)35.274*0.001*0.001*inputVal));break;
                      }
                  }if(from.equals("Tonne")){
                      switch (into){
                          case "Gram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000*1000*inputVal));break;
                          case "Milligram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000000*1000*inputVal));break;
                          case "Kilogram": resultant.setText(String.valueOf("The value in " + into + " is " +(double) 1000*inputVal));break;
                          case "Quintal": resultant.setText(String.valueOf("The value in " + into + " is " +(double) 0.01*1000*inputVal));break;
                          case "Microgram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000000000*1000*inputVal));break;
                          case "Pound": resultant.setText(String.valueOf("The value in " + into + " is " + (double)2.20462*1000*inputVal));break;
                          case "Ounce": resultant.setText(String.valueOf("The value in " + into + " is " + (double)35.274*1000*inputVal));break;
                      }
                  }if(from.equals("Quintal")){
                      switch (into){
                          case "Gram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000*100*inputVal));break;
                          case "Milligram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000000*100*inputVal));break;
                          case "Tonne": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.001*100*inputVal));break;
                          case "Kilogram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)100*inputVal));break;
                          case "Microgram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000000000*100*inputVal));break;
                          case "Pound": resultant.setText(String.valueOf("The value in " + into + " is " + (double)2.20462*100*inputVal));break;
                          case "Ounce": resultant.setText(String.valueOf("The value in " + into + " is " + (double)35.274*100*inputVal));break;
                      }
                  }if(from.equals("Microgram")){
                      switch (into){
                          case "Gram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000*0.000000001*inputVal));break;
                          case "Milligram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1000000*0.000000001*inputVal));break;
                          case "Tonne": resultant.setText(String.valueOf("The value in " + into + " is " +(double) 0.001*0.000000001*inputVal));break;
                          case "Quintal": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.01*0.000000001*inputVal));break;
                          case "Kilogram": resultant.setText(String.valueOf("The value in " + into + " is " +(double) 0.000000001*inputVal));break;
                          case "Pound": resultant.setText(String.valueOf("The value in " + into + " is " + (double)2.20462*0.000000001*inputVal));break;
                          case "Ounce": resultant.setText(String.valueOf("The value in " + into + " is " + (double)35.274*0.000000001*inputVal));break;
                      }
                  }if(from.equals("Pound")){
                      switch (into){
                          case "Gram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.453592*1000*inputVal));break;
                          case "Milligram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.453592*1000000*inputVal));break;
                          case "Tonne": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.453592*0.001*inputVal));break;
                          case "Quintal": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.453592*0.01*inputVal));break;
                          case "Microgram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.453592*1000000000*inputVal));break;
                          case "Kilogram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.453592*inputVal));break;
                          case "Ounce": resultant.setText(String.valueOf("The value in " + into + " is " + (double)16*inputVal));break;
                      }
                  }if(from.equals("Ounce")){
                      switch (into){
                          case "Gram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.0283495*1000*inputVal));break;
                          case "Milligram": resultant.setText(String.valueOf("The value in " + into + " is " +(double)0.0283495* 1000000*inputVal));break;
                          case "Tonne": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.0283495*0.001*inputVal));break;
                          case "Quintal": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.0283495*0.01*inputVal));break;
                          case "Microgram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.0283495*1000000000*inputVal));break;
                          case "Pound": resultant.setText(String.valueOf("The value in " + into + " is " +(double)0.625*inputVal));break;
                          case "Kilogram": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.0283495*inputVal));break;
                      }
                  }if(from.equals("Meter")){
                      switch (into){
                          case "Kilometer": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.001*inputVal));break;
                          case "Foot": resultant.setText(String.valueOf("The value in " + into + " is " + (double)3.28084*inputVal));break;
                          case "Yard": resultant.setText(String.valueOf("The value in " + into + " is " + (double)1.093613*inputVal));break;
                          case "Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.0006213712121*inputVal));break;
                          case "Centimeter": resultant.setText(String.valueOf("The value in " + into + " is " + (double)100*inputVal));break;
                          case "Inches": resultant.setText(String.valueOf("The value in " + into + " is " + (double)39.37008*inputVal));break;
                          case "Nautical Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (double)0.00054*inputVal));break;
                      }
                  }else if (from.equals("Kilometer")) {
                      switch (into) {
                          case "Meter": resultant.setText(String.valueOf("The value in " + into + " is " + (1000 * inputVal))); break;
                          case "Foot": resultant.setText(String.valueOf("The value in " + into + " is " + (3280.84 * inputVal))); break;
                          case "Yard": resultant.setText(String.valueOf("The value in " + into + " is " + (1093.613 * inputVal))); break;
                          case "Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.621371 * inputVal))); break;
                          case "Centimeter": resultant.setText(String.valueOf("The value in " + into + " is " + (100000 * inputVal))); break;
                          case "Inches": resultant.setText(String.valueOf("The value in " + into + " is " + (39370.08 * inputVal))); break;
                          case "Nautical Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.54 * inputVal))); break;
                      }
                  }
                  else if (from.equals("Foot")) {
                      switch (into) {
                          case "Meter": resultant.setText(String.valueOf("The value in " + into + " is " + (0.3048 * inputVal))); break;
                          case "Kilometer": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0003048 * inputVal))); break;
                          case "Yard": resultant.setText(String.valueOf("The value in " + into + " is " + (0.333333 * inputVal))); break;
                          case "Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.000189394 * inputVal))); break;
                          case "Centimeter": resultant.setText(String.valueOf("The value in " + into + " is " + (30.48 * inputVal))); break;
                          case "Inches": resultant.setText(String.valueOf("The value in " + into + " is " + (12 * inputVal))); break;
                          case "Nautical Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.000164579 * inputVal))); break;
                      }
                  }
                  else if (from.equals("Yard")) {
                      switch (into) {
                          case "Meter": resultant.setText(String.valueOf("The value in " + into + " is " + (0.9144 * inputVal))); break;
                          case "Kilometer": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0009144 * inputVal))); break;
                          case "Foot": resultant.setText(String.valueOf("The value in " + into + " is " + (3 * inputVal))); break;
                          case "Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.000568182 * inputVal))); break;
                          case "Centimeter": resultant.setText(String.valueOf("The value in " + into + " is " + (91.44 * inputVal))); break;
                          case "Inches": resultant.setText(String.valueOf("The value in " + into + " is " + (36 * inputVal))); break;
                          case "Nautical Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.000493737 * inputVal))); break;
                      }
                  }
                  else if (from.equals("Mile")) {
                      switch (into) {
                          case "Meter": resultant.setText(String.valueOf("The value in " + into + " is " + (1609.34 * inputVal))); break;
                          case "Kilometer": resultant.setText(String.valueOf("The value in " + into + " is " + (1.60934 * inputVal))); break;
                          case "Foot": resultant.setText(String.valueOf("The value in " + into + " is " + (5280 * inputVal))); break;
                          case "Yard": resultant.setText(String.valueOf("The value in " + into + " is " + (1760 * inputVal))); break;
                          case "Centimeter": resultant.setText(String.valueOf("The value in " + into + " is " + (160934 * inputVal))); break;
                          case "Inches": resultant.setText(String.valueOf("The value in " + into + " is " + (63360 * inputVal))); break;
                          case "Nautical Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.868976 * inputVal))); break;
                      }
                  }
                  else if (from.equals("Centimeter")) {
                      switch (into) {
                          case "Meter": resultant.setText(String.valueOf("The value in " + into + " is " + (0.01 * inputVal))); break;
                          case "Kilometer": resultant.setText(String.valueOf("The value in " + into + " is " + (0.00001 * inputVal))); break;
                          case "Foot": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0328084 * inputVal))); break;
                          case "Yard": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0109361 * inputVal))); break;
                          case "Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0000062137 * inputVal))); break;
                          case "Inches": resultant.setText(String.valueOf("The value in " + into + " is " + (0.393701 * inputVal))); break;
                          case "Nautical Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0000054 * inputVal))); break;
                      }
                  }
                  else if (from.equals("Inches")) {
                      switch (into) {
                          case "Meter": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0254 * inputVal))); break;
                          case "Kilometer": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0000254 * inputVal))); break;
                          case "Foot": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0833333 * inputVal))); break;
                          case "Yard": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0277778 * inputVal))); break;
                          case "Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0000157828 * inputVal))); break;
                          case "Centimeter": resultant.setText(String.valueOf("The value in " + into + " is " + (2.54 * inputVal))); break;
                          case "Nautical Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (0.0000137149 * inputVal))); break;
                      }
                  }
                  else if (from.equals("Nautical Mile")) {
                      switch (into) {
                          case "Meter": resultant.setText(String.valueOf("The value in " + into + " is " + (1852 * inputVal))); break;
                          case "Kilometer": resultant.setText(String.valueOf("The value in " + into + " is " + (1.852 * inputVal))); break;
                          case "Foot": resultant.setText(String.valueOf("The value in " + into + " is " + (6076.12 * inputVal))); break;
                          case "Yard": resultant.setText(String.valueOf("The value in " + into + " is " + (2025.37 * inputVal))); break;
                          case "Mile": resultant.setText(String.valueOf("The value in " + into + " is " + (1.15078 * inputVal))); break;
                          case "Centimeter": resultant.setText(String.valueOf("The value in " + into + " is " + (185200 * inputVal))); break;
                          case "Inches": resultant.setText(String.valueOf("The value in " + into + " is " + (72913.4 * inputVal))); break;
                      }
                  }
              }
          } catch (Exception e) {
              Toast.makeText(this, "ERROR!", Toast.LENGTH_SHORT).show();
          }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int parentId = parent.getId();
        if (parentId == R.id.units_spinner) {
            String item = parent.getItemAtPosition(position).toString();
            int arrayId = item.equals("Length") ? R.array.length_units_array : R.array.weights_units_array;

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, arrayId, android.R.layout.simple_spinner_item
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_to_1.setAdapter(adapter);
            spinner_to_2.setAdapter(adapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
