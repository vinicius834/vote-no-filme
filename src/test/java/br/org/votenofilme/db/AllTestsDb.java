package br.org.votenofilme.db;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DAOTest.class, EntityManagerProviderTest.class })
public class AllTestsDb {

}
