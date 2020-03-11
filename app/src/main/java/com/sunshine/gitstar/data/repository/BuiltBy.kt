package com.sunshine.gitstar.data.repository
import com.google.gson.annotations.SerializedName

data class BuiltBy (

	@SerializedName("username") val username : String,
	@SerializedName("href") val href : String,
	@SerializedName("avatar") val avatar : String
)