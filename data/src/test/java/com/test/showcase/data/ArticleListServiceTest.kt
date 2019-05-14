package com.test.showcase.data

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleListServiceTest {

    @Test
    fun testDefaultSection() {
        assertEquals("all-sections",  ArticleListService.DEFAULT_SECTION)
    }

    @Test
    fun testDefaultPeriod() {
        assertEquals("7",  ArticleListService.DEFAULT_PERIOD)
    }

    @Test
    fun testSections() {
        assertEquals(4,  ArticleListService.SECTIONS.size)
    }

    @Test
    fun testPeriods() {
        assertEquals(3,  ArticleListService.PERIODS.size)
    }
}