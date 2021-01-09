package com.example.ustoyou.model

import com.example.ustoyou.R

class CornHarvestProviders {
    fun getCornHarvestProviders(): ArrayList<Provider>{
        val providers = ArrayList<Provider>()
        providers.add(Provider(R.drawable.manlogo, "Lupas Mihai", "Floresti, Romania", 15))
        providers.add(Provider(R.drawable.manlogo, "Pop Gheorghe", "Sic, Romania", 14))
        providers.add(Provider(R.drawable.manlogo, "Turdean Farm", "Turda, Romania", 20))
        return providers
    }
}