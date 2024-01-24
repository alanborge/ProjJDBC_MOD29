package br.com.borges;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClienteTest.class, ProdutoTest.class, VendaTest.class})
public class AllTest {

}
