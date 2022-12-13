package com.miu.cvbuilderapp

import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_home.*


class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        //get username
        //to study lesson 8
        var sintent = intent
        //tvUsername.text = sintent.getStringExtra("username")
        // =========== tab layout ===============
        val myPageAdapter = MyPageAdapter(this)
        vpager.adapter = myPageAdapter//viewpager2
        // Will align the space according to the Screen size to equally spread
        tlayout.tabGravity = TabLayout.GRAVITY_FILL
        /* Setting up Tab Layout with the ViewPageg2 is handled by the TabLayoutMediator
       * by passing your tablayout id and viewpager id*/
        TabLayoutMediator(tlayout, vpager){tab, position ->
            when(position){
                0->{
                    tab.text = "Home"
                }
                1->{
                    tab.text = "About Me"
                }
                2->{
                    tab.text = "Work"
                }
                3->{
                    tab.text = "CONTACT"
                }
            }
        }.attach()

       //card view bind has to be in fragment
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        item.setOnMenuItemClickListener {menuItem ->
            when(item.itemId){
                R.id.call -> {
                    dial()
                }
                R.id.email -> {
                    email()
                }
                R.id.whatsapp -> {
                    whatsapp()
                }
                R.id.linkedin -> {
                    linkedIn()
                }
                else -> false
            }
//        }
//        Toast.makeText(
//            applicationContext,
//            item.title.toString(),
//            Toast.LENGTH_LONG).show()
        return super.onOptionsItemSelected(item)
    }
    fun dial(){
        val i = Intent()
        i.action = Intent.ACTION_DIAL
        val et2 = resources.getString(R.string.ctPhone)
        i.data = Uri.parse("tel:$et2")
        startActivity(i)
    }
    fun linkedIn(){
        val uri = Uri.parse(resources.getString(R.string.ctLinkedIn));
        val it = Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
    }
    fun whatsapp(){
        // To open Third Party app Whatsapp directly
        val i = packageManager.getLaunchIntentForPackage("com.whatsapp")
        if(i!=null)
            startActivity(i)
        else
            Toast.makeText(applicationContext,"WhatsApp not installed", Toast.LENGTH_LONG).show()

    }
    fun email(){
        //send email implicit intent
        val emailIntent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", resources.getString(R.string.ctEmail), null
            )
        )
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Via CV Builder App")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, I'd like to connect you.")
        startActivity(Intent.createChooser(emailIntent, "Send email..."))

    }

}