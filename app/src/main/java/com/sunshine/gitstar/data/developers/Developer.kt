package com.sunshine.gitstar.data.developers
import com.google.gson.annotations.SerializedName

data class Developer (
	@SerializedName("username") val username : String,
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String,
	@SerializedName("avatar") val avatar : String,
	@SerializedName("repo") val repo : Repo,
	@SerializedName("sponsorUrl") val sponsorUrl: String
)