package ross.android.todo.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Todo (
    var id : Int? = null,
    var created_at : String? = null,
    var updated_at : String? = null,

    var name : String,
    var status : String,
    var description: String,
    var expiry_date : String
){
    @PrimaryKey(autoGenerate = true)
    var autoId: Int=0
}
