package fr.systemathicdev.consoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import fr.systemathicdev.consoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ConsoApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        navigateToProductsList()
    }

    private fun navigateToProductsList(){
        findNavController(binding.navHostFragment.id).run {
            setGraph(fr.systemathicdev.feature.R.navigation.nav_graph_products)
        }
    }
}