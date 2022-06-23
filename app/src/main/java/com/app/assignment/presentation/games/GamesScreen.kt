package com.app.assignment.presentation.games

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.app.assignment.domain.model.Game
import com.app.assignment.presentation.WebViewActivity
import com.app.assignment.presentation.components.ProgressBar
import com.dcastalia.localappupdate.DownloadApk


@Composable
fun GamesScreen(
    viewModel: GamesViewModel = hiltViewModel(),
    onLogout: () -> Unit
){

    val state = viewModel.state

    if(state.errorCode == 401){
        onLogout()
    }
    if(state.isLoading){
        ProgressBar()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 16.dp, 0.dp, 0.dp)
    ) {
        items(state.games.size) { i ->
            val game = state.games[i]
            GameItem(
                game = game
            )
            if(i < state.games.size) {
                Divider(modifier = Modifier.padding(
                    horizontal = 16.dp
                ))
            }
        }
    }

}

@Composable
fun GameItem(game: Game){
    val context = LocalContext.current
    Row(modifier = Modifier.padding(16.dp)){
        Image(
            painter = rememberImagePainter(game.imageUrl),
            contentDescription = game.name,
            modifier = Modifier
                .width(80.dp)
                .height(100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            Text(game.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                handleClick(game, context)
            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary
            )) {
                Text(text = "Launch")
            }
        }
        
    }
}

fun handleClick(game: Game, context: Context) {
    if(game.gameForm == "BROWSER" && game.appUrl.isNotEmpty()){
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", game.appUrl)
        context.startActivity(intent)

    } else if(game.gameForm == "ANDROID" && game.packageId.isNotEmpty()&& game.isOnPlayStore == false){
        downloadAndInstall(game, context)
    }
    else if(game.gameForm == "ANDROID" && game.packageId.isNotEmpty() && game.isOnPlayStore == true){
        startNewActivity(context, game.packageId)
    }
}

fun downloadAndInstall(game: Game, context: Context) {
    val intent = context.packageManager.getLaunchIntentForPackage(game.packageId)
    if (intent == null) {
        checkStoragePermission(game.appUrl, context)
    } else{
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}

fun startNewActivity(context: Context, packageName: String) {
    var intent = context.packageManager.getLaunchIntentForPackage(packageName)
    if (intent == null) {
        intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=$packageName")
    }
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}

private fun checkStoragePermission(url: String, context: Context) {
    val downloadApk = DownloadApk(context)
    downloadApk.startDownloadingApk(url);
}

