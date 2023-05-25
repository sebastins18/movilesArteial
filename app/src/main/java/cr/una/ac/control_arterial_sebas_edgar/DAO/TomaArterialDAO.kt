package cr.una.ac.control_arterial_sebas_edgar.DAO

import cr.una.ac.control_arterial_sebas_edgar.entity.TomarArterial
import cr.una.ac.control_arterial_sebas_edgar.entity.TomasArteriales
import retrofit2.http.*


interface TomaArterialDAO {

    @GET("TomasArteriales")
    suspend fun getItems(): TomasArteriales

    @GET("TomasArteriales/{uuid}")
    suspend fun getItem(@Path("uuid") uuid: String): TomarArterial

    @POST("TomasArteriales")
    suspend fun createItems( @Body items: List<TomarArterial>): TomasArteriales

    @POST("TomasArteriales")
    suspend fun createItem(@Body item: TomarArterial): TomarArterial

    @PUT("TomasArteriales/{uuid}")
    suspend fun updateItem(@Path("uuid") uuid: String, @Body item: TomarArterial): TomarArterial

    @DELETE("TomasArteriales/{uuid}")
    suspend fun deleteItem(@Path("uuid") uuid: String)
}