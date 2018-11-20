# Fragment

#### Location file:

> view->activities->MainActivity.kt->**Line 50**

#### Code:
```
private fun loadFragment(fragment: Fragment) {
    with(supportFragmentManager.beginTransaction()) {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        replace(R.id.main_container, fragment)
        commit()
    }
}
```
Replace the `R.id.main_container` with `FrameLayout`'s id inside the xml layout or anko layout
```
<android.support.design.widget.TabLayout
            android:id="@+id/matches_tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabMode="fixed"/>

    <android.support.v4.view.ViewPager
            android:id="@+id/matches_viewpager"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
```

```
class TabLayoutFragment: Fragment(){
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_matches, container, false)
        tabLayout = view.findViewById(R.id.matches_tabs)
        viewPager = view.findViewById(R.id.matches_viewpager)

        pagerAdapter = PagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(FragmentOne(), "Title One")
        pagerAdapter.addFragment(FragmentTwo(), "Title Two")

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

//      optional
//      tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//          override fun onTabSelected(tab: TabLayout.Tab) {}
//          override fun onTabUnselected(tab: TabLayout.Tab) {}
//          override fun onTabReselected(tab: TabLayout.Tab) {}
//      })
        return view
    }
}
```