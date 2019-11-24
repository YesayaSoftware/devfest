package software.yesaya.devfest.data.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: Int,

    val title: String,

    val content: String,

    val publish_date: String

) : Parcelable