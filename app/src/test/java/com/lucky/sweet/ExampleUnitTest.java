package com.lucky.sweet;

import com.lucky.sweet.manager.LoginRegisterManager;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine
 * (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void loginTest() throws Exception {
        assertEquals(true, LoginRegisterManager.userLogin("2222@163.com",
                "aaaaa"));
    }

    @Test
    public void EmailExistTest() throws Exception {
        assertEquals(0, LoginRegisterManager.CheckOutEmail("chinn96@163.com"));
    }
    @Test
    public void CheckOutEmailTest() throws Exception {
        assertEquals(0, LoginRegisterManager.CheckOutEmailFirPsw
                ("chinn96@163.com","aaaaaa"));
    }
    @Test
    public void CheckOutUserPswTest() throws Exception {
        assertEquals(0, LoginRegisterManager.CheckOutUserPsw
                ("chinn96@163.com","aaaaaa"));
    }
}