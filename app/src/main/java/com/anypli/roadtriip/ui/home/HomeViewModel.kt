import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anypli.roadtriip.base.BaseViewModel
import com.anypli.roadtriip.data.model.Event
import com.anypli.roadtriip.data.model.EventList
import com.anypli.roadtriip.di.RepositoriesUtility
import com.anypli.roadtriip.global.helper.Navigation
import com.anypli.roadtriip.ui.home.HomeGridView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val eventRepository = RepositoriesUtility.getEventRepository()

    private var currentItem= -1

    // Use StateFlow instead of LiveData
    private val _gridViewItems = MutableStateFlow<List<HomeGridView>>(emptyList())
    val gridViewItems: StateFlow<List<HomeGridView>> = _gridViewItems

    init {
        fetchEvents()
    }
    private fun fetchEvents() {
        viewModelScope.launch {
            try {
                val eventList = eventRepository.showEvents()
                val homeGridViews = convertToHomeGridViews(eventList)
                _gridViewItems.value = homeGridViews
            } catch (e: Exception) {
                hideBlockProgressBar()
                handleGlobalError(e)
            }
        }
    }
    private fun convertToHomeGridViews(eventList: List<Event>): List<HomeGridView> {
        return eventList.map { event ->
            HomeGridView(
                event.nameDestination ?: "",
                event.prix ?: "",
                event.eventPicture ?: "",
                event.description
            )
        }
    }
    fun onItemsClicked() {
        navigate(Navigation.DetailsEventsScreen)
    }
    fun onItemsProfile() {
        navigate(Navigation.ProfileScreen)
    }
    fun onBackClicked(){
        navigate(Navigation.Back)
    }
}
