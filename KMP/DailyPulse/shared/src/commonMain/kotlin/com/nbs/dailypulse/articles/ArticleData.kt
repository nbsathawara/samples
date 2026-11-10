package com.nbs.dailypulse.articles

import com.nbs.dailypulse.db.ArticleEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime


data class Article(
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String
)

@Serializable
data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)

@Serializable
data class ArticleDto(
    val title: String,
    val description: String? = null,
    @SerialName("publishedAt") val publishedAt: String,
    @SerialName("urlToImage") val urlToImage: String? = null
)

data class ArticleRequest(
    val country: String = "us",
    val category: String = "business",
    val apiKey: String = "8f3fddf391094922b79027f42014140d"
)

@OptIn(ExperimentalTime::class)
fun String.toDaysAgo(): String {
    return try {
        val publishedInstant = Instant.parse(this)
        val timeZone = TimeZone.currentSystemDefault()

        val today = Clock.System.now().toLocalDateTime(timeZone).date
        val publishedDate = publishedInstant.toLocalDateTime(timeZone).date

        val daysDifference = publishedDate.daysUntil(today)

        when {
            daysDifference <= 0 -> "Today"
            daysDifference == 1 -> "Yesterday"
            daysDifference < 30 -> "$daysDifference days ago"
            daysDifference < 365 -> "${daysDifference / 30} month${if (daysDifference / 30 > 1) "s" else ""} ago"
            else -> "${daysDifference / 365} year${if (daysDifference / 365 > 1) "s" else ""} ago"
        }
    } catch (_: Exception) {
        this // Fallback to raw string if parsing fails
    }
}


// 1. API DTO -> Domain Model (UI)
fun ArticleDto.toModel(): Article = Article(
    title = title,
    description = description ?: "Click to find out more.",
    date = publishedAt.toDaysAgo(),
    imageUrl = urlToImage ?: "https://picsum.photos/800/400?random=1"
)

// 2. Room Entity -> Domain Model (UI)
fun ArticleEntity.toModel(): Article = Article(
    title = title,
    description = description.ifEmpty { "Click to find out more." },
    date = date.toDaysAgo(),
    imageUrl = imageUrl.ifEmpty { "https://picsum.photos/800/400?random=1" }
)

// 3. API DTO -> Room Entity (Cache)
fun ArticleDto.toEntity(): ArticleEntity = ArticleEntity(
    title = title,
    description = description ?: "",
    date = publishedAt,
    imageUrl = urlToImage ?: ""
)