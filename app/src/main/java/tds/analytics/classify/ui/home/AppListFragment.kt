package tds.analytics.classify.ui.home

import tds.analytics.classify.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tds.analytics.classify.databinding.FragmentApplistBinding
import tds.analytics.classify.databinding.FragmentHomeBinding

class FragmentAppList : Fragment() {

    private var _binding: FragmentApplistBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApplistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }
}