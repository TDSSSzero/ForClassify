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
import android.widget.ListAdapter
import tds.analytics.classify.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = app(1000)
        binding.recyclerView.apply {
            layoutManager = layoutManager(requireContext())
            adapter = ListAdapter(items)
        }
    }
    private fun app
}