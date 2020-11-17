package com.nabil.notes.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nabil.notes.R
import com.nabil.notes.databinding.FragmentUpdateBinding
import com.nabil.notes.model.ToDoData
import com.nabil.notes.viemodel.SharedViewModel
import com.nabil.notes.viemodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_add.*


class UpdateFragment : Fragment() {

    private val args: UpdateFragmentArgs by navArgs<UpdateFragmentArgs>()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.args = args
        binding.spinnerPrioritiesUpdate.onItemSelectedListener = mSharedViewModel.listener
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmRemovalItem()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmRemovalItem() {
        AlertDialog.Builder(requireContext())
                .setTitle("Delete '${args.currentItem.title}'?")
                .setMessage("Asli nyesel dah kalo dihapus '${args.currentItem.title}'")
                .setPositiveButton("Nyesel"){_ , _  ->
                    mToDoViewModel.deleteData(args.currentItem)
                    Toast.makeText(requireContext(), "Mampus Nyesel lu", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                }

                .setNegativeButton("Khilaf") {_ , _ -> }
                .create()
                .show()

    }

    private fun updateItem() {
        val mTitle = edt_title.text.toString()
        val mPriority = spinner_priorities.selectedItem.toString()
        val mDescription = edt_description.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (validation) {
            val newData = ToDoData(
                    args.currentItem.id,
                    mTitle,
                    mSharedViewModel.parsePriority(mPriority),
                    mDescription
            )
            mToDoViewModel.updateData(newData)
            Toast.makeText(requireContext(),"Data Berhasil DiUpdate", Toast.LENGTH_SHORT).show()
            findNavController(). navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}