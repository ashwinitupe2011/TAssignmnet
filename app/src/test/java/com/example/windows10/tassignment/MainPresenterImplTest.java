package com.example.windows10.tassignment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import com.example.windows10.tassignment.Iterator.MainPresenterImpl;
import com.example.windows10.tassignment.data.Article;
import com.example.windows10.tassignment.interfaces.MainView;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by amit on 3/24/18.
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

//    @Test
//    public void checkIfEQArePassedToView() {
//        Article test1 = mock(Article.class);
//        Article test2 = mock(Article.class);
//
//        List<Article> list = new ArrayList<Article>(2);
//        list.add(test1);
//        list.add(test2);
//
//        mainPresenter.onSuccess("sucess",list);
//        verify(mainView, times(1)).onGetDataSuccess("success",list);
//        verify(mainView, times(1)).hideProgress();
//
//    }
//


    @Test
    public void checkIfViewIsReleasedOnStop() {
        mainPresenter.onDestroy();
        assertNull(mainPresenter.getMainView());
    }



}