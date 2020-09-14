package com.seiyria.reuuzei.presentation.pro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.seiyria.reuuzei.PRO_PACKAGE_NAME
import com.seiyria.reuuzei.R
import com.seiyria.reuuzei.util.openInPlayStore
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_pro.*

/**
 * Created by Alireza Afkar on 5/28/20.
 */
class ProDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pro, container, false)
    }

    companion object {
        fun show(fragmentManager: FragmentManager) {
            ProDialog().show(fragmentManager, "")
        }
    }
}
