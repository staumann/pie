package com.home.tool.service

import com.home.tool.repository.ShopRepository
import com.home.tool.model.Shop
import org.springframework.stereotype.Service

@Service
class ShopService(private val shopRepository: ShopRepository) {

    fun getAllShops(): Iterable<Shop> = shopRepository.findAll()

    fun getShopById(id: String): Shop? = shopRepository.findById(id).orElse(null)

    fun storeShop(shop: Shop?) {
        shop?.let {
            it.id = if (it.id == "") getIdForString(it.name) else it.id
            shopRepository.save(it)
        }
    }

    fun deleteShop(id: String) = shopRepository.deleteById(id)
}