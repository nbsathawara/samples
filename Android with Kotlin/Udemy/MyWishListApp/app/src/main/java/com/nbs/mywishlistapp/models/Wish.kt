package com.nbs.mywishlistapp.models

data class Wish(val id: Long = 0, val title: String = "", val description: String)

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
