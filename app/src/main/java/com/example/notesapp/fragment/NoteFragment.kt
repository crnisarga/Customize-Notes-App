package com.example.notesapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.activities.MainActivity
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.utils.hideKeyboard
import com.example.notesapp.viewModel.NoteActivityViewModel
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteFragment : Fragment(R.layout.fragment_note) {

    private lateinit var noteBinding: FragmentNoteBinding
    private val noteActivityViewModel : NoteActivityViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false).apply {
            duration = 350
        }
        enterTransition = MaterialElevationScale(true).apply {
            duration= 350
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteBinding = FragmentNoteBinding.bind(view)
        val activity = activity as MainActivity
        val navController = Navigation.findNavController(view)
        requireView().hideKeyboard()
        CoroutineScope(Dispatchers.Main).launch {
            delay(10)
            activity.window.statusBarColor = Color.WHITE
            activity.window.clearFlags((WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS))
            activity.window.statusBarColor = Color.parseColor("#9E9D9D")
        }

        noteBinding.addNoteFab.setOnClickListener{
            noteBinding.appBarLayout.visibility = View.INVISIBLE
            navController.navigate(NoteFragmentDirections.actionNoteFragmentToSaveOrDeleteFragment())
        }

        noteBinding.innerFab.setOnClickListener{
            noteBinding.appBarLayout.visibility = View.INVISIBLE
            navController.navigate(NoteFragmentDirections.actionNoteFragmentToSaveOrDeleteFragment())
        }

    }
}
