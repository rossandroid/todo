package ross.android.todo.view.addupdate

import android.content.Context

interface IAddEditActivity {
    fun getContext(): Context
    fun updateName(name:String)
    fun saveDone()
    fun updateStatus(status:String)
    fun updateDescription(description:String)
    fun updateExpiry(expiry_date:String)
    fun saveDoneWithError(error:String)
}