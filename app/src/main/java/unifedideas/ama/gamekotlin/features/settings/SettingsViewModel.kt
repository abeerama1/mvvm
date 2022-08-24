package unifedideas.ama.gamekotlin.features.settings

import unifedideas.ama.gamekotlin.base.BaseViewModel
import unifedideas.ama.gamekotlin.util.eventBus.AppAction


class SettingsViewModel : BaseViewModel() {

    fun onClickShowAllGames() {
        doAction.value = AppAction.ACTION_SHOW_ALL_GAMES
    }

    fun onClickShowLaseGameDate() {
        doAction.value = AppAction.ACTION_SHOW_LAST_GAME_DATE
    }

    fun onClickChangePassword() {
        doAction.value = AppAction.ACTION_CHANGE_PASSWORD
    }

    fun onClickClearGameHistory() {
        doAction.value = AppAction.ACTION_CLEAR_GAME_HISTORY
    }
}