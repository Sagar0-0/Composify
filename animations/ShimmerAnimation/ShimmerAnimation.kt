import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun ShimmerAnimation(
    shimmerColorShades: List<Color>,//pass your preferred colors for shimmer
    cardHeight: Dp,//pass your card height so that shimmer can go all the way down to end of your card
    cardWidth: Dp,//pass your card width so that shimmer can go all the way down to end of your card
    gradientWidth: Float//pass your shimmer gradient width
) {
    val transition = rememberInfiniteTransition()
    val pxValueX = LocalDensity.current.run {
        cardWidth.toPx()//convert dp value to pixels for x axis
    }
    val pxValueY = LocalDensity.current.run {
        cardHeight.toPx()//convert dp value to pixels for y axis
    }

    val translateX by transition.animateFloat(
        initialValue = 0f,//starts from 0
        targetValue = pxValueX + gradientWidth,//goes all the way down to max pixels value
        animationSpec = infiniteRepeatable(//infinitely repeat the animation
            /*
             Tween Animates between values over specified [durationMillis]
            */
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing,
                delayMillis = 300
            )
        )
    )
    val translateY by transition.animateFloat(
        initialValue = 0f,//starts from 0
        targetValue = pxValueY + gradientWidth,//goes all the way down to max pixels value
        animationSpec = infiniteRepeatable(//infinitely repeat the animation
            /*
             Tween Animates between values over specified [durationMillis]
            */
            animation = tween(
                durationMillis = 1200,
                easing = LinearEasing,
                delayMillis = 300
            )
        )
    )

    /*
      Create a gradient using the list of colors
      Use Linear Gradient for animating in any direction according to requirement
      start=specifies the position to start with in cartesian like system Offset(10f,10f) means x(10,0) , y(0,10)
      end= Animate the end position to give the shimmer effect using the transition created above
    */
    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        //start shimmer line
        start = Offset(
            translateX - gradientWidth,
            translateY - gradientWidth
        ),//start even before 0 so that end shimmer line can start from 0
        //end shimmer line
        end = Offset(translateX, translateY)
    )

    //shimmer sample item to show transition
    ShimmerItem(
        brush = brush,
        height = cardHeight,
        width = cardWidth
    )

}


@Composable
fun ShimmerItem(
    brush: Brush,
    height: Dp,
    width: Dp
) {
    Spacer(
        modifier = Modifier
            .height(height)
            .width(width)
            .background(brush = brush)
    )
}
