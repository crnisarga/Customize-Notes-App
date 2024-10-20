package com.example.notesapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.activities.MainActivity
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.databinding.FragmentSaveOrDeleteBinding
import com.example.notesapp.model.Note
import com.example.notesapp.utils.hideKeyboard
import com.example.notesapp.viewModel.NoteActivityViewModel
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [saveOrDeleteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class saveOrDeleteFragment : Fragment(R.layout.fragment_save_or_delete) {

    private lateinit var navController: NavController
    private lateinit var contentBinding: FragmentSaveOrDeleteBinding
    private var note: Note?= null
    private var color = -1
    private val noteActivityViewModel: NoteActivityViewModel by activityViewModels()
    private var currentData = SimpleDateFormat.getInstance().format(Date())
    private val job = CoroutineScope(Dispatchers.Main)
    private val args: saveOrDeleteFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = MaterialContainerTransform().apply {
            drawingViewId=R.id.fragment
            scrimColor = Color.TRANSPARENT
            duration = 300L
        }
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentBinding = FragmentSaveOrDeleteBinding.bind(view)

        navController = Navigation.findNavController(view)
        val activity = activity as MainActivity

        contentBinding.backBtn.setOnClickListener{
            requireView().hideKeyboard()
            navController.popBackStack()
        }

        contentBinding.saveNote.setOnClickListener{
            saveNote()
        }

        try{
            contentBinding.etNoteContent.setOnFocusChangeListener{_,hasFocus ->
                if(hasFocus) {
                    contentBinding.bootomBar.visibility = View.VISIBLE
                    contentBinding.etNoteContent.setStylesBar(contentBinding.styleBar)
                }
                else contentBinding.bootomBar.visibility = View.GONE
            }
        }catch (e : Throwable) {
            Log.d("tag",e.stackTrace.toString());
        }
    }

    private fun saveNote() {
        TODO("Not yet implemented the new accounttested")
    }
}