package com.example.toDoList43017

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.toDoList43017.databinding.ActivityListBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_list.view.*


class ListActivity : AppCompatActivity() {

    private var lista: MutableList<ModeloTarefa> = mutableListOf()
    private var adapter = ListAdapter(lista)
    private var renamePos = 0 // Irá ser usado para guardar a posição que se quer alterar o nome
    private lateinit var viewModel: ListActivityViewModel
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFromBD()
        setup()

    }

    override fun onResume() {
        super.onResume()

        loadFromBD()
        adapter.notifyDataSetChanged()

    }

    override fun onPause() {
        super.onPause()

        addToDB()
    }

    fun setup() {

        viewModel = ViewModelProvider(this).get(ListActivityViewModel::class.java)

        rv_lista.adapter = adapter
        rv_lista.layoutManager = LinearLayoutManager(this)
        rv_lista.setHasFixedSize(false)

        // Slide para opções
        val itemTouchHelper = ItemTouchHelper(itemTouchHelpercallback)
        itemTouchHelper.attachToRecyclerView(rv_lista)

        // Se o enter for premido para adicionar uma nova tarefa

        binding.newTaskText.setOnEditorActionListener(OnEditorActionListener { v: View, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                addTask(v, lista.size)
                return@OnEditorActionListener true
            }
            false
        })

        // Se o enter for premido para mudar o nome de uma task
        binding.renameTaskText.setOnEditorActionListener(OnEditorActionListener { v: View, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                renameTask(v, renamePos)
                return@OnEditorActionListener true
            }
            false
        })
    }

    // Botão new foi premido, mostrar a caixa de texto

    fun mostrarNovoNome(itemView: View) {
        binding.newTaskText.visibility = View.VISIBLE
    }

    // Adicionar uma nova task
    fun addTask(itemView: View, pos: Int) {

        val textView = binding.newTaskText

        if (viewModel.isEmpty(textView)) {
            Toast.makeText(applicationContext, "Task must have a name", Toast.LENGTH_SHORT).show();
        } else {

            // Criar a nova instância
            val newTask = ModeloTarefa(false, textView.text.toString())

            // Adicionar a nova task à lista e notificar o adapter da nova inserção
            lista.add(pos, newTask)
            adapter.notifyItemInserted(pos)


            // Reset do ecrã
            viewModel.resetScreen(this, itemView, textView)

        }

        adapter.notifyDataSetChanged()
        Log.v(lista.size.toString(), "inserido")
    }

    // Remove a task
    fun deleteTask(pos: Int) {

        lista.removeAt(pos)
        adapter.notifyItemRemoved(pos)

        adapter.notifyDataSetChanged()
    }

    // Renomeia a task
    fun renameTask(view: View, pos: Int) {

        val textView = binding.renameTaskText

        if (viewModel.isEmpty(textView)) {

            Toast.makeText(applicationContext, "Task must have a name", Toast.LENGTH_SHORT).show();
        } else {

            lista[pos].texto = textView.text.toString()
            adapter.notifyItemChanged(pos)

            viewModel.resetScreen(this, view, textView)

        }
    }

    // Se foi feito um slide de alguma task
    val itemTouchHelpercallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: ViewHolder,
            target: ViewHolder
        ): Boolean {
            TODO("Not yet implemented")
        }

        // Quando é feito um swipe
        override fun onSwiped(viewHolder: ViewHolder, direction: Int) {

            // Guardar a posição da task que foi swiped
            val pos = viewHolder.absoluteAdapterPosition
            adapter.notifyItemChanged(pos)

            Log.v(pos.toString(), "POSIÇÂO")

            val popup = viewModel.showMenu(applicationContext, viewHolder.itemView)


            // Dependendo do menu ou Renomear ou Remover
            popup.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.RenameTask) {

                    binding.renameTaskText.visibility = View.VISIBLE

                    renamePos = pos
                    return@setOnMenuItemClickListener true
                } else {

                    deleteTask(pos)
                    return@setOnMenuItemClickListener true
                }
            }
        }
    }



    private fun loadFromBD() {

        /*val docs = Firebase.firestore.collection("todoList").orderBy("texto")

        docs.addSnapshotListener { snapshot, e ->
            lista = mutableListOf()

            for (document in snapshot!!.documents) {

                val cb = ModeloTarefa(
                    "${document.data?.get("checked")}".toBoolean(),
                    "${document.data?.get("texto").toString()}"
                )
                lista.add(cb)
            }

            binding.rvLista.adapter = ListAdapter(lista)
            adapter.notifyDataSetChanged()

        }*/
    }

    private fun addToDB() {

        /*val db = Firebase.firestore
        for (task in lista) {
            Log.v("adicionado à bd","adicionado à BD")
            val tasAAdd = hashMapOf(
                "checked" to task.checked,
                "texto" to task.texto
            )

            db.collection("todoList")
                .add(tasAAdd)
        }*/
    }

    private fun deleteFromBD(){
        val docs = Firebase.firestore.collection("todoList")
        docs.addSnapshotListener { snapshot, e ->
            for (document in snapshot!!.documents) {

                docs.document(document.id).delete()
                    .addOnSuccessListener {
                        Log.d("teste", "DocumentSnapshot successfully deleted!")
                    }

            }
        }

    }


}