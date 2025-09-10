package com.nbs.mywishlistapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nbs.mywishlistapp.data.Constants

@Entity(tableName = Constants.TABLE_WISH)
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String = "",
    val description: String
)

val emptyWish = Wish(0, "", "")

object DummyData {
    val wishList = listOf(
        Wish(1, "asmsakfmdasdas", "sdfnsdkfnsmkdnfksdnfksndfksndfs"),
        Wish(1, "asmsakfmdasdas", "sdfnsdkfnsmkdnfksdnfksndfksndfs"),
        Wish(1, "asmsakfmdasdas", "sdfnsdkfnsmkdnfksdnfksndfksndfs"),
        Wish(1, "asmsakfmdasdas", "sdfnsdkfnsmkdnfksdnfksndfksndfs"),
        Wish(1, "asmsakfmdasdas", "sdfnsdkfnsmkdnfksdnfksndfksndfs"),
        Wish(1, "asmsakfmdasdas", "sdfnsdkfnsmkdnfksdnfksndfksndfs"),
    )
}
