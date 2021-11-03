package br.com.brunoccbertolini.ecommerce_whitelabel.ui.products.addproduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.brunoccbertolini.ecommerce_whitelabel.databinding.AddProductFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddProductFragment: BottomSheetDialogFragment() {

    private var _binding: AddProductFragmentBinding? = null
    private val binding: AddProductFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddProductFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }
}