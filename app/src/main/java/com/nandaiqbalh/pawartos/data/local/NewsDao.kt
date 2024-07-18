package com.nandaiqbalh.pawartos.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nandaiqbalh.pawartos.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsert(article: Article)

	@Query("SELECT * FROM article")
	fun getAllArticles(): Flow<List<Article>>

	@Delete
	suspend fun deleteArticle(article: Article)

	@Query("SELECT * FROM article WHERE url = :url")
	suspend fun getArticleByUrl(url: String): Article?
}
