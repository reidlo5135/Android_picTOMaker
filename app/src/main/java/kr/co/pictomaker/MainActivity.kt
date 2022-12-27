package kr.co.pictomaker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.pictomaker.common.infra.RecyclerViewAdapter
import kr.co.pictomaker.common.model.Entity
import kr.co.pictomaker.common.model.MainViewModel
import kr.co.pictomaker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        val button: Button = findViewById(R.id.button)
        var edit: EditText = findViewById(R.id.edit)

        val mAdapter = RecyclerViewAdapter(this, viewModel)
        val recyclerview = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.recycler_item)
        recyclerview.apply {
            var adapter = mAdapter
            var layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.allUsers.observe(this, Observer { users ->
            // Update the cached copy of the users in the adapter.
            users?.let { mAdapter.setUsers(it) }
        })

        // 버튼 클릭시 edit에 적혀있는 텍스트를 db에 저장
        button.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.insert(
                    Entity(0, edit.text.toString())
                )
            }
        }
    }
}