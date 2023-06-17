package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00
private const val PRICE_PER_CUPCAKE = 2.00
class OrderViewModel: ViewModel() {
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int>
        get() = _quantity
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String>
        get() = _flavor
    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String>
        get() =  Transformations.map(_price){
            NumberFormat.getCurrencyInstance().format(it)
        }
    val dateOptions: List<String> = getPickUpOptions()
    fun setQuantity(numberCupcakes: Int){
        _quantity.value = numberCupcakes
        updatePrice()
    }
    fun setFlavor(desiredFlavor: String){
        _flavor.value = desiredFlavor
    }
    fun setDate(pickUpDate: String){
        _date.value = pickUpDate
        updatePrice()
    }
    fun hasNoFlavorSet(): Boolean{
        return  _flavor.value.isNullOrEmpty()
    }
    init {
        resetOrder()
    }
    fun resetOrder(){
        _quantity.value = 0
        _price.value = 0.0
        _flavor.value = ""
        _date.value = dateOptions[0]
    }
    private fun getPickUpOptions():List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calender = Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calender.time))
            calender.add(Calendar.DATE, 1)
        }
        return  options
    }
    private fun updatePrice(){
        var calculatedPrice = (_quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if(dateOptions[0] == _date.value){
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }
}