package com.sunshine.gitstar.data.developers

import com.google.gson.annotations.SerializedName

data class Repo (

	@SerializedName("name") val name : String,
	@SerializedName("description") val description : String,
	@SerializedName("url") val url : String
)