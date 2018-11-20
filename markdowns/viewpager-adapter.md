# ViewPager Adapter

#### Location file:

> utils->PagerAdapter.kt

#### Code:

```
class PagerAdapter (fm: FragmentManager)  : FragmentPagerAdapter(fm) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getCount(): Int = mFragmentList.size
    override fun getItem(position: Int): Fragment = mFragmentList[position]
    override fun getPageTitle(position: Int): CharSequence = mFragmentTitleList[position]

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}
```
Store fragments and titles inside ArrayList.