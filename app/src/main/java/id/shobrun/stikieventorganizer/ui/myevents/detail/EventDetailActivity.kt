package id.shobrun.stikieventorganizer.ui.myevents.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerAppCompatActivity
import id.shobrun.stikieventorganizer.R

import id.shobrun.stikieventorganizer.extensions.simpleToolbarWithHome
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.ui.adapter.EventDetailPagerAdapter
import kotlinx.android.synthetic.main.activity_event_detail.*
import javax.inject.Inject

class EventDetailActivity : AppCompatActivity() {
    companion object{
        val EXTRA_EVENT = "extra_event"
    }
    var event: Event? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)
        simpleToolbarWithHome(toolbar, "Event")

        event = intent?.getParcelableExtra(EXTRA_EVENT)
        val sectionsPagerAdapter =
            EventDetailPagerAdapter(
                applicationContext,
                supportFragmentManager,
                event
            )
        val viewPager: ViewPager = findViewById(R.id.view_pager)

        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
