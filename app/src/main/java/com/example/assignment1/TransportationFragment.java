package com.example.assignment1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class TransportationFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    GoogleMap gMap;
    private Marker currentMarker = null;
    Location currentLocation = null;
    double currentLatitude = 0;
    double currentLongitude = 0;
//    String uri_string = "";


    Button btnReset, btnGmap, btnBus, btnSubway;
//    TextView tvLatitude, tvLongitude;
    FusedLocationProviderClient client;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_transportation, container, false);

        btnReset = rootView.findViewById(R.id.btn_reset_marker);
        btnGmap = rootView.findViewById(R.id.btn_googleMap);
        btnBus = rootView.findViewById(R.id.btn_bus);
        btnSubway = rootView.findViewById(R.id.btn_subway);

//        tvLatitude = rootView.findViewById(R.id.tv_latitude);
//        tvLongitude = rootView.findViewById(R.id.tv_longitude);
        client = LocationServices.getFusedLocationProviderClient(getActivity());


        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,false);
                    if (fineLocationGranted != null && fineLocationGranted) {
                        getCurrentLocation();
                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        getCurrentLocation();
                        Toast.makeText(getActivity(), "Only approximate location access granted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                });


        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
        btnReset.setOnClickListener(view -> {getCurrentLocation();});

        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.transMap);
        mapFragment.getMapAsync(this);

        btnGmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
                Uri geouri = Uri.parse(String.format("geo:%f,%f", currentLatitude, currentLongitude));
                Intent geomap = new Intent(Intent.ACTION_VIEW, geouri);
                geomap.setPackage("com.google.android.apps.maps");  // 구글맵으로 열기
                try {
                    startActivity(geomap);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }

////                String uri_string = "geo: " + currentLatitude + ", " + currentLongitude;
//                Uri location = Uri.parse(uri_string);
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                try {
//                    startActivity(mapIntent);
//                } catch (ActivityNotFoundException e) {
//                    e.printStackTrace();
//                }
            }
        });

        btnBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Transportation_Bus fragmentBus = new Transportation_Bus();
                transaction.replace(R.id.frame_layout, fragmentBus).commitAllowingStateLoss();
            }
        });

        btnSubway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Transportation_Subway fragmentSubway = new Transportation_Subway();
                transaction.replace(R.id.frame_layout, fragmentSubway).commitAllowingStateLoss();
            }
        });

        return rootView;
    }


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
                        updateLocation(location);
//                        tvLatitude.setText(String.valueOf(location.getLatitude()));
//                        tvLongitude.setText(String.valueOf(location.getLongitude()));
                    } else {
                        LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                        .setInterval(10000).setFastestInterval(1000).setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                currentLocation = locationResult.getLastLocation();
                                if (currentLocation != null) {
                                    currentLatitude = currentLocation.getLatitude();
                                    currentLongitude = currentLocation.getLongitude();
                                    updateLocation(currentLocation);
//                                    uri_string = "\"geo: " + currentLatitude + ", " + currentLongitude + "\"";
//                                    tvLatitude.setText(String.valueOf(currentLatitude));
//                                    tvLongitude.setText(String.valueOf(currentLongitude));
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
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng DEFAULT_LatLng = new LatLng(37.5665, 126.9780);

        MarkerOptions marker = new MarkerOptions();
        marker.position(DEFAULT_LatLng);
        marker.title("SEOUL");
        marker.snippet("South Korea");

        Objects.requireNonNull(googleMap.addMarker(marker)).showInfoWindow();
        gMap.setOnInfoWindowClickListener(this);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LatLng, 5));

        double defaultLatitude = DEFAULT_LatLng.latitude;
        double defaultLongitude = DEFAULT_LatLng.longitude;
//        tvLatitude.setText(String.valueOf(defaultLatitude));
//        tvLongitude.setText(String.valueOf(defaultLongitude));
    }


    public void updateLocation(Location location) {
        if (currentMarker != null) currentMarker.remove();
        LatLng current_LatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions marker = new MarkerOptions();
        marker.position(current_LatLng);
        marker.title("I'm here");
        marker.snippet("Latitude: " + current_LatLng.latitude + " / Longitude: " + current_LatLng.longitude);
        marker.draggable(true);
        currentMarker = gMap.addMarker(marker);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(current_LatLng, 13);
        gMap.moveCamera(cameraUpdate);

        currentLatitude = current_LatLng.latitude;
        currentLongitude = current_LatLng.longitude;

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