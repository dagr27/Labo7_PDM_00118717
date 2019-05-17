package com.example.laboseven7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laboseven7.adapters.ReposAdapter
import com.example.laboseven7.database.entities.GithubRepo
import com.example.laboseven7.database.viewmodels.GithubRepoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: ReposAdapter
    lateinit var viewModel: GithubRepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()
    }

    private fun bind(){
        adapter= ReposAdapter(ArrayList())
        viewModel=ViewModelProviders.of(this).get(GithubRepoViewModel::class.java)
        rv_repos.apply {
            this.adapter=this@MainActivity.adapter
            this.layoutManager=LinearLayoutManager(this@MainActivity)
        }
        viewModel.getAll().observe(this, Observer {
            println(it.size)
            adapter.updateList(it)
        })
        btn_add.setOnClickListener {
            viewModel.insert(GithubRepo(et_repo_name.text.toString()))
        }
    }
}
