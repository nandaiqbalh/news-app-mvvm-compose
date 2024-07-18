package com.nandaiqbalh.pawartos.presentation.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nandaiqbalh.pawartos.R
import com.nandaiqbalh.pawartos.domain.model.Article
import com.nandaiqbalh.pawartos.presentation.Dimens.ArticleCardSize
import com.nandaiqbalh.pawartos.presentation.Dimens.ExtraSmallPadding
import com.nandaiqbalh.pawartos.presentation.Dimens.SmallIconSize


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleCard(
	modifier: Modifier = Modifier,
	article: Article,
	onClick: (() -> Unit)? = null,
) {

	val context = LocalContext.current
	Row(
		modifier = modifier.clickable { onClick?.invoke() },

		) {
		AsyncImage(
			modifier = Modifier
				.size(ArticleCardSize)
				.clip(MaterialTheme.shapes.medium),
			model = ImageRequest.Builder(context).data(article.urlToImage).build(),
			contentDescription = null,
			contentScale = ContentScale.Crop
		)

        Spacer(modifier = Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

	        Row(
		        verticalAlignment = Alignment.CenterVertically
	        ) {

		        Icon(
			        painter = painterResource(id = R.drawable.ic_time),
			        contentDescription = null,
			        modifier = Modifier.size(SmallIconSize),
			        tint = colorResource(id = R.color.body)
		        )
		        Spacer(modifier = Modifier.width(ExtraSmallPadding))
		        Text(
			        text = article.publishedAt,
			        style = MaterialTheme.typography.labelSmall,
			        color = colorResource(id = R.color.body)
		        )
	        }

	        Text(
		        text = article.source.name,
		        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
		        color = colorResource(id = R.color.body)
	        )

        }
    }
}
