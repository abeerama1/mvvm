package unifedideas.ama.gamekotlin.features.register

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")

class User() {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    @ColumnInfo(name = "user_name")
    var userName: String? = null
    @ColumnInfo(name = "email")
    var email: String? = null
    @ColumnInfo(name = "password")
    var password: String? = null
    @ColumnInfo(name = "full_name")
    var fullName: String? = null
    @ColumnInfo(name = "birth_date")
    var birthDate: String? = null
    @ColumnInfo(name = "country")
    var country: String? = null
    @ColumnInfo(name = "gender")
    var gender: String? = null
    @ColumnInfo(name = "image")
    var image: String? = null


    constructor(
        id: Int?,
        userName: String?,
        email: String?,
        password: String?,
        fullName: String?,
        birthDate: String?,
        country: String?,
        gender: String?,
        image: String?
    ) : this() {
        this.id = id
        this.userName = userName
        this.email = email
        this.password = password
        this.fullName = fullName
        this.birthDate = birthDate
        this.country = country
        this.gender = gender
        this.image = image
    }

}