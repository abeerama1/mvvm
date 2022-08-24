package unifedideas.ama.gamekotlin.features.allGames.adapter

import androidx.lifecycle.MutableLiveData
import org.greenrobot.eventbus.EventBus
import unifedideas.ama.gamekotlin.base.BaseViewModel
import unifedideas.ama.gamekotlin.util.UserDataUtil
import unifedideas.ama.gamekotlin.util.eventBus.AppAction
import unifedideas.ama.kotlinapp.util.eventBus.ActionEvent

class GameItemViewModel(private val game: Game) : BaseViewModel() {

    val id: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = game.id }
    val userId: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = game.userId }
    val score: MutableLiveData<Int> = MutableLiveData<Int>().apply { value = game.score }
    val date: MutableLiveData<String> = MutableLiveData<String>().apply { value = game.date }
    val time: MutableLiveData<String> = MutableLiveData<String>().apply { value = game.time }
    val name: MutableLiveData<String> = MutableLiveData<String>().apply { value = UserDataUtil.getFullName() }

    fun onClickGameItem() {
        EventBus.getDefault().post(ActionEvent(AppAction.ACTION_GAME_ITEM))
    }


}