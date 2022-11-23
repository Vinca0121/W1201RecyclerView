package kr.ac.kumoh.s20180287.prof.w1201recyclerview


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    private  val songs = ArrayList<String>()
    private val _list = MutableLiveData<ArrayList<String>>()
    val list : LiveData<ArrayList<String>>
        get() = _list

    init {
        _list.value = songs
    }

    fun add(song : String)
    {
        songs.add(song)
        _list.value = songs
    }

//    fun getList() : MutableLiveData<ArrayList<String>> = _list
//    fun getSong(i : Int) = songs[i]
//    fun getSize() = songs.size

}