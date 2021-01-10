package com.example.ustoyou.model

import com.example.ustoyou.R

class ITProviders {
    fun getITProviders(): ArrayList<ITProvider>{
        val providers = ArrayList<ITProvider>()
        providers.add(ITProvider(R.drawable.luci, "Vacariu Lucia", "Cluj, Romania", 15,"VIVADO"))
        providers.add(ITProvider(R.drawable.ciprian, "Oprisa Ciprian", "Cluj, Romania", 14, "GENERAL"))
        providers.add(ITProvider(R.drawable.potolea, "Potolea Rodica", "Cluj, Romania", 20, "GENERAL BASIC"))
        return providers
    }
}