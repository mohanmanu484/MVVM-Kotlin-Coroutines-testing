package com.mohan.prepare

import android.opengl.ETC1.isValid
import junit.framework.Assert
import org.junit.Test
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.*
import java.util.List

class TestIntro {


    @Test
    fun `test times params`() {
        val list= spy(mutableListOf(1,2,3))
        list.add(5)
        list.add(6)
        verify(list, times(1)).add(5)
    }

    @Test
    fun `test never params`() {
        val list= spy(mutableListOf(1,2,3))
        list.add(5)
        list.add(6)
        verify(list, never()).add(1)
    }

    @Test
    fun `test atleast params`() {
        val list= spy(mutableListOf(1,2,3))
        list.add(5)
        list.add(6)
        verify(list, atLeast(2)).add(ArgumentMatchers.anyInt())
    }

    @Test
    fun `test stubbing`() {
        val list= spy(mutableListOf(1,2,3))

        Mockito.`when`(list.contains(argThat(ArgumentMatcher { true }))).thenReturn(false)
        list.add(5)
        list.add(6)

        Assert.assertFalse(list.contains(7))
    }


}
