import com.anypli.roadtriip.data.retrofit.ApiClient
import com.anypli.roadtriip.di.NetworkUtility
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


const val SERVER_URL  = "http://192.168.30.1:8009"
const val  BASE_URL = "$SERVER_URL/api/"

object ApiClientUtility {

        private var instance : ApiClient? = null
        @Synchronized
        fun getInstance(): ApiClient {
            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)// BASE_URL
                    .client(NetworkUtility.getClient())
                    .build()
                    .create(ApiClient::class.java)
            return instance !!
        }

}




