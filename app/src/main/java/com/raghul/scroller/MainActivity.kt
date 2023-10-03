package com.raghul.scroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raghul.scroller.Data.Datasource
import com.raghul.scroller.Data.Datasource2
import com.raghul.scroller.model.Affirmation
import com.raghul.scroller.model.Topic
import com.raghul.scroller.ui.theme.ScrollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseGrid(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
                }
            }
        }
    }
}

@Composable
fun scroller(){
    AffirmationList(affirmationList = Datasource().loadAffirmations(),
        )
}

@Composable
fun theCard(affirmation: Affirmation,modifier:Modifier=Modifier){
        Card(modifier = modifier) {
            Column {
                Image(painter = painterResource(id = affirmation.imageResourceId),
                    contentDescription = stringResource(id = affirmation.stringResourceId)
                ,modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp),
                    contentScale = ContentScale.Crop
                )

                Text(text = LocalContext.current.getString(affirmation.stringResourceId),
                    modifier=Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                    )
            }
        }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>,
                    modifier: Modifier=Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            theCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun CourseGrid(modifier: Modifier=Modifier){
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier)
    {
        items(Datasource2.topics){
            topic -> TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic,modifier: Modifier=Modifier){
    Card {
        Row {
            Box{
                Image(painter = painterResource(id = topic.picresource),
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                    )
            }
            Column {
                Text(text = stringResource(id = topic.stringresource),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start= dimensionResource(id = R.dimen.padding_medium),
                        top = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_small)
                    )
                )

                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(painter = painterResource(id = R.drawable.ic_grain )
                        , contentDescription = null,
                        modifier=Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium),
                            end = dimensionResource(id = R.dimen.padding_small)
                            )
                        )
                    Text(text = topic.number.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_small))
                        )

                    
                }

            }
        }
    }
}