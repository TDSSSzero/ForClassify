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
import android.widget.ArrayAdapter

class FragmentAppList : Fragment() {

    private var _binding: FragmentApplistBinding? = null

    private val binding get() = _binding!!
    private val numberList = (1..100).map { "数字 $it" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApplistBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setuplistview()
    }
    private fun setuplistview(){
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            numberList
        )
        binding.listView.adapter = adapter
    }
}