
package unifedideas.ama.gamekotlin.base
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import unifedideas.ama.gamekotlin.util.SingleLiveEvent
import unifedideas.ama.gamekotlin.util.eventBus.AppAction

open class BaseViewModel : ViewModel() {

    var showMessage: SingleLiveEvent<String> = SingleLiveEvent()
    var showMessageRes: SingleLiveEvent<Int> = SingleLiveEvent()
    var doAction: SingleLiveEvent<AppAction> = SingleLiveEvent()
    var isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    var isRefreshing = MutableLiveData<Boolean>().apply { value = false}


    var emptyIcon = MutableLiveData<Int>(0)
    var emptyText = MutableLiveData<Int>()
    var isEmpty = MutableLiveData<Boolean>()


}