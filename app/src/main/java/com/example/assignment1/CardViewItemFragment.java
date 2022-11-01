package com.example.assignment1;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.MapsInitializer.Renderer;
import com.google.android.gms.maps.OnMapsSdkInitializedCallback;
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
    RecyclerItem item;

    ImageView introImageView;
    TextView nameTextView,categoryTextView, descriptionTextView, addressTextView;

    GoogleMap gMap;

//    public CardViewItemFragment() {
//        // Required empty public constructor
//    }

    public CardViewItemFragment(RecyclerItem item) {
        this.item = item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.
//        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                // Handle the back button event
//                NavHostFragment.findNavController(this).navigateUp();
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
//
//        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_card_view_item, container, false);

        name = item.getName();
        category = item.getCategory();
        district = item.getDistrict();
        address = item.getAddress();
        latitude = item.getLatitude();
        longitude = item.getLongitude();
        description = item.getDescription();
        image = item.getImage();

        nameTextView = rootView.findViewById(R.id.nameTv);
        descriptionTextView = rootView.findViewById(R.id.descriptionTv);
        addressTextView = rootView.findViewById(R.id.addressTv);
        introImageView = rootView.findViewById(R.id.introIv);

        nameTextView.setText(name);
        descriptionTextView.setText(description);
        addressTextView.setText(address);
        introImageView.setImageResource(image);

//        MapsInitializer.initialize(requireContext(), MapsInitializer.Renderer.LATEST, this);
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
//                    Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
//                    Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,false);
//                    if (coarseLocationGranted != null && coarseLocationGranted) {
//                        Toast.makeText(getActivity(), "Only approximate location access granted", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
//                    }
                });


        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});

        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.itemMap);
        mapFragment.getMapAsync(this);


        return rootView;
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        latitude = item.getLatitude();
        longitude = item.getLongitude();
        gMap = googleMap;
        LatLng location = new LatLng(latitude, longitude);

//        String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=%d",
//                latitude, longitude, 15);


        gMap.addMarker(new MarkerOptions().position(location).title(name));
//        gMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        gMap.setOnInfoWindowClickListener(this);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

//        MarkerOptions marker = new MarkerOptions();
//        marker.position(location);
//        marker.title(name);
//        marker.snippet("Seoul");
//
//        Objects.requireNonNull(googleMap.addMarker(marker)).showInfoWindow();
//        gMap.setOnInfoWindowClickListener(this);
//        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }

//    @Override
//    public void onMapsSdkInitialized(@NonNull Renderer renderer) {
//        switch (renderer) {
//            case LATEST:
//                Log.d("MapsDemo", "The latest version of the renderer is used.");
//                break;
//            case LEGACY:
//                Log.d("MapsDemo", "The legacy version of the renderer is used.");
//                break;
//        }
//    }
}