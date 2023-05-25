package cr.una.ac.control_arterial_sebas_edgar.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cr.una.ac.control_arterial_sebas_edgar.DAO.TomaArterialDAO
import cr.una.ac.control_arterial_sebas_edgar.entity.AuthInterceptor
import cr.una.ac.control_arterial_sebas_edgar.entity.TomarArterial
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TomaArterialViewModel: ViewModel(){
    private var _tomasArteriales: MutableLiveData<List<TomarArterial>> = MutableLiveData()
    var tomasArteriales: LiveData<List<TomarArterial>> = _tomasArteriales
    lateinit var  apiService : TomaArterialDAO


    suspend fun listTomaArterial() {
        intService()
        var lista = apiService.getItems()
        _tomasArteriales.postValue(lista.items)

    }

    suspend fun addTomaArterial(item: TomarArterial) {
        intService()
        Log.d("TomaArterialViewModel", "addTomaArterial: " + item.toString())
        var items = ArrayList<TomarArterial>()
        items.add(item)
        apiService.createItems(items)
    }

    suspend fun deleteTomaArterial(item: TomarArterial) {
        intService()
        Log.d("TomaArterialViewModel", "deleteTomaArterial: " + item.toString())
        item._uuid?.let { apiService.deleteItem(it) }
        listTomaArterial()
    }


    fun intService(){
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor("tYKyh4u7PXFwVnFl_VwNn_puT5fnq5cA4jtM3vcc9xO9zbN-bQ"))
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://crudapi.co.uk/api/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(TomaArterialDAO::class.java)
    }
}