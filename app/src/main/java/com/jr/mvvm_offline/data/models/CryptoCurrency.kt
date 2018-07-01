package com.jr.mvvm_offline.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.squareup.moshi.Json
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(
        tableName = "cryptocurrencies"
)
data class CryptoCurrency constructor(
        @Json(name = "id")
        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: String,

        @Json(name = "name")
        @ColumnInfo(name = "name")
        var name: String?,

        @Json(name = "symbol")
        @ColumnInfo(name = "symbol")
        var symbol: String?,

        @Json(name = "rank")
        @ColumnInfo(name = "rank")
        var rank: String?,

        @Json(name = "price_usd")
        @ColumnInfo(name = "price_usd")
        var priceUsd: String?,

        @Json(name = "price_btc")
        @ColumnInfo(name = "price_btc")
        var priceBtc: String?,

        @Json(name = "24h_volume_usd")
        @ColumnInfo(name = "24h_volume_usd")
        var jsonMember24hVolumeUsd: String,

        @Json(name = "market_cap_usd")
        @ColumnInfo(name = "market_cap_usd")
        var marketCapUsd: String?,

        @Json(name = "available_supply")
        @ColumnInfo(name = "available_supply")
        var availableSupply: String?,

        @Json(name = "total_supply")
        @ColumnInfo(name = "total_supply")
        var totalSupply: String?,

        @Json(name = "max_supply")
        @ColumnInfo(name = "max_supply")
        var maxSupply: String?,

        @Json(name = "percent_change_1h")
        @ColumnInfo(name = "percent_change_1h")
        var percentChange1h: String?,

        @Json(name = "percent_change_24h")
        @ColumnInfo(name = "percent_change_24h")
        var percentChange24h: String?,

        @Json(name = "percent_change_7d")
        @ColumnInfo(name = "percent_change_7d")
        var percentChange7d: String?,

        @Json(name = "last_updated")
        @ColumnInfo(name = "last_updated")
        var lastUpdated: Long?
) : Serializable