package kr.ac.kumoh.s20180287.prof.w1201recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.s20180287.prof.w1201recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var model : ListViewModel
    private val mysongAdapter = SongAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this)[ListViewModel::class.java]

        binding.list.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = mysongAdapter
        }

        model.list.observe(this){
            // ? : 엘비스 연산자 : 마냥 0일 경우 어떻게 할 것인가?
            mysongAdapter.notifyItemRangeChanged(0,
                model.list.value?.size ?: 0)

        }

        for (i in 0 until 3) {
            model.add("진인사대천명")
        }
        model.add("하루가 달리")
    }


    inner class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>(){

        // 2
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            // ViewHolder에 text view를 가진 객체를 하나 생성한다.
            val txSong : TextView = itemView.findViewById(android.R.id.text1)
        }


        // 1
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        // 3
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.txSong.text = model.list.value?.get(position) ?: "노래 정보 없음"
        }

        override fun getItemCount() = model.list.value?.size ?: 0
    }

}
