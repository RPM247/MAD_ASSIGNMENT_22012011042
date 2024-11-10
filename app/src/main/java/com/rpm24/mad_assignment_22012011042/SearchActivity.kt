package com.rpm24.mad_assignment_22012011042

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.rpm24.mad_assignment_22012011042.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdapter: SearchAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var recipes:  List<Recipe?>
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.search.requestFocus()
        var db= Room.databaseBuilder(this@SearchActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        recipes = daoObject.getAll()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpRecyclerView()
        binding.goBackHome.setOnClickListener {
            finish()
        }
        binding.search.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString()!=""){
                    filterData(p0.toString())
                }else{
                    setUpRecyclerView()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun filterData(filterText: String) {
        var filterData=ArrayList<Recipe>()
        for(i in recipes.indices){
            if(recipes[i]!!.tittle.lowercase().contains(filterText.lowercase())){
                filterData.add(recipes[i]!!)
            }
            rvAdapter.filterList(filterList = filterData)
        }
    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()
        binding.rvSearch.layoutManager= LinearLayoutManager(this)


        for(i in recipes!!.indices){
            if(recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
            rvAdapter=SearchAdapter(dataList, this)
            binding.rvSearch.adapter=rvAdapter
        }
    }
}