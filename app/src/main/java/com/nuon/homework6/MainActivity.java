package com.nuon.homework6;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public City[] cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        cities = new City[]{
                new City("Banteay Meanchey", 13.6673, 102.8979, "banteay_meanchey" ),
                new City("Battambang", 13.0957, 103.2022, "battambang"),
                new City("Kampong Cham", 12.0000, 105.4500, "kampong_cham"),
                new City("Kampong Chhnang", 12.2500, 104.6667, "kampong_chhnang"),
                new City("Kampong Speu", 11.4500, 104.5200, "kampong_speu"),
                new City("Kampong Thom", 12.7111, 104.8887, "kampong_thom"),
                new City("Kampot", 10.6167, 104.1833, "kampot"),
                new City("Kandal", 11.2647, 105.150, "kampot"),
                new City("Kep", 10.536389, 104.355833, "kep"),
                new City("Koh Kong", 11.6175, 102.9849, "koh_kong"),
                new City("Kratie", 12.4881, 106.0167, "kratie"),
                new City("Mondulkiri", 12.4500, 107.2000, "mondulkiri"),
                new City("Oddar Meanchey", 14.0000, 103.1000, "oddar_meanchey"),
                new City("Pailin", 12.8500, 102.6093, "pailin"),
                new City("Phnom Penh", 11.5564, 104.9283, "phnom_penh"),
                new City("Preah Sihanouk", 10.6253, 103.5234, "preah_sihanouk"),
                new City("Preah Vihear", 13.8069, 104.9723, "preah_vihear"),
                new City("Prey Veng", 11.4849, 105.3247, "prey_veng"),
                new City("Pursat", 12.5336, 103.9167, "pursat"),
                new City("Ratanakiri", 13.7367, 106.9867, "ratanakiri"),
                new City("Siem Reap", 13.3618, 103.8606, "siem_reap"),
                new City("Stung Treng", 13.5259, 105.9740, "stung_treng"),
                new City("Svay Rieng", 11.0879, 105.7981, "svay_rieng"),
                new City("Takéo", 10.9908, 104.7848, "takeo"),
                new City("Tboung Khmum", 11.8000, 105.6000, "tboung_khmum"),
        };

        ListView list = findViewById(R.id.listCity);
        list.setAdapter(new RowIconAdapter());
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City selectedCity = cities[position];
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.list_row, null);
                ImageView imageView = dialogLayout.findViewById(R.id.image_view_city);
                if (imageView != null) {
                    @SuppressLint("DiscouragedApi") int imageResourceId = MainActivity.this.getResources().getIdentifier(selectedCity.getImage(), "drawable", MainActivity.this.getPackageName());
                    imageView.setImageResource(imageResourceId);
                } else {
                    Log.d("ListViewClick", "ImageView not found in the layout");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(selectedCity.getProvince());
                TextView textView = dialogLayout.findViewById(R.id.text_view_city_name);
                textView.setText("Latitude: " + selectedCity.getLatitude() + "\nLongitude: " + selectedCity.getLongitude());
                builder.setView(dialogLayout);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    public static class City {
        private String province;
        private double longitude;
        private double latitude;
        private String imageRepresent;
        public City(String province, double latitude, double longitude, String imageRepresent) {
            this.province = province;
            this.latitude = latitude;
            this.longitude = longitude;
            this.imageRepresent = imageRepresent;
        }

        public String getProvince() {

            return province;
        }
        public void setProvince(String province) {

            this.province = province;
        }
        public double getLongitude() {

            return longitude;
        }
        public void setLongitude(double longitude) {

            this.longitude = longitude;

        }
        public double getLatitude() {
            return latitude;
        }
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getImage() {
            return this.imageRepresent; // Ensure this matches the variable that holds the image name
        }

        @NonNull
        public String toString(){
            return "Longitude: " + longitude + "Latitude: " + latitude;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    class RowIconAdapter extends ArrayAdapter<City> {
        public RowIconAdapter() {

            super(MainActivity.this, R.layout.list_row, cities);
        }
        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
            }
            ImageView imageView = convertView.findViewById(R.id.image_view_city);
            TextView textView = convertView.findViewById(R.id.text_view_city_name);
            if (imageView == null || textView == null) {
                throw new IllegalStateException("Required view not found in layout");
            }

            City city = getItem(position);
            if (city != null) {
                textView.setText(city.getProvince());
                switch (city.getProvince()) {
                    case "Battambang":
                        imageView.setImageResource(R.drawable.battambang);
                        break;
                    case "Banteay Meanchey":
                        imageView.setImageResource(R.drawable.banteay_meanchey);
                        break;
                    case "Kampong Cham":
                        imageView.setImageResource(R.drawable.kampong_cham);
                        break;
                    case "Kampong Chhnang":
                        imageView.setImageResource(R.drawable.kampong_chhnang);
                        break;
                    case "Kampong Speu":
                        imageView.setImageResource(R.drawable.kampong_speu);
                        break;
                    case "Kampong Thom":
                        imageView.setImageResource(R.drawable.kampong_thom);
                        break;
                    case "Kampot":
                        imageView.setImageResource(R.drawable.kampot);
                        break;
                    case "Kandal":
                        imageView.setImageResource(R.drawable.kandal);
                        break;
                    case "Kep":
                        imageView.setImageResource(R.drawable.kep);
                        break;
                    case "Koh Kong":
                        imageView.setImageResource(R.drawable.koh_kong);
                        break;
                    case "Kratie":
                        imageView.setImageResource(R.drawable.kratie);
                        break;
                    case "Mondulkiri":
                        imageView.setImageResource(R.drawable.mondulkiri);
                        break;
                    case "Oddar Meanchey":
                        imageView.setImageResource(R.drawable.oddar_meanchey);
                        break;
                    case "Pailin":
                        imageView.setImageResource(R.drawable.pailin);
                        break;
                    case "Phnom Penh":
                        imageView.setImageResource(R.drawable.phnom_enh);
                        break;
                    case "Preah Sihanouk":
                        imageView.setImageResource(R.drawable.preah_sihanouk);
                        break;
                    case "Preah Vihear":
                        imageView.setImageResource(R.drawable.preah_vihear);
                        break;
                    case "Prey Veng":
                        imageView.setImageResource(R.drawable.prey_veng);
                        break;
                    case "Pursat":
                        imageView.setImageResource(R.drawable.pursat);
                        break;
                    case "Ratanakiri":
                        imageView.setImageResource(R.drawable.ratanakiri);
                        break;
                    case "Siem Reap":
                        imageView.setImageResource(R.drawable.siem_reap);
                        break;
                    case "Stung Treng":
                        imageView.setImageResource(R.drawable.stung_treng);
                        break;
                    case "Svay Rieng":
                        imageView.setImageResource(R.drawable.svay_rieng);
                        break;
                    case "Takéo":
                        imageView.setImageResource(R.drawable.takeo);
                        break;
                    case "Tboung Khmum":
                        imageView.setImageResource(R.drawable.tboung_khmum);
                        break;
                }
            }
            return convertView;
        }

    }

}