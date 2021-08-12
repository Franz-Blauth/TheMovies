package com.example.mymovies.ui.components

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovies.constants.MoviesConstants
import com.example.mymovies.data.local.SharedPreferencesRepository
import com.example.mymovies.data.model.AccountDetailsModel
import com.example.mymovies.data.network.RequestDetailsAccount
import com.example.mymovies.service.retrofit.APIReturn

class GetDetails  (val application: Application) {

    private val getDetailsAccount = RequestDetailsAccount(application)
    private val sharedPreferences = SharedPreferencesRepository(application)

    private val mGetDetails = MutableLiveData<AccountDetailsModel>()
    var getDetails : LiveData<AccountDetailsModel> = mGetDetails

    fun getDetails() {
        getDetailsAccount.getDetailsAccount(MoviesConstants.QUERY.API_KEY,
            object: APIReturn<AccountDetailsModel> {

                override fun onSuccess(model: AccountDetailsModel) {
                    saveSessionAccountId(model.id.toString())
                    mGetDetails.value = model
                }
                override fun onFailure(str: String) {
                }
            })
    }

    fun saveSessionAccountId(accountId : String){
        sharedPreferences.saveShared(MoviesConstants.SHARED.ACCOUNT_ID, accountId)
    }

}