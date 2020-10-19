package com.hit.itaycohen.intentsandactions.main

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hit.itaycohen.intentsandactions.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            MyViewModelFactory(applicationContext)
        ).get(MainViewModel::class.java)
    }
    private val imm: InputMethodManager by lazy {
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initInteractionListeners()
    }

    private fun initInteractionListeners() {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            recyclerView.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
        textField.editText?.addTextChangedListener { editable ->
            editable ?: return@addTextChangedListener
            val amount = editable.toString().toIntOrNull()


            checkBox.isEnabled = amount != null && amount > 0
            goBtn.isEnabled = amount != null && amount > 0
            label.text = viewModel.getLabelTextFor(editable, amount)
            if (amount == null) {
                updateRecyclerView()
            }
        }
        goBtn.setOnClickListener { invokeUpdate() }
        textField.editText?.setOnEditorActionListener { _, action, _ ->
            if (action == EditorInfo.IME_ACTION_GO) {
                invokeUpdate()
                true
            } else
                false
        }
    }

    private fun initViews() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = StupidTextAdapter()
        }
        checkBox.isEnabled = false
        goBtn.isEnabled = false

        checkBox.isChecked = recyclerView.visibility == View.VISIBLE
    }

    private fun invokeUpdate() {
        updateRecyclerView()
        imm.hideSoftInputFromWindow(textField.editText!!.windowToken, 0)
    }

    private fun updateRecyclerView() {
        (recyclerView.adapter as? StupidTextAdapter)?.apply {
            rowsAmount = viewModel.inputAmount
            notifyDataSetChanged()
        }
    }
}
