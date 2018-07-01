package com.jr.mvvm_offline.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.jr.mvvm_offline.R
import com.jr.mvvm_offline.data.models.CryptoCurrency
import com.jr.mvvm_offline.utils.Constants
import com.jr.mvvm_offline.utils.InfiniteScrollListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CryptoCurrenciesActivity : AppCompatActivity() {

    @Inject
    lateinit var cryptoCurrenciesViewModelFactory: CryptoCurrenciesViewModelFactory
    lateinit var cryptoCurrenciesViewModel: CryptoCurrenciesViewModel
    var cryptoCurrencyAdapter = CryptoCurrenciesAdapter(ArrayList())
    var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        initzializeRecycler()

        cryptoCurrenciesViewModel = ViewModelProviders.of(this, cryptoCurrenciesViewModelFactory)
                .get(CryptoCurrenciesViewModel::class.java)

        progressBar.visibility = View.VISIBLE
        loadData()

        cryptoCurrenciesViewModel.loadCurrencies(Constants.LIMIT, currentPage * Constants.OFFSET)

        cryptoCurrenciesViewModel.cryptoCurrenciesResult().observe(this, Observer<List<CryptoCurrency>> {
//            tv_hello_world.text = "Hello ${it?.size} crypto currencies"
            if(it != null) {
                val position = cryptoCurrencyAdapter.itemCount
                cryptoCurrencyAdapter.addCryptoCurrencies(it)
                recycler.adapter = cryptoCurrencyAdapter
                recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
            }
        })

        cryptoCurrenciesViewModel.cryptoCurrenciesError().observe(this,
                Observer<String> {
                    if(it != null) {
                        Toast.makeText(this, "Error in loading: "+it, Toast.LENGTH_SHORT).show()
                    }
                })

        cryptoCurrenciesViewModel.cryptoCurrencyLoader().observe(this, Observer<Boolean> {
            if(it!=null) progressBar.visibility = View.GONE
        })

    }

    private fun initzializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScrollListener({
                loadData()
            }, gridLayoutManager))
        }
    }

    private fun loadData() {
        cryptoCurrenciesViewModel.loadCurrencies(Constants.LIMIT, currentPage * Constants.OFFSET)
        currentPage++
    }


    override fun onDestroy() {
        cryptoCurrenciesViewModel.disposeElements()
        super.onDestroy()
    }
}

