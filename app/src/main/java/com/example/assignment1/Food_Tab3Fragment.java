package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Food_Tab3Fragment extends Fragment {

    RecyclerView recyclerView;
    List<RecyclerItem> itemsList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_food_tab3, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        initView();

        return rootView;
    }


    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(itemsList);
        recyclerView.setAdapter(adapter);
    }

    private void initItems() {
        itemsList.add(new RecyclerItem("Wine", "Big Lights", "Yongsan-gu", getString(R.string.food_biglights_address), 37.53543842594574, 127.0101485275639, getString(R.string.food_biglights_description), R.drawable.food_big_lights));
        itemsList.add(new RecyclerItem("Cocktail", "Bar Musk", "Gangnam-gu", getString(R.string.food_barmusk_address), 37.52266059999937, 127.03480512369961, getString(R.string.food_barmusk_description), R.drawable.food_bar_musk));
        itemsList.add(new RecyclerItem("Other", "Bar Tea Scent", "Gangnam-gu", getString(R.string.food_barteascent_address), 37.5266676844209, 127.04166940977258, getString(R.string.food_barteascent_description), R.drawable.food_bar_tea_scent));
        itemsList.add(new RecyclerItem("Bar", "Miners Bar", "Yongsan-gu", getString(R.string.food_minersbar_address), 37.53359360505004, 127.00642764754234, getString(R.string.food_minersbar_description), R.drawable.food_miners_bar));
        itemsList.add(new RecyclerItem("Cocktail", "Marque d'Amour", "Jung-gu", getString(R.string.food_marquedamour_address), 37.55983617447398, 126.97948655087812, getString(R.string.food_marquedamour_description), R.drawable.food_marque_d_amour));
        itemsList.add(new RecyclerItem("Bar", "Le Chamber", "Gangnam-gu", getString(R.string.food_lechamber_address), 37.52621618441137, 127.0411132975041, getString(R.string.food_lechamber_description), R.drawable.food_le_chamber));
    }

}