package ixidev.jetspring.ui.auth


import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication
import org.springframework.test.context.junit4.SpringRunner

/***
 * Created by ixiDev on 22/08/2022
 * GitHub : https://www.github.com/ixiDev
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnableGlobalAuthentication
class LoginScreenTest {


    @Autowired
    lateinit var viewModel: LoginViewModel


    @Test
    fun loginScreen(): Unit = runBlocking {

    }


}