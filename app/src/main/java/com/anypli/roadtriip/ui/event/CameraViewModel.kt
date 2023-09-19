import android.net.Uri
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.R
import com.anypli.roadtriip.data.retrofit.ApiClient
import com.anypli.roadtriip.di.RepositoriesUtility
import com.anypli.roadtriip.global.helper.TypeMessage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CameraViewModel : BaseViewModel() {
    private val eventRepository = RepositoriesUtility.getEventRepository()
    private val apiService = createApiClient()

    private var selectedImageUri: Uri? = null

    fun setSelectedImageUri(uri: Uri?) {
        selectedImageUri = uri
    }

    fun getSelectedImageUri(): Uri? {
        return selectedImageUri
    }

    fun onNextClicked() {
        showChoseDialog(
            title = TypeMessage.ResourceMessage(R.string.camera_dialog_title),
            message = TypeMessage.ResourceMessage(R.string.camera_dialog_message),
            ok = TypeMessage.ResourceMessage(R.string.suivant),
            cancel = TypeMessage.ResourceMessage(R.string.annuler),
            //okActionBlock = {navigate()},
            cancelActionBlock = {}
        )
    }

//    fun createEventWithImage(
//        eventBody: EventBody ,
//        imagePart: MultipartBody.Part ,
//        onSuccess: () -> Unit ,
//        onFailure: () -> Unit
//    ) {
//        viewModelScope.launch {
//            val response = apiService.createEvent(eventBody)
//            if (response.isSuccessful) {
//                val eventId = response.body()?.eventId
//                if (eventId != null) {
//                    val imageResponse = apiService.uploadEventImage(eventId, imagePart)
//                    if (imageResponse.isSuccessful) {
//                        onSuccess()
//                    } else {
//                        onFailure()
//                    }
//                }
//            } else {
//                onFailure()
//            }
//        }
//    }

    private fun createApiClient(): ApiClient {
        return Retrofit.Builder()
            .baseUrl("http://192.168.30.1:8009")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }
}
