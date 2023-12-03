package com.example.latihan14

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import com.example.latihan14.data.HeroesRepository
import com.example.latihan14.ui.theme.Latihan14Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Latihan14Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperHerroApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title={
                Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.displayLarge)
        }, modifier = modifier
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHerroApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar={
        SuperTopAppBar()
        }
    ){
//        it->
//        LazyColumn(contentPadding = it){
//            items(HeroesRepository.heroes){
//                HeroesItem(hero = it, modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
//            }
//        }
//        val heroes = HeroesRepository.heroes
        HeroesList(HeroesRepository.heroes, contentPadding = it)
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Latihan14Theme {
        SuperHerroApp()
    }
}