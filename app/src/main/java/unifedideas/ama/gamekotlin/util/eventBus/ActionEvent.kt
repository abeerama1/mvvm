package unifedideas.ama.kotlinapp.util.eventBus

import unifedideas.ama.gamekotlin.util.eventBus.AppAction


class ActionEvent {
    var actions: AppAction? = null

    constructor(actions: AppAction?) {
        this.actions = actions
    }



}