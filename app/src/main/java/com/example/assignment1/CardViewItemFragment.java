package com.example.assignment1;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;


public class CardViewItemFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    String category, name, district, address, description;
    double latitude, longitude;
    int image;

    ImageView introImageView;
    TextView nameTextView,categoryTextView, descriptionTextView, addressTextView;

    GoogleMap gMap;

    public CardViewItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_card_view_item, container, false);

        if (getArguments() != null) {
//            category = getArguments().getString("category");
            name = getArguments().getString("name");
//            district = getArguments().getString("district");
//            address = getArguments().getString("address");
//            latitude = Double.parseDouble(getArguments().getString("latitude"));
//            longitude = Double.parseDouble(getArguments().getString("longitude"));
//            description = getArguments().getString("description");
//            image = Integer.parseInt(getArguments().getString("image"));
            for (int i = 0; i < MainActivity.itemsList.size(); i++) {
                if (Objects.equals(MainActivity.itemsList.get(i).getName(), name)) {
                    category = MainActivity.itemsList.get(i).getCategory();
                    district = MainActivity.itemsList.get(i).getDistrict();
                    address = MainActivity.itemsList.get(i).getAddress();
                    latitude = MainActivity.itemsList.get(i).getLatitude();
                    longitude = MainActivity.itemsList.get(i).getLongitude();
                    description = MainActivity.itemsList.get(i).getDescription();
                    image = MainActivity.itemsList.get(i).getImage();
                }
            }

//            categoryTextView = rootView.findViewById(R.id.categoryTv);
            nameTextView = rootView.findViewById(R.id.nameTv);
            descriptionTextView = rootView.findViewById(R.id.descriptionTv);
            addressTextView = rootView.findViewById(R.id.addressTv);
            introImageView = rootView.findViewById(R.id.introIv);

//            categoryTextView.setText(category);
            nameTextView.setText(name);
            descriptionTextView.setText(description);
            addressTextView.setText(address);
            introImageView.setImageResource(image);

            ActivityResultLauncher<String[]> locationPermissionRequest =
                    registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                        Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,false);
//                        if (fineLocationGranted != null && fineLocationGranted) {
//                            getCurrentLocation();
//                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
//                            getCurrentLocation();
//                            Toast.makeText(getActivity(), "Only approximate location access granted", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
//                        }
                    });


            locationPermissionRequest.launch(new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});

            SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.itemMap);
            mapFragment.getMapAsync(this);

        }

        return rootView;
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (getArguments() != null) {
            gMap = googleMap;
            LatLng location = new LatLng(latitude, longitude);

            MarkerOptions marker = new MarkerOptions();
            marker.position(location);
            marker.title(name);
            marker.snippet("Seoul");

            Objects.requireNonNull(googleMap.addMarker(marker)).showInfoWindow();
            gMap.setOnInfoWindowClickListener(this);
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        }
    }

}