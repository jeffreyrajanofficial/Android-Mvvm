package com.jr.mvvm_offline.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jr.mvvm_offline.R
import com.jr.mvvm_offline.data.models.CryptoCurrency

class CryptoCurrenciesAdapter(private val cryptoCurrencies: List<CryptoCurrency>): RecyclerView.Adapter<CryptoCurrenciesAdapter.CryptoCurrencyViewHolder>() {

    private var cryptoCurrencyList = ArrayList<CryptoCurrency>()

    init {
        this.cryptoCurrencyList = cryptoCurrencies as ArrayList<CryptoCurrency>
    }

    override fun getItemCount(): Int = cryptoCurrencyList.size

    override fun onBindViewHolder(p0: CryptoCurrencyViewHolder, p1: Int) {
        p0.cryptoCurrencyListItems(cryptoCurrencyList.get(p1))
    }

    fun addCryptoCurrencies(cryptoCurrencies: List<CryptoCurrency>) {
        val currentPosition = cryptoCurrencyList.size
        cryptoCurrencyList.addAll(cryptoCurrencies)
        notifyItemRangeChanged(currentPosition, cryptoCurrencyList.size)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CryptoCurrencyViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.crypto_currency_list_item, p0, false)
        return CryptoCurrencyViewHolder(itemView)
    }

    class CryptoCurrencyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val currencyId = itemView.findViewById<TextView>(R.id.cryptocurrency_id)

        fun cryptoCurrencyListItems (cryptoCurrency: CryptoCurrency) {
            currencyId.text = cryptoCurrency.id
        }
    }
}