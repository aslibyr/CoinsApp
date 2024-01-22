package com.app.coins.custom.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.coins.utils.theme.light
import com.app.coins.utils.theme.primaryBackgroundColor
import com.app.coins.utils.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier,
    label: String,
    text: String,
    returnText: (String) -> Unit,
    onImeClicked: () -> Unit,
    keyboardOptions: KeyboardOptions,
    maxLength: Int? = null,
    errorMessage: String? = null
) {

    var isFocused by rememberSaveable {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = text,
        onValueChange = { output ->
            if (maxLength != null) {
                if (output.length <= maxLength) returnText(output)
            } else {
                returnText(output)
            }
        },
        modifier = modifier.onFocusChanged {
            isFocused = it.hasFocus
        },
        label = {
            Text(
                text = label,
                fontSize = 11.sp
            )
        },
        trailingIcon = {},
        isError = !errorMessage.isNullOrEmpty(),
        supportingText = {
            errorMessage?.let { it1 ->
                Text(
                    text = it1,
                    fontSize = 11.sp,
                    color = Color.Red
                )
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                onImeClicked()
            },
            onGo = {
                onImeClicked()
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = light,
            unfocusedIndicatorColor = primaryBackgroundColor,
            focusedContainerColor = primaryBackgroundColor,
            unfocusedContainerColor = primaryBackgroundColor,
            focusedLabelColor = light,
            unfocusedLabelColor = light,
            cursorColor = Color.White,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor
        ),
        shape = RoundedCornerShape(20.dp)
    )
}