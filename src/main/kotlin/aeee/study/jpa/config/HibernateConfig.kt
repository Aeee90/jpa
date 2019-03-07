package aeee.study.jpa.config

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class HibernateConfig {

    @Autowired
    private lateinit var env: Environment

    @Bean
    fun sessionFactory(): LocalSessionFactoryBean = LocalSessionFactoryBean().apply {
        setDataSource(dataSource())
        setPackagesToScan("aeee.study.jpa.entity")
        hibernateProperties = Properties().apply {
            setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDB53Dialect")
            setProperty("hibernate.hbm2ddl.auto", "update")
        }
    }

    @Bean
    fun dataSource(): DataSource = BasicDataSource().apply {
        driverClassName = env["aeee.db.driverclassname"]
        url = env["aeee.db.url"]
        username = env["aeee.db.username"]
        password = env["aeee.db.password"]
    }


    @Bean
    fun hibernateTransactionManager(): PlatformTransactionManager = HibernateTransactionManager().apply {
        sessionFactory = sessionFactory().`object`
    }
}