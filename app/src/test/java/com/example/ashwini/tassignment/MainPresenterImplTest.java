package com.example.ashwini.tassignment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.ashwini.tassignment.Iterator.MainPresenterImpl;
import com.example.ashwini.tassignment.interfaces.MainView;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by ashwini on 11/03/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterImplTest {

    @Mock
    MainView mainView;

    private MainPresenterImpl mainPresenter;

    @Before
    public void setUp() throws Exception {
        mainPresenter = new MainPresenterImpl(mainView);
    }

    @Test
    public void checkIfViewIsReleasedOnStop() {
        mainPresenter.onDestroy();
        assertNull(mainPresenter.getMainView());
    }



}