package ross.android.todo.repository.db

import androidx.room.*
import ross.android.todo.repository.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTodo(todos: List<Todo>)

    @Insert
    fun insertOne(todo: Todo)

    @Query("DELETE FROM Todo Where id=:id")
    fun delete (id:Int)

    @Query("DELETE FROM Todo")
    fun deleteAll()

    @Query("SELECT * FROM Todo Where id=:id")
    fun get (id:Int):Todo

    @Update
    fun updateOne(todo: Todo)

    @Query("SELECT * FROM Todo Where id IS NULL")
    fun getPendingNewTodo(): List<Todo>

    @Query("SELECT * FROM Todo LIMIT :limit")
    fun getWithLimit(limit:Int): List<Todo>

    @Query("SELECT * FROM Todo Where updated_at IS NULL AND id IS NOT NULL")
    fun getPendingUpdatedTodo(): List<Todo>

}
