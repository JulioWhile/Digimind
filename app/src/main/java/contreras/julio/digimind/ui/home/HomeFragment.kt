package contreras.julio.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import contreras.julio.digimind.R
import contreras.julio.digimind.Task
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.task_view.view.*

class HomeFragment : Fragment() {
    private var adaptador: AdaptadorTareas? = null
    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if (first){
            fillTasks()
            first = false
        }

        adaptador = AdaptadorTareas(root.context, tasks)
        root.gridViewHome.adapter = adaptador
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }

    fun fillTasks() {
        tasks.add(Task("Practica 1", arrayListOf("Tuesday"), "17:30"))
        tasks.add(Task("Practica 2", arrayListOf("Monday, Sunday"), "17:30"))
        tasks.add(Task("Practica 3", arrayListOf("Wednesday"), "11:00"))
        tasks.add(Task("Practica 4", arrayListOf("Friday"), "13:30"))
        tasks.add(Task("Practica 5", arrayListOf("Thursday"), "10:40"))
    }

    private class AdaptadorTareas: BaseAdapter {
        var tasks = ArrayList<Task>()
        var contexto: Context? = null

        constructor(contexto: Context, tasks: ArrayList<Task>) {
            this.contexto = contexto
            this.tasks = tasks
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var task = tasks[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.task_view, null)

            vista.tv_title.setText(task.title)
            vista.tv_days.setText(task.days.toString())
            vista.tv_time.setText(task.time)

            return vista
        }

        override fun getItem(p0: Int): Any {
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }

    }



}





