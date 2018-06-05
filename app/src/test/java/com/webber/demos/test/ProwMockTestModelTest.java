package com.webber.demos.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by picher on 2018/6/5.
 * Describe：
 */
@RunWith(PowerMockRunner.class)
public class ProwMockTestModelTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createTextInfo() throws Exception {
        ProwMockTestModel testModel = Mockito.mock(ProwMockTestModel.class);
        when(testModel,"createTextInfo").thenReturn("mock info");
        assertEquals("mock info",testModel.createTextInfo());
    }

}