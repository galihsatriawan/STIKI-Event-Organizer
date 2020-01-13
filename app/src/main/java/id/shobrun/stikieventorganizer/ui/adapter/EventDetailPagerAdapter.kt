package id.shobrun.stikieventorganizer.ui.adapter

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.shobrun.stikieventorganizer.R
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventDetailActivity.Companion.EXTRA_EVENT
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventDetailFragment
import id.shobrun.stikieventorganizer.ui.myevents.detail.ParticipantEventFragment


private val TAB_TITLES = arrayOf(
    R.string.tab_event,
    R.string.tab_participant

)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class EventDetailPagerAdapter(private val context: Context, fm: FragmentManager, private val event: Event?) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        if (position == 0) {
            fragment = EventDetailFragment.newInstance()
        } else {
            fragment = ParticipantEventFragment.newInstance()
        }
        fragment.arguments = bundleOf(EXTRA_EVENT to event)
        return fragment

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}