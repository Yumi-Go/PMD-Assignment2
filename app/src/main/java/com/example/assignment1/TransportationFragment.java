package com.example.assignment1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Objects;

public class TransportationFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    GoogleMap gMap;
    private Marker currentMarker = null;
//    MapView mapView = null;
    Location currentLocation = null;
    double currentLatitude = 0;
    double currentLongitude = 0;

    Button btLocation;
    TextView tvLatitude, tvLongitude;
    FusedLocationProviderClient client;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }


    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,false);
                if (fineLocationGranted != null && fineLocationGranted) {
                    getCurrentLocation();
//                    updateLocation();
                } else if (coarseLocationGranted != null && coarseLocationGranted) {
                    getCurrentLocation();
//                    updateLocation();
                    Toast.makeText(getActivity(), "Only approximate location access granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
            });


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_transportation, container, false);

//        mapView = rootView.findViewById(R.id.map);
        btLocation = rootView.findViewById(R.id.bt_location);
        tvLatitude = rootView.findViewById(R.id.tv_latitude);
        tvLongitude = rootView.findViewById(R.id.tv_longitude);

        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(getActivity());

        btLocation.setOnClickListener(view -> {
            locationPermissionRequest.launch(new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });

//            if (ContextCompat.checkSelfPermission(getActivity(),
//                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                    ContextCompat.checkSelfPermission(getActivity(),
//                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                getCurrentLocation();
//            }
//            else {
//                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
//                                Manifest.permission.ACCESS_COARSE_LOCATION }, 100);
//            }
        });
        return rootView;
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 100 && (grantResults.length > 0) &&
//                (grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
//            getCurrentLocation();
//        }
//        else {
//            Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
//        }
//    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        tvLatitude.setText(String.valueOf(location.getLatitude()));
                        tvLongitude.setText(String.valueOf(location.getLongitude()));
                    } else {
                        LocationRequest locationRequest =
                                new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                        .setInterval(10000).setFastestInterval(1000).setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                currentLocation = locationResult.getLastLocation();
                                if (currentLocation != null) {
                                    currentLatitude = currentLocation.getLatitude();
                                    currentLongitude = currentLocation.getLongitude();
                                    updateLocation(currentLocation);
                                    tvLatitude.setText(String.valueOf(currentLatitude));
                                    tvLongitude.setText(String.valueOf(currentLongitude));
                                }
                            }
                        };
                        client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        } else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        LatLng DEFAULT_LatLng = new LatLng(37.5665, 126.9780);

        MarkerOptions marker = new MarkerOptions();
        marker.position(DEFAULT_LatLng);
        marker.title("SEOUL");
        marker.snippet("South Korea");

        Objects.requireNonNull(googleMap.addMarker(marker)).showInfoWindow();
        googleMap.setOnInfoWindowClickListener(this);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LatLng, 5));
    }


    public void updateLocation(Location location) {
        if (currentMarker != null) currentMarker.remove();
        LatLng current_LatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions marker = new MarkerOptions();
        marker.position(current_LatLng);
        marker.title("You");
        marker.draggable(true);

        currentMarker = gMap.addMarker(marker);


        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(current_LatLng);
        gMap.moveCamera(cameraUpdate);


//        Objects.requireNonNull(gMap.addMarker(marker)).showInfoWindow();
//        gMap.setOnInfoWindowClickListener(this);
//        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(current_LatLng, 5));
    }


    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {

    }
}













//public class TransportationFragment extends Fragment {
//
//    TabLayout tabLayout;
//    ViewPager2 viewPager;
//    TabPagerAdapter adapter;
//    ArrayList<String> tabTitles;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_transportation, container, false);
//
//        tabLayout = rootView.findViewById(R.id.transportation_tab_layout);
//        viewPager = rootView.findViewById(R.id.transportationTabViewpager);
//
//        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(new Transportation_Tab1Fragment());
//        fragments.add(new Transportation_Tab2Fragment());
//        fragments.add(new Transportation_Tab3Fragment());
//
//        adapter = new TabPagerAdapter(requireActivity());
//        adapter.setData(fragments);
//        viewPager.setAdapter(adapter);
//
//        tabTitles = new ArrayList<>();
//        tabTitles.add("Bus");
//        tabTitles.add("Subway");
//        tabTitles.add("Parking Lot");
//
//
//        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(tabTitles.get(position))).attach();
//
//
//        return rootView;
//    }
//}