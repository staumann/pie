package com.home.tool.service

import com.home.tool.repository.ShopRepository
import com.home.tool.model.Shop
import org.springframework.stereotype.Service

interface ShopService {
    fun getAllShops(): Iterable<Shop>

    fun getShopById(id: String): Shop?

    fun storeShop(shop: Shop?)

    fun deleteShop(id: String)
}

@Service
class ShopServiceImpl(private val shopRepository: ShopRepository): ShopService {

    override fun getAllShops(): Iterable<Shop> = shopRepository.findAll()

    override fun getShopById(id: String): Shop? = shopRepository.findById(id).orElse(null)

    override fun storeShop(shop: Shop?) {
        shop?.let {
            it.id = if (it.id == "") getIdForString(it.name) else it.id
            shopRepository.save(it)
        }
    }

    override fun deleteShop(id: String) = shopRepository.deleteById(id)
}
