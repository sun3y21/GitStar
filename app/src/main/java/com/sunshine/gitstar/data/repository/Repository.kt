package com.sunshine.gitstar.data.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository (

	@SerializedName("author") val author : String?,
	@SerializedName("name") val name : String?,
	@SerializedName("avatar") val avatar : String?,
	@SerializedName("url") val url : String?,
	@SerializedName("description") val description : String?,
	@SerializedName("language") val language : String?,
	@SerializedName("languageColor") val languageColor : String?,
	@SerializedName("stars") val stars : Int?,
	@SerializedName("forks") val forks : Int?,
	@SerializedName("currentPeriodStars") val currentPeriodStars : Int?,
	@SerializedName("builtBy") val builtBy : List<BuiltBy>?
) : Parcelable