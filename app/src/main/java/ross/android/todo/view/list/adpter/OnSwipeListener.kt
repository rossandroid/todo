package ross.android.todo.view.list.adpter

import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.card_adapter.view.*

class OnSwipeListener: View.OnTouchListener{
    enum class SWIPE { INIT, DOWN, MOVE, UP }
    var dX:Float=0F
    var pX:Float=0F
    var xStep=300F
    var xSlide=-650F
    var status: SWIPE = SWIPE.INIT
    var duration:Long=500L

    override fun onTouch(v: View, m: MotionEvent): Boolean {

        if(m.action== MotionEvent.ACTION_DOWN && status== SWIPE.INIT){
            dX = v.getX() - m.getRawX()
            pX = m.getRawX()
            status= SWIPE.DOWN
        }else
            if(m.action== MotionEvent.ACTION_MOVE && (status== SWIPE.DOWN || status== SWIPE.MOVE)){
                if((pX-m.getRawX())>0){
                    status= SWIPE.MOVE
                    v.remove_button.visibility=View.GONE
                    v.update_button.visibility=View.GONE
                    v.materialCardView.animate()
                        .x(m.getRawX() + dX)
                        .setDuration(0)
                        .start();
                }
            }
        else
        if((m.action== MotionEvent.ACTION_UP || m.action==MotionEvent.ACTION_CANCEL) && status== SWIPE.MOVE){
            if((pX-m.getRawX())>xStep) {
                v.materialCardView.animate()
                    .x(xSlide)
                    .setDuration(0)
                    .start();
                status= SWIPE.UP
                v.remove_button.visibility=View.VISIBLE
                v.update_button.visibility=View.VISIBLE
            }else {
                status= SWIPE.INIT
                v.remove_button.visibility=View.GONE
                v.update_button.visibility=View.GONE
                v.materialCardView.animate()
                    .x(0F)
                    .setDuration(duration)
                    .start();
            }
        }else
            if(m.action== MotionEvent.ACTION_DOWN && status== SWIPE.UP){
                status= SWIPE.INIT
                v.remove_button.visibility=View.GONE
                v.update_button.visibility=View.GONE
                v.materialCardView.animate()
                    .x(0F)
                    .setDuration(duration)
                    .start();
            }
        return true
    }

}