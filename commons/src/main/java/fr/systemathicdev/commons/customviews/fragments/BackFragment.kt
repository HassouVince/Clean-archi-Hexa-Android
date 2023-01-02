package fr.systemathicdev.commons.customviews.fragments

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BackFragment : Fragment() {

    lateinit var onBackPressed: OnBackPressedCallback

    open fun creatOnBackPressed(): OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onBackPressed = creatOnBackPressed()
        requireActivity().onBackPressedDispatcher.addCallback(this,onBackPressed)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.explode)
    }
}