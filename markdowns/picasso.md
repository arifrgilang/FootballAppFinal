# Picasso

#### Location file:

> view->activities>EventDetailActivity.kt->**Line 470**

#### Code:

```
override fun setHomeBadges(teams: List<TeamBadges>) {
    Picasso.get().load(teams[0].strTeamBadge).into(homeBadges)
}
```
Load `String` Url into `ImageView`