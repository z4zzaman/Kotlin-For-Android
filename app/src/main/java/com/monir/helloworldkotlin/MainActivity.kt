package com.monir.helloworldkotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import com.monir.helloworldkotlin.R.id.toast_button


class MainActivity : AppCompatActivity(), View.OnClickListener {
    // private var buttonToast: Button? = null
    //  private var buttonCount: Button? = null
    //  private lateinit var buttonRandom : Button

    /**
     * lazy initialization process to remove mutability
     */
    private val buttonToast by bind<Button>(toast_button)
    private val buttonCount by bind<Button>(R.id.count_button)
    private val buttonRandom by bind<Button>(R.id.random_button)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // buttonToast = findViewById(R.id.toast_button) as Button
        // buttonCount = findViewById(R.id.count_button) as Button
        //  buttonRandom = findViewById(R.id.random_button) as Button

        buttonToast.setOnClickListener() {
           toastMe()
        }
        buttonCount.setOnClickListener(this)
        buttonRandom.setOnClickListener(clickListener)
    }

    /**
     * To initialization default view component
     */
    fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return lazy(LazyThreadSafetyMode.NONE) { findViewById<T>(res) }
    }

    /**
     * For initialization custom view component
     */
    fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
        @Suppress("UNCHECKED_CAST")
        return unsafeLazy { findViewById<T>(idRes) }
    }

    private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

    /**
     * Class level click listener implementation
     */
    override fun onClick(view: View) {
        when (view.id) {
            toast_button -> {
                Toast.makeText(this, "Clicked 1", Toast.LENGTH_SHORT).show()
            }
            R.id.count_button -> {
               // Toast.makeText(this, "Clicked 2", Toast.LENGTH_SHORT).show()

                countMe()
            }

            R.id.random_button -> {
                Toast.makeText(this, "Clicked 3", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Custom click listener implementation
     */
    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.toast_button -> {
                Toast.makeText(this, "Clicked 1", Toast.LENGTH_SHORT).show()
            }
            R.id.count_button -> {
                Toast.makeText(this, "Clicked 2", Toast.LENGTH_SHORT).show()
            }

            R.id.random_button -> {
              //  Toast.makeText(this, "Clicked 3", Toast.LENGTH_SHORT).show()

                randomMe();
            }
        }
    }

    fun toastMe() {
        // val myToast = Toast.makeText(this, message, duration);
        val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun countMe () {
        // Get the text view
        val showCountTextView = findViewById<TextView>(R.id.textView)

        // Get the value of the text view.
        val countString = showCountTextView.text.toString()

        // Convert value to a number and increment it
        var count: Int = Integer.parseInt(countString)
        count++

        // Display the new value in the text view.
        showCountTextView.text = count.toString()
    }

    fun randomMe(){
     //   Intent intent = new Intent(this, RandomActivity.class)
        val randomIntent = Intent(this, RandomActivity::class.java)
        // Get the text view
        val textView = findViewById<TextView>(R.id.textView)

        // Get the current value of the text view.
        val countString = textView.text.toString()

        // Convert the count to an int
        val count = Integer.parseInt(countString)

        // Add the count to the extras for the Intent.
        randomIntent.putExtra(RandomActivity.TOTAL_COUNT, count)

        startActivity(randomIntent)
    }


}
