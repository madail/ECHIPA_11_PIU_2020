package com.example.ustoyou.model

class MyServices {
    private var services = ArrayList<Service>()

    private object INSTANCE {
        val INSTANCE = MyServices()
    }

    companion object {
        val instance : MyServices by lazy { INSTANCE.INSTANCE }
    }

    init {
        initServices()
    }

    private fun initServices() {
        this.services.addAll(ServicesListSingleton.services)
    }

    fun getServices() : ArrayList<Service> {
        return services
    }

    fun addNewService(position: Int, newService: Service) {
        services.add(position, newService)
    }
}