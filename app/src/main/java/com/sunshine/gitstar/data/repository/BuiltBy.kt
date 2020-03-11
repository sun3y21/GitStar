package com.sunshine.gitstar.data.repository
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BuiltBy (

	@SerializedName("username") val username : String?,
	@SerializedName("href") val href : String?,
	@SerializedName("avatar") val avatar : String?
) : Parcelable