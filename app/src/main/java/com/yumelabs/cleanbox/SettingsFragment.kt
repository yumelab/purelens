package com.yumelabs.cleanbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yumelabs.cleanbox.common.ViewBindingHolder
import com.yumelabs.cleanbox.common.ViewBindingHolderImpl
import com.yumelabs.cleanbox.common.logd
import com.yumelabs.cleanbox.databinding.FragmentSettingsListDialogBinding

// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

class SettingsFragment : BottomSheetDialogFragment(), ViewBindingHolder<FragmentSettingsListDialogBinding> by ViewBindingHolderImpl() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = initBinding(FragmentSettingsListDialogBinding.inflate(layoutInflater), this) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.sortingOrder?.setOnCheckedChangeListener{ group, checkedId ->

            when(checkedId){
                R.id.sort_name -> "By Name".logd()
                R.id.sort_date -> "By Date".logd()
                R.id.sort_size -> "By Size".logd()
                else -> "first".logd()
            }
        }
    }

    companion object {
        fun newInstance(itemCount: Int): SettingsFragment =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ITEM_COUNT, itemCount)
                }
            }

    }

}