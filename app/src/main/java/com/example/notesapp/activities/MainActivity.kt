package com.example.notesapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.db.NoteDatabase
import com.example.notesapp.repository.NoteRepository
import com.example.notesapp.viewModel.NoteActivityViewModel
import com.example.notesapp.viewModel.NoteActivityViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteActivityViewModel: NoteActivityViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)


        try {
            setContentView(binding.root)
            val noteRepository = NoteRepository(NoteDatabase(this))
            val noteActivityViewModelFactory = NoteActivityViewModelFactory(noteRepository)
            noteActivityViewModel = ViewModelProvider(this,noteActivityViewModelFactory)[NoteActivityViewModel :: class.java]
        }
        catch (e : Exception) {
            Log.d("Tag", "Error")
        }
    }
}