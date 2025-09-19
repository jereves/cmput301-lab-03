package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener, EditCityFragment.EditCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;
    private int accessedPos;

    // override interface
    public void addCity(City city) {
        cityAdapter.add(city);

    }

    public void editCity(String cityName, String provinceName) {
        // edit cclass
        editCityClass(cityName, provinceName);
        // notify adapter
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] cities = {
//                "Edmonton", "Vancouver", "Moscow",
//                "Sydney", "Berlin", "Vienna",
//                "Tokyo", "Beijing", "Osaka", "New Delhi"
//        };
        // two parallel string lists
        String[] cities = { "Edmonton", "Vancouver", "Toronto" };
        String[] provinces = { "AB", "BC", "ON" };

        dataList = new ArrayList<City>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });

        // if someone clicks city list edit it
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                accessedPos = i;

                EditCityFragment FragmentEdit = new EditCityFragment();

                // set the City
                City accessedCity= dataList.get(i);
                FragmentEdit.setCityClass(accessedCity);

                FragmentEdit.show(getSupportFragmentManager(), "Edit City");
            }
        });
    }
    protected void editCityClass(String cityName, String provinceName) {
        City modifyCity = dataList.get(accessedPos);
        modifyCity.setName(cityName);
        modifyCity.setProvince(provinceName);
    }

    }

