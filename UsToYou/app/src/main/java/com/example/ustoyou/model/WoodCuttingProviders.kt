package com.example.ustoyou.model

import com.example.ustoyou.R

class WoodCuttingProviders {
    fun getWoodCuttingProviders(): ArrayList<Provider>{
        val providers = ArrayList<Provider>()
        providers.add(Provider(R.drawable.manlogo, "Pop Nicolae", "Sic, Romania", 27))
        providers.add(Provider(R.drawable.manlogo, "Man Stefan", "Gherla, Romania", 25))
        providers.add(Provider(R.drawable.manlogo, "Barbos Marian", "Gilau, Romania", 24))
        return providers
    }
}