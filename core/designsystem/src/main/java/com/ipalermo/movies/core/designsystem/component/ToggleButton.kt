package com.ipalermo.movies.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Movies toggle button with icon and checked icon content slots. Wraps Material 3
 * [IconButton].
 *
 * @param checked Whether the toggle button is currently checked.
 * @param onCheckedChange Called when the user clicks the toggle button and toggles checked.
 * @param modifier Modifier to be applied to the toggle button.
 * @param enabled Controls the enabled state of the toggle button. When `false`, this toggle button
 * will not be clickable and will appear disabled to accessibility services.
 * @param icon The icon content to show when unchecked.
 * @param checkedIcon The icon content to show when checked.
 * @param size The size of the toggle button.
 * @param iconSize The size of the icon.
 * @param backgroundColor The background color when unchecked.
 * @param checkedBackgroundColor The background color when checked.
 * @param iconColor The icon color when unchecked.
 * @param iconColor The icon color when checked.
 */
@Composable
fun MoviesToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    checkedIcon: @Composable () -> Unit = icon,
    size: Dp = MoviesToggleButtonDefaults.ToggleButtonSize,
    iconSize: Dp = MoviesToggleButtonDefaults.ToggleButtonIconSize,
    backgroundColor: Color = Color.Transparent,
    checkedBackgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    iconColor: Color = contentColorFor(backgroundColor),
    checkedIconColor: Color = contentColorFor(checkedBackgroundColor)
) {
    val radius = with(LocalDensity.current) { (size / 2).toPx() }
    IconButton(
        onClick = { onCheckedChange(!checked) },
        modifier = modifier
            .size(size)
            .toggleable(value = checked, enabled = enabled, role = Role.Button, onValueChange = {
                onCheckedChange(!checked)
            })
            .drawBehind {
                drawCircle(
                    color = if (checked) checkedBackgroundColor else backgroundColor,
                    radius = radius
                )
            },
        enabled = enabled,
        content = {
            Box(
                modifier = Modifier.sizeIn(
                    maxWidth = iconSize,
                    maxHeight = iconSize
                )
            ) {
                val contentColor = if (checked) checkedIconColor else iconColor
                CompositionLocalProvider(LocalContentColor provides contentColor) {
                    if (checked) checkedIcon() else icon()
                }
            }
        }
    )
}

/**
 * Movies toggle button default values.
 */
object MoviesToggleButtonDefaults {
    val ToggleButtonSize = 40.dp
    val ToggleButtonIconSize = 18.dp
}
