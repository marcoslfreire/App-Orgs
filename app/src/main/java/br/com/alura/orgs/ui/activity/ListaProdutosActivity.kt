package br.com.alura.orgs.ui.activity

import android.app.admin.DevicePolicyManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import br.com.alura.orgs.dao.ProdutosDao
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.databinding.ActivityListaProdutosActivityBinding
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class ListaProdutosActivity : AppCompatActivity() {

    private val dao = ProdutosDao()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())
    private val binding by lazy {
        ActivityListaProdutosActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()

        val db = databaseBuilder(
            this,
            AppDataBase::class.java,
            "orgs.db"

        ).allowMainThreadQueries()
            .build()
        val  produtoDao = db.produtoDao()
        adapter.atualiza(produtoDao.buscaTodos())
    }

    override fun onResume() {
        super.onResume()
        val db = Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "orgs.db"
        ).allowMainThreadQueries()
            .build()
        val produtoDao = db.produtoDao()
        adapter.atualiza(produtoDao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

     private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DetalhesProdutoActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }
    }

}