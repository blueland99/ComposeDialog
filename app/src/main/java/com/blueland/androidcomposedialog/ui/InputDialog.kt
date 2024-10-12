package com.blueland.androidcomposedialog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueland.androidcomposedialog.R
import com.blueland.androidcomposedialog.viewmodel.InputDialogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDialog(
    viewModel: InputDialogViewModel = hiltViewModel()
) {
    val isDialogVisible by viewModel.isDialogVisible

    // 암호 입력 TextField
    var input by remember { mutableStateOf("") }

    if (isDialogVisible) {
        val shape = MaterialTheme.shapes.medium

        // Dialog가 표시될 때 초기화
        input = ""

        Dialog(
            onDismissRequest = {
                viewModel.onDismiss()
            }
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colorScheme.background, shape = shape)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = shape)
                    .clip(shape = shape)
            ) {
                Column {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        // Title 영역
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.input_title),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )

                        // 암호 입력
                        BasicTextField(
                            value = input,
                            onValueChange = { input = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .border(1.dp, MaterialTheme.colorScheme.outline, shape = MaterialTheme.shapes.small) // 1dp의 둥근 테두리 추가
                                .clip(MaterialTheme.shapes.small)
                                .background(MaterialTheme.colorScheme.background),
                            textStyle = MaterialTheme.typography.bodySmall,
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp, vertical = 8.dp) // 내부 패딩 추가
                                ) {
                                    if (input.isEmpty()) {
                                        Text(
                                            text = stringResource(R.string.input_hint),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.typography.bodySmall.color.copy(0.8f)
                                        )
                                    }
                                    innerTextField() // 실제 입력 필드 표시
                                }
                            },
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
                        )
                    }

                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)

                    // 버튼 영역
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            modifier = Modifier.weight(1f),
                            onClick = { viewModel.onDismiss() }
                        ) {
                            Text(stringResource(R.string.cancel))
                        }
                        VerticalDivider(color = MaterialTheme.colorScheme.outline)
                        TextButton(
                            modifier = Modifier.weight(1f),
                            onClick = { viewModel.onConfirm(input) }
                        ) {
                            Text(stringResource(R.string.confirm))
                        }
                    }
                }
            }
        }
    }
}
