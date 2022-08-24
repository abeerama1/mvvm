package unifedideas.ama.gamekotlin.base

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    open fun showToast(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    open fun showToast(msg: Int?) {
        Toast.makeText(requireActivity(), msg!!, Toast.LENGTH_SHORT).show()
    }

}