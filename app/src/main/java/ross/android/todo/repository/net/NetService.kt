package ross.android.todo.repository.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetService {

    private val base_url = "https://bboxx-android-coding-challenge.herokuapp.com"

    private val rf = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(base_url)
        .build()

    var todoApi = rf.create(INet::class.java)
}