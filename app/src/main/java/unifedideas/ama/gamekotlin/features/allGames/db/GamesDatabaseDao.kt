package unifedideas.ama.gamekotlin.features.allGames.db

import androidx.lifecycle.LiveData
import androidx.room.*
import unifedideas.ama.gamekotlin.features.allGames.adapter.Game
import unifedideas.ama.gamekotlin.features.register.User
import java.util.*


@Dao
interface GamesDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewGame(game: Game)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User)

    @Delete
    fun delete(game: Game)

    @Query("SELECT score FROM games_table WHERE user_id = :id")
    fun getUserTotalScore(id: Int): Int

    @Query("SELECT * FROM games_table WHERE user_id = :userId")
    fun getAllGames(userId: Int): List<Game>

    @Query("SELECT date FROM games_table WHERE id = :id ORDER BY date DESC LIMIT 1")
    fun getLastDate(id: Int) : String


    @Query("delete from games_table")
    fun clear()

    @Query("SELECT * from games_table order by date desc")
    fun getAllGames(): LiveData<List<Game>>
}