package com.nbs.latestnews.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nbs.latestnews.R
import com.nbs.latestnews.data.util.Utils
import com.nbs.latestnews.databinding.FragmentSavedNewsBinding
import com.nbs.latestnews.presentation.adapter.NewsAdapter
import com.nbs.latestnews.presentation.viewmodel.NewsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SavedNewsFragment : BaseFragment() {

    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSavedNewsBinding.bind(view)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        initRecyclerView()

        newsViewModel.getSavedNewsArticles().observe(viewLifecycleOwner, Observer {
            newsAdapter.differ.submitList(it)
        })
    }

    private fun initRecyclerView() {

        binding.recyclerView.apply {

            layoutManager = LinearLayoutManager(activity)

            adapter = newsAdapter
            newsAdapter.setOnItemClick {
                val bundle = Bundle().apply {
                    putSerializable(Utils.keyArticle, it)
                }
                val navController = findNavController()
                navController.navigate(
                    R.id.action_savedNewsFragment_to_newsDetailsFragment, bundle
                )
            }
        }

        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.recyclerView)
        }
    }

    private val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val article = newsAdapter.differ.currentList[position]

            newsViewModel.deleteSavedNewsArticle(article)
            Snackbar.make(view!!, "Deleted Successfully.", Snackbar.LENGTH_SHORT).apply {
                setAction("Undo") {
                    newsViewModel.saveArticle(article)
                }
            }.show()
        }

    }
}