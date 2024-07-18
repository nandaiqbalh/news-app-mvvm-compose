package com.nandaiqbalh.pawartos.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.nandaiqbalh.pawartos.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

	@TypeConverter
	fun sourceToString(source: Source): String {
		return "${source.id},${source.name}"
	}

	@TypeConverter
	fun stringToSource(source: String): Source {
		val list = source.split(",")
		return Source(list[0], list[1])
	}
}