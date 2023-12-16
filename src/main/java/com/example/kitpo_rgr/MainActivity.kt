package com.example.kitpo_rgr

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kitpo_rgr.Builder.UserType


class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var builder: UserType
        val mainText: TextView = findViewById(R.id.main_text)
        mainText.text = viewModel.logText
        val scroll: ScrollView = findViewById(R.id.scroller)
        val spinner: Spinner = findViewById(R.id.spinner)
        val spinnerList = UserFactory.getTypeNameList()
        val adapter = ArrayAdapter(spinner.context, android.R.layout.simple_spinner_item, spinnerList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val Index: EditText = findViewById(R.id.index_input)
        val insertValue: EditText = findViewById(R.id.insert_value_input)
        val insertBtn: Button = findViewById(R.id.insert_btn)
        insertBtn.setOnClickListener {
            if (Index.text.isNullOrEmpty() || insertValue.text.isNullOrEmpty()) return@setOnClickListener
            val index = Integer.parseInt(Index.text.toString())
            val value = UserFactory
                .getBuilderByName(spinner.selectedItem.toString())
                .parseValue(insertValue.text.toString()) as UserType
            Index.setText("")
            insertValue.setText("")

            viewModel.list.add(value,index)
            viewModel.logText = viewModel.logText + "\nInsert " + value.toString() + " at " + index
            mainText.text = viewModel.logText
        }

        spinner.setSelection(0,false)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                viewModel.list.init()
                insertValue.hint = spinner.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val insfrontBtn: Button = findViewById(R.id.insert_front)
        insfrontBtn.setOnClickListener {
            if (insertValue.text.isNullOrEmpty()) return@setOnClickListener
            val value = UserFactory
                .getBuilderByName(spinner.selectedItem.toString())
                .parseValue(insertValue.text.toString()) as UserType
            insertValue.setText("")

            viewModel.list.pushFront(value)
            viewModel.logText = viewModel.logText + "\nInsert front: " + value.toString()
            mainText.text = viewModel.logText
        }

        val insendBtn: Button = findViewById(R.id.insert_end)
        insendBtn.setOnClickListener {
            if (insertValue.text.isNullOrEmpty()) return@setOnClickListener
            val value = UserFactory
                .getBuilderByName(spinner.selectedItem.toString())
                .parseValue(insertValue.text.toString()) as UserType
            insertValue.setText("")

            viewModel.list.pushEnd(value)
            viewModel.logText = viewModel.logText + "\nInsert end: " + value.toString()
            mainText.text = viewModel.logText
        }

        val searchBtn: Button = findViewById(R.id.search_btn)
        searchBtn.setOnClickListener {
            if (Index.text.isNullOrEmpty()) return@setOnClickListener
            val index = Integer.parseInt(Index.text.toString())
            Index.setText("")
            viewModel.logText = viewModel.logText + "\nIndex " + index + ": " + viewModel.list.find(index)
            mainText.text = viewModel.logText
        }

        val removeBtn: Button = findViewById(R.id.remove_btn)
        removeBtn.setOnClickListener {
            if (Index.text.isNullOrEmpty()) return@setOnClickListener
            val index = Integer.parseInt(Index.text.toString())
            viewModel.list.delete(index)
            viewModel.logText = viewModel.logText + "\nIndex: " + index + " deleted"
            mainText.text =  viewModel.logText
            scroll.fullScroll(View.FOCUS_DOWN)
        }

        val sortBtn: Button = findViewById(R.id.sort_btn)
        sortBtn.setOnClickListener {
            builder = UserFactory.getBuilderByName(spinner.selectedItem.toString())
            viewModel.list.sort(builder.getTypeComparator())
            viewModel.logText = viewModel.logText + "\nList was sorted!\n"
            mainText.text =  viewModel.logText
            scroll.fullScroll(View.FOCUS_DOWN)
        }

        val clearBtn: Button = findViewById(R.id.clear_btn)
        clearBtn.setOnClickListener {
            viewModel.list.clear()
            viewModel.logText = viewModel.logText + "\nList cleared!\n"
            mainText.text =  viewModel.logText
            scroll.fullScroll(View.FOCUS_DOWN)
        }

        val showBtn: Button = findViewById(R.id.show_btn)
        showBtn.setOnClickListener {
            viewModel.logText = viewModel.logText + "\n" + viewModel.list.toString()
            mainText.text =  viewModel.logText
            scroll.fullScroll(View.FOCUS_DOWN)
        }

        val saveBtn: Button = findViewById(R.id.save_btn)
        saveBtn.setOnClickListener {
            Serialization.saveToFile(viewModel.list,"list.txt", spinner.selectedItem.toString())
            viewModel.logText = viewModel.logText + "\nList was saved!\n"
            mainText.text =  viewModel.logText
            scroll.fullScroll(View.FOCUS_DOWN)
        }

        val loadBtn: Button = findViewById(R.id.load_btn)
        loadBtn.setOnClickListener {
            viewModel.list = Serialization.loadFile("list.txt")
            viewModel.logText = viewModel.logText + "\nList was loaded!\n"
            mainText.text =  viewModel.logText
            scroll.fullScroll(View.FOCUS_DOWN)
        }
    }
}