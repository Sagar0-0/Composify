import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.TransformOrigin

fun ScaleIn() {
    Column {
        var showRed by remember { mutableStateOf(true) }
        var showGreen by remember { mutableStateOf(true) }

        AnimatedVisibility(
            visible = showGreen,
            enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
            exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
        ) {
            Box(
                Modifier.size(100.dp)
                    .background(color = Color.Green, shape = RoundedCornerShape(20.dp))
            )
        }

        AnimatedVisibility(
            visible = showRed,
            enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                fadeIn() + expandIn(expandFrom = Alignment.TopStart),
            exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
        ) {
            Box(
                Modifier.size(100.dp)
                    .background(color = Color.Red, shape = RoundedCornerShape(20.dp))
            )
        }
    }
}
