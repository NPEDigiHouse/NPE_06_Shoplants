package com.example.npe_06_shoplants.data;

import com.example.npe_06_shoplants.models.Plant;

import java.util.ArrayList;
import java.util.List;

public class PlantsData {
    private static final String[] plantName = {
            "Bonsai",
            "Bambu",
            "Lavender",
            "Red Plant",
            "Kaktus",
            "Ficus retusa",
    };

    private static final String[] imageURL = {
            "https://firebasestorage.googleapis.com/v0/b/shoplants-c2e1e.appspot.com/o/plants%2Fkisspng-flowerpot-bonsai-loudspeaker-plant-potted-plants-5a997bbc396190.8508827815200081242351.png?alt=media&token=7c783637-4ad8-46b7-b110-bca770f87ed8",
            "https://firebasestorage.googleapis.com/v0/b/shoplants-c2e1e.appspot.com/o/plants%2Fkisspng-flowerpot-houseplant-bamboo-potted-plants-decorate-pictures-5a97eb48359c68.2720286015199056082196.png?alt=media&token=645f65d3-d430-4df9-8fa3-f58709c4d6ac",
            "https://firebasestorage.googleapis.com/v0/b/shoplants-c2e1e.appspot.com/o/plants%2Flavender%202.png?alt=media&token=fab63555-15d8-4536-b8fc-193816b74a12",
            "https://firebasestorage.googleapis.com/v0/b/shoplants-c2e1e.appspot.com/o/plants%2Fkisspng-houseplant-flowerpot-indoor-plant-potted-plants-5a8b8a300dda37.9780680715190943200568.png?alt=media&token=b52a026a-fc6f-4e4c-98b9-17a4e4156f8e",
            "https://firebasestorage.googleapis.com/v0/b/shoplants-c2e1e.appspot.com/o/plants%2Fkaktus.png?alt=media&token=63908ec1-7991-4b30-8836-979d72b42274",
            "https://firebasestorage.googleapis.com/v0/b/shoplants-c2e1e.appspot.com/o/plants%2Fkisspng-ficus-retusa-houseplant-flower-garden-potted-plant-5abc15c61e1cc0.9182933415222757821233.png?alt=media&token=5d0b10b4-332a-4799-8164-7d96fdc83886",
    };

    private static final int[] price = {
            500000,
            600000,
            700000,
            800000,
            900000,
            1000000
    };

    public static List<Plant> getPlantsData() {
        List<Plant> list = new ArrayList<>();
        for (int i = 0; i < plantName.length; i++) {
            Plant plant = new Plant();
            plant.setName(plantName[i]);
            plant.setImageUrl(imageURL[i]);
            plant.setPrice(price[i]);

            list.add(plant);
        }

        return list;
    }
}
