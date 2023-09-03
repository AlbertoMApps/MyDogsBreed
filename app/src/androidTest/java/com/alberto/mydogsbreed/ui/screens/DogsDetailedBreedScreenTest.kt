package com.alberto.mydogsbreed.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alberto.mydogsbreed.ui.theme.MyDogsBreedTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DogsDetailedBreedScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyDogsBreedTheme {
                DogsDetailedBreedScreen(
                    dogBreedImages = arrayListOf(
                        "https://images.dog.ceo/breeds/hound-afghan/n02088094_4037.jpg",
                        "https://images.dog.ceo/breeds/hound-basset/n02088238_8839.jpg",
                        "https://images.dog.ceo/breeds/hound-blood/n02088466_10831.jpg",
                        "https://images.dog.ceo/breeds/hound-afghan/n02088094_4037.jpg",
                        "https://images.dog.ceo/breeds/hound-basset/n02088238_8839.jpg",
                        "https://images.dog.ceo/breeds/hound-blood/n02088466_10831.jpg"
                    )
                )
            }
        }
    }

    @Test
    fun testDogBreedListScreenView() {
        composeTestRule.onNodeWithTag(TAG_DOGS_BREED_SCREEN).assertExists()
            .performScrollToIndex(0)
            .performScrollToIndex(1)
            .performScrollToIndex(2)
            .performScrollToIndex(3)
            .performScrollToIndex(4)
            .performScrollToIndex(5)
    }

}