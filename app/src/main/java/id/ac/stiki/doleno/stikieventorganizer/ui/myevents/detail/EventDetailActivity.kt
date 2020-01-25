package id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import id.ac.stiki.doleno.stikieventorganizer.R
import id.ac.stiki.doleno.stikieventorganizer.extensions.simpleToolbarWithHome
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Event
import id.ac.stiki.doleno.stikieventorganizer.ui.adapter.EventDetailPagerAdapter
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {
    companion object {
        val EXTRA_EVENT = "extra_event"
        val EXTRA_ID_EVENT = "extra_id_event"
        var currentEventId: String? = null
        var isNewEvent: Boolean = false
    }

    var event: Event? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        simpleToolbarWithHome(toolbar, "Event")
        event = intent?.getParcelableExtra(EXTRA_EVENT)
        isNewEvent = event == null
        currentEventId = event?.event_id
        val sectionsPagerAdapter =
            EventDetailPagerAdapter(
                applicationContext,
                supportFragmentManager
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
