package unifedideas.ama.gamekotlin.util.eventBus;

public enum AppAction {


    ACTION_GAME_ITEM("ACTION_GAME_ITEM"),
    ACTION_SHOW_ALL_GAMES("ACTION_SHOW_ALL_GAMES"),
    ACTION_SHOW_LAST_GAME_DATE("ACTION_SHOW_LAST_GAME_DATE"),
    ACTION_CHANGE_PASSWORD("ACTION_CHANGE_PASSWORD"),
    ACTION_CLEAR_GAME_HISTORY("ACTION_CLEAR_GAME_HISTORY"),
    ACTION_CHECK_NUMBER_GAME("ACTION_CHECK_NUMBER_GAME"),
    ACTION_NEW_GAME("ACTION_NEW_GAME"),
    ACTION_MAIN("ACTION_MAIN"),
    ACTION_SHOW_DATE_PICKER("ACTION_SHOW_DATE_PICKER"),
    ACTION_SAVE("ACTION_SAVE"),
    ACTION_LOGIN("ACTION_LOGIN"),
    ACTION_REGISTER("ACTION_REGISTER"),

    ;


    private final String action;

    AppAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
