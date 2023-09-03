package com.alberto.mydogsbreed.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alberto.mydogsbreed.ui.theme.MyDogsBreedTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DogsListScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyDogsBreedTheme {
                DogsListScreen(
                    dogsData = listOf(
                        "akita", "husky", "beagle"
                    ),
                    navigation = rememberNavController()
                )
            }
        }
    }

    @Test
    fun testDogsListScreenView() {
        composeTestRule.onNodeWithTag(TAG_DOGS_LIST_SCREEN).assertExists()
            .performScrollToIndex(0)
            .performScrollToIndex(1)
            .performScrollToIndex(2)
            .performScrollToIndex(0)
            .performScrollToIndex(1)
            .performScrollToIndex(2)
    }

}