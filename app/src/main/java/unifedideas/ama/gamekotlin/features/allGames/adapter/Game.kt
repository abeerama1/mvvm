package unifedideas.ama.gamekotlin.features.allGames.adapter

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games_table")
class Game() {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    @ColumnInfo(name = "user_id") var userId: Int = 0
    @ColumnInfo(name = "score") var score: Int = 0
    @ColumnInfo(name = "date") var date: String = ""
    @ColumnInfo(name = "time") var time: String = ""



//    constructor(id: Int, userId: Int, score: Int, date: String, time: String) : this() {
//        this.id = id
//        this.userId = userId
//        this.score = score
//        this.date = date
//        this.time = time
//    }

}


