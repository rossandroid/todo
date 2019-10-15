package ross.android.todo.repository.net

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*
import ross.android.todo.repository.model.Todo

interface INet {
    @Headers("Accept:application/json")
    @GET("/todos")
    fun read(): Observable<List<Todo>>

    @Headers("Content-Type:application/json;charset=utf-8","Accept:application/json")
    @POST("/todos")
    fun write(@Body todos: Todo): Observable<Todo>

    @Headers("Accept:application/json")
    @DELETE("/todos/{id}")
    fun delete(@Path("id")id:Int): Observable<Response<Void>>

    @Headers("Content-Type:application/json;charset=utf-8","Accept:application/json")
    @PATCH("/todos/{id}")
    fun update(@Path("id")id:Int,@Body todos: Todo): Observable<Todo>

}