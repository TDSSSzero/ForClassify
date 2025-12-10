package tds.analytics.classify.ui.home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.graphics.Color
import androidx.fragment.app.Fragment
import tds.analytics.classify.databinding.FragmentGridviewBinding
import tds.analytics.classify.R

class FragmentGridview : Fragment() {

    private var _binding: FragmentGridviewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGridviewBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    private val imageResources = listOf(
        R.drawable.grid_1,
        R.drawable.grid_2,
        R.drawable.grid_3,
        R.drawable.grid_4,
        R.drawable.grid_5,
        R.drawable.grid_6,
        R.drawable.grid_7,
        R.drawable.grid_8,
        R.drawable.grid_9
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGridView()
        binding.gridview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            showImageFullScreen(imageResources[position])
        }
    }
    private fun setupGridView(){
        binding.gridview.adapter = BaseGridAdapter(requireContext(), imageResources)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private class BaseGridAdapter(
        private val context: Context,
        private val images: List<Int>
    ) : BaseAdapter() {
        override fun getCount(): Int = images.size
        override fun getItem(position: Int): Any = images[position]
        override fun getItemId(position: Int): Long = position.toLong()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_grid_image, parent, false)
            val imageView = view.findViewById<ImageView>(R.id.image)
            imageView.setImageResource(images[position])
            return view
        }
    }
    private fun showImageFullScreen(imageRes: Int) {
        val dialog = Dialog(requireContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen)
        val imageView = ImageView(requireContext()).apply {
            setImageResource(imageRes)
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            scaleType = ImageView.ScaleType.FIT_CENTER
            setBackgroundColor(Color.BLACK)
            setOnClickListener { dialog.dismiss() }
        }
        dialog.setContentView(imageView)
        dialog.show()
    }
}
