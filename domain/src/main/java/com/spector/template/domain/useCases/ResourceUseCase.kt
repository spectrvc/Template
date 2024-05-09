package com.spector.template.domain.useCases

import com.spector.template.domain.repository.DeviceRepository

class ResourceUseCase(private val deviceRepository: DeviceRepository) {

    fun getResourceString(resourceId: Int): String{
        return deviceRepository.getResourceString(resourceId)
    }

}