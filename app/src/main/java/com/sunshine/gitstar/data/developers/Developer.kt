package com.sunshine.gitstar.data.developers
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Developer (
	@SerializedName("username") val username : String,
	@SerializedName("name") val name : String?,
	@SerializedName("url") val url : String?,
	@SerializedName("avatar") val avatar : String?,
	@SerializedName("repo") val repo : Repo?,
	@SerializedName("sponsorUrl") val sponsorUrl: String?
) : Parcelable