package com.nbs.latestnews.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nbs.latestnews.data.util.Utils

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(Utils.logTag, "onCreate of $this")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(Utils.logTag, "onCreateView of $this")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(Utils.logTag, "onViewCreated of $this")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(Utils.logTag, "onDestroyView of $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(Utils.logTag, "onDestroy of $this")
    }
}